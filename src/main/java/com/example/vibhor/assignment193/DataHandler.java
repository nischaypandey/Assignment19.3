package com.example.vibhor.assignment193;

/**
 * Created by Vibhor on 07-01-2018.
 */

//creating class to store the data
public class DataHandler
{
    //Fields in the class.
    private String name;
    private int id,vote;

    //Getters and Setters for the fields.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
