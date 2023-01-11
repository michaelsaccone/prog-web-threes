package it.michaelsaccone.sudoku.Beans;

import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
    private String name, surname, username;

    private Integer maxScore = 0;

    public User() {}
    public User(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public int compareTo(User user) {
        return this.maxScore - user.maxScore;
    }
}
