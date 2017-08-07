package com.example.francisco.finalprojectweek1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by FRANCISCO on 03/08/2017.
 */

public class PersonW1D4_1 implements Parcelable {

    String name;
    String gender;

    public PersonW1D4_1(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    protected PersonW1D4_1(Parcel in) {
        name = in.readString();
        gender = in.readString();
    }

    public static final Creator<PersonW1D4_1> CREATOR = new Creator<PersonW1D4_1>() {
        @Override
        public PersonW1D4_1 createFromParcel(Parcel in) {
            return new PersonW1D4_1(in);
        }

        @Override
        public PersonW1D4_1[] newArray(int size) {
            return new PersonW1D4_1[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeString(gender);
    }
}
