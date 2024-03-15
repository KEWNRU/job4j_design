package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties config;
    private Connection connection;
    private final String dump;

    public ImportDB(Properties config, String dump) throws SQLException, ClassNotFoundException, IOException {
        this.config = config;
        initConnection();
        this.dump = dump;

    }

    public void initConnection() throws ClassNotFoundException, SQLException, IOException {
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        Class.forName(config.getProperty("driver_class"));
        connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("login"),
                config.getProperty("password"));
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(s -> {
                String[] values = s.split(";");
                validation(values);
                users.add(new User(values[0], values[1]));
            });
        }
        return users;
    }

    public void validation(String[] values) {
        if (values.length < 2 || values[0].isEmpty() || values[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public void createTable() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE users(name varchar(255), email varchar(255));")) {
            preparedStatement.execute();
        }
    }

    public void save(List<User> users) throws SQLException {
        for (User user : users) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email) values (?, ?);")) {
                preparedStatement.setString(1, user.name);
                preparedStatement.setString(2, user.email);
                preparedStatement.execute();
            }
        }
    }

    public void delete() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE users;")) {
            preparedStatement.execute();
        }
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getString("name"),
                            resultSet.getString("email")
                    ));
                }
            }
        }
        return users;
    }

    public record User(String name, String email) {

    }
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.createTable();
        dataBase.save(dataBase.load());
        dataBase.findAll().forEach(System.out::println);
        dataBase.delete();
    }
}