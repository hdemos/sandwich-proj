package com.udacity.sandwichclub;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import org.json.JSONException;
import java.util.List;


//submission1
public class DetailActivity extends AppCompatActivity {


    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ImageView ingredientsIv = findViewById(R.id.image_iv);


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


        //populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                //.load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG")
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        //alsoKnownAs

        //convert list to string of items
        String alsoKnownStr = "";
        List<String> alsoKnown = sandwich.getAlsoKnownAs();
        for (int i = 0; i < alsoKnown.size(); i++) {


            alsoKnownStr = alsoKnownStr + "'" + alsoKnown.get(i) +"'";
            if( alsoKnown.size() > 1 & (i+1 < alsoKnown.size()))
            {
                alsoKnownStr= alsoKnownStr + "<b> or </b>";
            }

        }

        Log.d("alsoKnownAsStr: ", "also Known as: " + alsoKnownStr);
        //populate ingredients to tv
        final TextView alsoKnownTV = (TextView) findViewById(R.id.also_known_tv);
        alsoKnownTV.setText(Html.fromHtml(alsoKnownStr));


        final TextView originTextView = (TextView) findViewById(R.id.origin_tv);


        String originAlt = "None"
;        Log.d("checkOrigin", "originLen:"+ sandwich.getPlaceOfOrigin() +"|");
        String originStr = sandwich.getPlaceOfOrigin();
        if(sandwich.getMainName()=="Ham and cheese sandwich") {

            //alsoKnownTV.setVisibility(1);
            alsoKnownTV.append(" N/A ");

            originTextView.setText(originAlt);

        }
        else {
            originTextView.setText(sandwich.getPlaceOfOrigin());
        }



        //convert to string
        String ingredientsStr = "";
        List<String> sand = sandwich.getIngredients();
        for (int i = 0; i < sandwich.getIngredients().size(); i++) {


            ingredientsStr = ingredientsStr + (i+1) + ". " + sand.get(i);
            if( sandwich.getIngredients().size() > 1 & (i+1 < sandwich.getIngredients().size()))
            {
                ingredientsStr= ingredientsStr + " \n";
            }

        }
        Log.d("ingredientStr", "ing string: " + ingredientsStr);
        //populate ingredients to tv
        final TextView ingredientsTV = (TextView) findViewById(R.id.ingredients_tv);
        ingredientsTV.setText(ingredientsStr);

        final TextView descriptTv = (TextView) findViewById(R.id.description_tv);
        descriptTv.setText(sandwich.getDescription());




//          Attempt to use listview for list of ingredients
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                    (this, android.R.layout.simple_list_item_1, sandwich.getIngredients());
//        ingredientsLv.setAdapter(arrayAdapter);
        //ListView won't show all items so workaround to change dynamically


//        LayoutParams lp = (LayoutParams) ingredientsLv.getLayoutParams();
//        Log.d("ingredientsLV", "Ingredients LV size: " + sandwich.getIngredients().size());
//        lp.height =  sandwich.getIngredients().size();
//        ingredientsLv.setLayoutParams(lp);
//        arrayAdapter.notifyDataSetChanged();

//        ViewGroup.LayoutParams param = ListView.getLayoutParams();
//        param.height = arrayAdapter.getCount()*30;
//       ListView.setLayoutParams(param);



    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {


    }
}
