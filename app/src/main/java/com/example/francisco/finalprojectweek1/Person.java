package com.example.francisco.finalprojectweek1;

import java.io.Serializable;

/**
 * Created by FRANCISCO on 02/08/2017.
 */

public class Person implements Serializable{

    String name;
    String gender;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
