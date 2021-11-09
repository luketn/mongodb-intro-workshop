package com.mycodefu.mongodb.workshop.data;

import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Fruit {
    private ObjectId id;
    private String name;
    private Shape shape;
    private int defects;
    private boolean perfect;
    private Instant purchased;
    private List<DefectDetails> defectDetails;

    public Fruit(ObjectId id, String name, Shape shape, int defects, boolean perfect, Instant purchased) {
        this.id = id;
        this.name = name;
        this.shape = shape;
        this.defects = defects;
        this.perfect = perfect;
        this.purchased = purchased;
    }

    public Fruit() {
    }

    public static Fruit of(String name, Shape shape, int defects) {
        return new Fruit(null, name, shape, defects, defects == 0, Instant.now());
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getDefects() {
        return defects;
    }

    public void setDefects(int defects) {
        this.defects = defects;
    }

    public boolean isPerfect() {
        return perfect;
    }

    public void setPerfect(boolean perfect) {
        this.perfect = perfect;
    }

    public Instant getPurchased() {
        return purchased;
    }

    public void setPurchased(Instant purchased) {
        this.purchased = purchased;
    }

    public List<DefectDetails> getDefectDetails() {
        return defectDetails;
    }

    public void setDefectDetails(List<DefectDetails> defectDetails) {
        this.defectDetails = defectDetails;
    }

    public void addDefectDetails(DefectDetails value) {
        if (this.defectDetails == null) {
            this.defectDetails = new ArrayList<>();
        }
        this.defectDetails.add(value);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shape=" + shape +
                ", defects=" + defects +
                ", perfect=" + perfect +
                ", purchased=" + purchased +
                ", defectDetails=" + defectDetails +
                '}';
    }
}
