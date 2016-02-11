package com.example.phoosop.testbook.mvp.model.object;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Created by Phoosop on 10.02.2016.
 */

@ParseClassName("Books")
public class Book extends ParseObject implements Serializable {

    public String getTitle(){
        return getString("title");
    }

    public String getDescription(){
        return getString("description");
    }

    public String getImage(){
        return getString("image");
    }

    public Integer getRaiting(){
        return getInt("raiting");
    }



}
