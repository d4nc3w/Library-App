package org.example.tpo_12.model;

public class BookDTO {
    public Integer id;

    public String author;
    public String title;
    public int price;
    public boolean isAvailable;

    public BookDTO(){}

    public BookDTO(String author, String title, int price){
        this.author = author;
        this.title = title;
        this.price = price;
        this.isAvailable = true;
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
}
