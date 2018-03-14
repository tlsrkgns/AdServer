package com.jaehoon.assignment.model;

enum Code {
    A, B, C
}

public class Area {
    private Code code;
    private int width, heigh;

    public String getCode() {
        return code.toString();
    }

    public void setCode(String code) {
        this.code = Code.valueOf(code);
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
}
