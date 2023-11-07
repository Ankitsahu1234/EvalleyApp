package android.example.evalleyapp;

import org.bson.types.ObjectId;

public class Slots {

    public ObjectId id;
    public int number;
    public boolean isBooked;
    public ObjectId whichArea;

    public Slots() {
    }

    public Slots(ObjectId id, int number, boolean isBooked, ObjectId whichArea) {
        this.id = id;
        this.number = number;
        this.isBooked = isBooked;
        this.whichArea = whichArea;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public ObjectId getWhichArea() {
        return whichArea;
    }

    public void setWhichArea(ObjectId whichArea) {
        this.whichArea = whichArea;
    }
}
