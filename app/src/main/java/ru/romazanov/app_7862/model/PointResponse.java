package ru.romazanov.app_7862.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class PointResponse {

    @SerializedName("points")
    private ArrayList<Point> list;

    public ArrayList<Point> getList() {
        return list;
    }

    public void setList(ArrayList<Point> list) {
        this.list = list;
    }
}
