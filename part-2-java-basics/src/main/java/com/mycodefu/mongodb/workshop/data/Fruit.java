package com.mycodefu.mongodb.workshop.data;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Fruit {
    @BsonId
    private ObjectId id;
    private String name;
    private Color color;
    private int defects;
    private boolean perfect;
    private Instant purchased;
    private List<DefectDetails> defectDetails = new ArrayList<>();;

    public static Fruit of(String name, Color color, int defects) {
        Fruit fruit = new Fruit();
        fruit.name=name;
        fruit.color = color;
        fruit.defects=defects;
        fruit.perfect = defects == 0;
        return fruit;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getDefects() {
        return defects;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public Instant getPurchased() {
        return purchased;
    }

    public List<DefectDetails> getDefectDetails() {
        return defectDetails;
    }

    public Fruit addDefectDetails(DefectDetails value) {
        this.defectDetails.add(value);
        return this;
    }

    public Fruit purchase() {
        purchased = Instant.now();
        return this;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", defects=" + defects +
                ", perfect=" + perfect +
                ", purchased=" + purchased +
                ", defectDetails=" + defectDetails +
                '}';
    }
}
