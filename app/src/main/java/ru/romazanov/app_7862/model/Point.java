package ru.romazanov.app_7862.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Point implements Parcelable {

    @SerializedName("x")
    private Double x;
    @SerializedName("y")
    private Double y;

    protected Point(Parcel in) {
        if (in.readByte() == 0) {
            x = null;
        } else {
            x = in.readDouble();
        }
        if (in.readByte() == 0) {
            y = null;
        } else {
            y = in.readDouble();
        }
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (x == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(x);
        }
        if (y == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(y);
        }
    }
}
