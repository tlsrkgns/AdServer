package com.jaehoon.assignment.model;

public class Campaign {
    public enum Type {
        CPM, CPC
    }

    public static final double CTR = 0.002;

    private String img;
    private int width, height;
    private int cost;
    private Type type;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int heigh) {
        this.height = heigh;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getCPM() {
        return (this.getType().equals(Type.CPM)) ? this.getCost() : this.getCost() * CTR * 1000;
    }
}
