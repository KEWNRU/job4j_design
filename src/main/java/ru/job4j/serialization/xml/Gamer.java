package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gamer {
    @XmlAttribute
    private boolean game;

    @XmlAttribute
    private int age;
    private Rank rank;
    private String status;

    public Gamer() {

    }
    public Gamer(boolean game, int age, Rank rank, String status) {
        this.game = game;
        this.age = age;
        this.rank = rank;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Gamer{"
                + "game=" + game
                + ", age=" + age
                + ", rank=" + rank
                + ", status='" + status + '\''
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Gamer gamer = new Gamer(true, 26, new Rank("level5"),
                "CS2, Faceit, Play");

        JAXBContext context = JAXBContext.newInstance(Gamer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(gamer, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
