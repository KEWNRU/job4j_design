package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Statement statement;
    private final Properties properties;

    public TableEditor(Properties properties) throws IOException, SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
        statement = connection.createStatement();
    }

    private void initConnection() throws IOException, SQLException, ClassNotFoundException {

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table %s ();", tableName);
        statement.execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s;", tableName);
        statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s;", tableName, columnName);
        statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        statement.execute(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("demo");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.addColumn("demo", "name", "varchar(255)");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.renameColumn("demo", "name", "name1");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.dropColumn("demo", "name1");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.dropTable("demo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}