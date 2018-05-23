package com.udacity.sandwichclub.utils;

import android.util.Log;
import android.widget.Toast;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich getSandwichDetails(String json) throws JSONException {
        //can't instanciate list b/c its an interface (arraylist best bet)
        //convery JSPONArrays to collection type
        //instanciate w/ constructors

        //String[] parsedSandwichDetails = null;

        Sandwich s = new Sandwich();
        try {
            //Initialize JSONObject from JSON string
            JSONObject details = new JSONObject(json);

            //Name into JSONObject
            JSONObject name = details.getJSONObject("name");
            //split into mainName and alsoKnownAs array
            String mainName = name.getString("mainName");
            //check data
            //s.setMainName(mainName);

            if(mainName != null) {
                Log.d("NAME", "MainName is set:" + mainName);
            }

            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            //change JSONArray to ArrayList
            ArrayList<String> alsoKnownAsList = new ArrayList<String>(alsoKnownAs.length());
            for (int i = 0; i < alsoKnownAs.length(); i++) {

                //get string from alsoknownas
                alsoKnownAsList.add(alsoKnownAs.get(i).toString());
                //add to alsoknownas1
            }
            Log.d("ALSO", "AlsoKnown are converted:" + alsoKnownAsList);
            //s.setAlsoKnownAs(alsoKnownAsList);


            String origin = details.getString("placeOfOrigin");
            Log.d("Origin", "origin saved as:" + origin);
            //s.setPlaceOfOrigin(origin);

            String description = details.getString("description");
            //s.setDescription(description);

            String image = details.getString("image");
            //s.setImage(image);

            JSONArray ingredients = details.getJSONArray("ingredients");
            //verify the jsonarray is filled correctly
            Log.d("INGR", "Ingredients are pulled:" + ingredients);
            //change ingredients JSONArray to ArrayList
            ArrayList<String> ingredientsList = new ArrayList<String>(ingredients.length());
            for (int i = 0; i < ingredients.length(); i++) {

                //get string from alsoknownas
                ingredientsList.add(ingredients.get(i).toString());
                //add to alsoknownas1
            }
            Log.d("INGR", "Ingredients are converted:" + ingredientsList);
            //s.setIngredients(ingredientsList);

            //populate into sandwich
            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, origin, description, image, ingredientsList);
            //sandwich.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG");

            //Sandwich sandwich1 = new Sandwich();
            //sandwich.setImage(image);


            return sandwich;


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}
