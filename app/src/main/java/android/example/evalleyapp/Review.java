package android.example.evalleyapp;

import org.bson.types.ObjectId;

public class Review {
    public ObjectId id;
    public ObjectId user;
    public String username;
    public int rating;
    public String review;
    public ObjectId whicharea;

    public Review() {
    }

    public Review(ObjectId id, ObjectId user, String username, int rating, String review, ObjectId whicharea) {
        this.id = id;
        this.user = user;
        this.username = username;
        this.rating = rating;
        this.review = review;
        this.whicharea = whicharea;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUser() {
        return user;
    }

    public void setUser(ObjectId user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ObjectId getWhicharea() {
        return whicharea;
    }

    public void setWhicharea(ObjectId whicharea) {
        this.whicharea = whicharea;
    }
}
