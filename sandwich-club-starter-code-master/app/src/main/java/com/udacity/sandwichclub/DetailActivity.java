package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView origin = findViewById(R.id.origin_tv);
        ListView ingredientsLv = (ListView) findViewById(R.id.ingredients_lv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        //Sandwich[] sandwich = null;
        try {
            sandwich = JsonUtils.getSandwichDetails(json);
        }catch(JSONException e){
            e.printStackTrace();
        }

            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }


            populateUI(sandwich);
            Picasso.with(this)
                    .load(sandwich.getImage())
                    //.load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG")
                    .into(ingredientsIv);

            setTitle(sandwich.getMainName());

            final TextView originTextView = (TextView) findViewById(R.id.origin_tv);
            originTextView.setText(sandwich.getPlaceOfOrigin());


//            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                    (this, android.R.layout.simple_list_item_1, sandwich.getIngredients());
//            ingredientsLv.setAdapter(arrayAdapter);








    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

//        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
//        int[] toViews = {android.R.id.text1};
    }
}
