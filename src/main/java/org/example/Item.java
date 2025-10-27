package org.example;

public abstract class Item {
    protected String title;
    protected double price;
    protected int year;

    public Item(String title, double price, int year) {
        this.title = title;
        this.price = price;
        this.year = year;
    }

    public String getTitle() {
        return title; }
    public double getPrice() {
        return price; }

    public int getYear() {
        return year; }

    public void setPrice(double price) {
        this.price = price; }

}
