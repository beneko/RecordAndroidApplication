package com.afpa.recordandroidapplication;

import android.provider.ContactsContract;

public class Disc {

    private int id;
    private String title;
    private int year;
    private String picture;
    private String label;
    private String genre;
    private int price;
    private String artist;

    public Disc(int id ,String artist, String title, String label, int year, String genre, String picture) {
        this.id=id;
        this.artist = artist;
        this.title = title;
        this.label = label;
        this.year = year;
        this.genre = genre;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
