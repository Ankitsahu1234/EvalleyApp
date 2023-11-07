package android.example.evalleyapp;

import org.bson.types.ObjectId;

public class ModelArea
{

    public ObjectId id;
    public String name;
    public String address;

    public ModelArea() {
    }

    public ModelArea(ObjectId id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
