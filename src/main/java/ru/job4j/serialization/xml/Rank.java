package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rank")
public class Rank {
    @XmlAttribute
    private String rank;

    public Rank() {

    }

    public Rank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Rank{"
                + "rank=" + rank
                + '}';
    }
}
