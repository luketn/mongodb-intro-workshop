package com.mycodefu.mongodb.workshop;

import org.bson.types.ObjectId;

import java.time.Instant;

public class Fruit {
    private ObjectId id;
    private String name;
    private Shape shape;
    private int defects;
    private boolean perfect;
    private Instant purchased;

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

    @Override
    public String toString() {
        return "Fruit{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shape=" + shape +
                ", defects=" + defects +
                ", perfect=" + perfect +
                ", purchased=" + purchased +
                '}';
    }
}
