package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public String parseSandwichJson(String json) throws JSONException{
        //return null;

        //Sandwich.setMainName();

        //Initialize JSONObject from JSON string
        JSONObject details = new JSONObject(json);


        //Name into JSONObject
        JSONObject name = details.getJSONObject("name");
        //split into data

        String mainName = name.getString("mainName");

        JSONArray alsoKnownAs;
        alsoKnownAs = name.getJSONArray("AlsoKnownAs");

        String description = details.getString("description");
        String image = details.getString("image");
        getIngredients();
        String ingredients = details.getString("ingredients");

        return mainName;


    }

    protected static String[] getIngredients() {


        String[] abc = null;

        return abc;
    }
}
