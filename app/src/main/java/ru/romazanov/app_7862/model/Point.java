package ru.romazanov.app_7862.model;

import com.google.gson.annotations.SerializedName;

public class Point {

    @SerializedName("x")
    private Double x;
    @SerializedName("y")
    private Double y;

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
