package org.example.tpo_12.model;

import java.util.Set;

public class BookDTO {
    public Integer id;

    public String author;
    public String title;
    public int price;
    public boolean isAvailable;
    public String description;
    public Set<Rating> ratings;
    public int averageRating = 0;

    public BookDTO(){}

    public BookDTO(String author, String title, int price, String description, Set<Rating> ratings){
        this.author = author;
        this.title = title;
        this.price = price;
        this.isAvailable = true;
        this.description = description;
        this.ratings = ratings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public int getAverageRating() {
        return averageRating;
    }
}
