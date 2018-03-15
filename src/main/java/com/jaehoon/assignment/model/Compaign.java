package com.jaehoon.assignment.model;

public class Compaign implements Comparable<Compaign> {
    public enum Type {
        CPM, CPC
    }

    private static final double CTR = 0.002;

    private String img;
    private int width, heigh;
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

    public int getHeigh() {
        return heigh;
    }

    public void setHeigh(int heigh) {
        this.heigh = heigh;
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

    @Override
    public int compareTo(Compaign o) {
        double cost1, cost2;
        cost1 = (this.getType().equals(Compaign.Type.CPM)) ? this.getCost() : this.getCost() * CTR * 1000;
        cost2 = (o.getType().equals(Compaign.Type.CPM)) ? o.getCost() : o.getCost() * CTR * 1000;
        int compare = Double.compare(cost2, cost1);

        return compare;
    }

}
