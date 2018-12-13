package com.powerlab_thailand.myangel;

import android.os.Parcel;
import android.os.Parcelable;

public class RealTimeModel implements Parcelable {

    private int sensor;

    public RealTimeModel() {
    } //Getter

    public RealTimeModel(int sensor) {
        this.sensor = sensor;
    }

    protected RealTimeModel(Parcel in) {
        sensor = in.readInt();
    }

    public static final Creator<RealTimeModel> CREATOR = new Creator<RealTimeModel>() {
        @Override
        public RealTimeModel createFromParcel(Parcel in) {
            return new RealTimeModel(in);
        }

        @Override
        public RealTimeModel[] newArray(int size) {
            return new RealTimeModel[size];
        }
    };

    public int getSensor() {
        return sensor;
    }

    public void setSensor(int sensor) {
        this.sensor = sensor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sensor);
    }
}


