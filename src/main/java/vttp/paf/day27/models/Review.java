package vttp.paf.day27.models;

import java.util.LinkedList;
import java.util.List;

public class Review {
    private String user;
    private int rating;
    private String comment;
    private int id;
    private List<Comment> edited = new LinkedList<>();
    private String date;
    private String name;
    

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getId() {
        return id;
    }
    public void setId(int iD) {
        id = iD;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Comment> getEdited() {
        return edited;
    }
    public void setEdited(List<Comment> edited) {
        this.edited = edited;
    }
    public void addEdited(Comment c){
        this.edited.add(c);
    }

}
