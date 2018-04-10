package com.java.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Shamil on 10-Apr-18.
 */
public class Town {

    private String name;

    public Town(){
        this("UndefinedName");
    }

    public Town(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Town)) return false;

        Town town = (Town) o;

        return getName().equals(town.getName());
    }

    @Override
    public int hashCode() {
        return (int) 3 ^ 2 ^ getName().hashCode();
    }
}
