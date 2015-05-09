package foodItemsScreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import diaryMain.DiaryMainScreen;


public class FoodItemDescription extends Activity {

    //key that has been selected from any food screen
    String key;
    //category of the food
    //to connect to the right database for retrieving food item full info
    String category;
    //textView declaration
    TextView enterText1;
    TextView enterText2;
    TextView enterText3;
    TextView enterText4;
    TextView enterText5;
    TextView enterText6;
    TextView enterText7;
    TextView enterText8;
    TextView enterText9;
    //extras for the Dairy screen
    //user email
    String email;
    //selected item calories value
    String calories;
    //selected item serving size
    String servingSize;
    //selected proteins,fats,carbohydrates  values
    String carbs;
    String prots;
    String fats;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_description_activity);
        Firebase.setAndroidContext(this);

        //get the extra from previous food screen
        Bundle keyData = getIntent().getExtras();
        //in case there is no text
        if (keyData == null)
            return;
        key = keyData.getString("key");
        TextView FoodKeyDescription = (TextView) findViewById(R.id.foodKeyDescription);
        FoodKeyDescription.setText(key);


        //get user email
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");


        //get the category extra
        Bundle categoryData = getIntent().getExtras();
        //in case there is no data
        if (categoryData == null)
            return;
        //get the category
        category = categoryData.getString("category");


        //display the selected item
        displayTheData();

    }


        //connect to the right database and perform actions to retrive food information

    public void displayTheData() {
        //check what category use has selected and connect to the correct database
        if (category.equalsIgnoreCase("fruit")) {
            declareTextView();
            connectToFruitDB();

        } else if (category.equalsIgnoreCase("meat")) {
            declareTextView();
            connectToMeatDB();

        } else if (category.equalsIgnoreCase("vegetables")) {
            declareTextView();
            connectToVegsDB();

        } else if (category.equalsIgnoreCase("snacks")) {
            declareTextView();
            connectToSnacksDB();

        } else if(category.equalsIgnoreCase("drinks")){
            declareTextView();
            connectToDrinksDB();
        }else {
            declareTextView();
            connectToMyFoodDB();
        }
    }

    //set the text to the values from the database
    //method to get all fields into database
    public void declareTextView() {
        enterText1 = (TextView) findViewById(R.id.enterText1);
        enterText2 = (TextView) findViewById(R.id.enterText2);
        enterText3 = (TextView) findViewById(R.id.enterText3);
        enterText4 = (TextView) findViewById(R.id.enterText4);
        enterText5 = (TextView) findViewById(R.id.enterText5);
        enterText6 = (TextView) findViewById(R.id.enterText6);
        enterText7 = (TextView) findViewById(R.id.enterText7);
        enterText8 = (TextView) findViewById(R.id.enterText8);
        enterText9 = (TextView) findViewById(R.id.enterText9);


    }



    //connect to fruit database
    public void connectToFruitDB() {
        //create a reference to the database
        final Firebase refFruit = new Firebase("https://fitnessdiary.firebaseio.com/fruits");
        // Attach an listener to read the data reference
        refFruit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                enterText1.setText(String.valueOf(snapshot.child(key).child("calories").getValue()));
                enterText2.setText(String.valueOf(snapshot.child(key).child("fats").getValue()));
                enterText3.setText(String.valueOf(snapshot.child(key).child("protein").getValue()));
                enterText4.setText(String.valueOf(snapshot.child(key).child("carbohydrates").getValue()));
                enterText5.setText(String.valueOf(snapshot.child(key).child("cholesterol").getValue()));
                enterText6.setText(String.valueOf(snapshot.child(key).child("sodium").getValue()));
                enterText7.setText(String.valueOf(snapshot.child(key).child("vitaminA").getValue()));
                enterText8.setText(String.valueOf(snapshot.child(key).child("vitaminC").getValue()));
                enterText9.setText(String.valueOf(snapshot.child(key).child("zserving").getValue()));
                saveTheExtras();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    //connect to vegetable database
    public void connectToVegsDB() {
        //create a reference to the database
        final Firebase refVeg = new Firebase("https://fitnessdiary.firebaseio.com/vegetables");
        // Attach an listener to read the data reference
        refVeg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                enterText1.setText(String.valueOf(snapshot.child(key).child("calories").getValue()));
                enterText2.setText(String.valueOf(snapshot.child(key).child("fats").getValue()));
                enterText3.setText(String.valueOf(snapshot.child(key).child("protein").getValue()));
                enterText4.setText(String.valueOf(snapshot.child(key).child("carbohydrates").getValue()));
                enterText5.setText(String.valueOf(snapshot.child(key).child("cholesterol").getValue()));
                enterText6.setText(String.valueOf(snapshot.child(key).child("sodium").getValue()));
                enterText7.setText(String.valueOf(snapshot.child(key).child("vitaminA").getValue()));
                enterText8.setText(String.valueOf(snapshot.child(key).child("vitaminC").getValue()));
                enterText9.setText(String.valueOf(snapshot.child(key).child("zserving").getValue()));
                saveTheExtras();
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void connectToMeatDB() {
        //create a reference to the database
        final Firebase refMeat = new Firebase("https://fitnessdiary.firebaseio.com/meat");
        // Attach an listener to read the data reference
        refMeat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                enterText1.setText(String.valueOf(snapshot.child(key).child("calories").getValue()));
                enterText2.setText(String.valueOf(snapshot.child(key).child("fats").getValue()));
                enterText3.setText(String.valueOf(snapshot.child(key).child("protein").getValue()));
                enterText4.setText(String.valueOf(snapshot.child(key).child("carbohydrates").getValue()));
                enterText5.setText(String.valueOf(snapshot.child(key).child("cholesterol").getValue()));
                enterText6.setText(String.valueOf(snapshot.child(key).child("sodium").getValue()));
                enterText7.setText(String.valueOf(snapshot.child(key).child("vitaminA").getValue()));
                enterText8.setText(String.valueOf(snapshot.child(key).child("vitaminC").getValue()));
                enterText9.setText(String.valueOf(snapshot.child(key).child("zserving").getValue()));
                saveTheExtras();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void connectToDrinksDB() {
        //create a reference to the database
        final Firebase refMeat = new Firebase("https://fitnessdiary.firebaseio.com/drinks");
        // Attach an listener to read the data reference
        refMeat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                enterText1.setText(String.valueOf(snapshot.child(key).child("calories").getValue()));
                enterText2.setText(String.valueOf(snapshot.child(key).child("fats").getValue()));
                enterText3.setText(String.valueOf(snapshot.child(key).child("protein").getValue()));
                enterText4.setText(String.valueOf(snapshot.child(key).child("carbohydrates").getValue()));
                enterText5.setText(String.valueOf(snapshot.child(key).child("cholesterol").getValue()));
                enterText6.setText(String.valueOf(snapshot.child(key).child("sodium").getValue()));
                enterText7.setText(String.valueOf(snapshot.child(key).child("vitaminA").getValue()));
                enterText8.setText(String.valueOf(snapshot.child(key).child("vitaminC").getValue()));
                enterText9.setText(String.valueOf(snapshot.child(key).child("zserving").getValue()));
                saveTheExtras();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void connectToSnacksDB() {
        //create a reference to the database
        final Firebase refMeat = new Firebase("https://fitnessdiary.firebaseio.com/snacks");
        // Attach an listener to read the data reference
        refMeat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                enterText1.setText(String.valueOf(snapshot.child(key).child("calories").getValue()));
                enterText2.setText(String.valueOf(snapshot.child(key).child("fats").getValue()));
                enterText3.setText(String.valueOf(snapshot.child(key).child("protein").getValue()));
                enterText4.setText(String.valueOf(snapshot.child(key).child("carbohydrates").getValue()));
                enterText5.setText(String.valueOf(snapshot.child(key).child("cholesterol").getValue()));
                enterText6.setText(String.valueOf(snapshot.child(key).child("sodium").getValue()));
                enterText7.setText(String.valueOf(snapshot.child(key).child("vitaminA").getValue()));
                enterText8.setText(String.valueOf(snapshot.child(key).child("vitaminC").getValue()));
                enterText9.setText(String.valueOf(snapshot.child(key).child("zserving").getValue()));
                saveTheExtras();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void connectToMyFoodDB() {
        //create a reference to the database
        final Firebase refMeat = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/myFood");
        // Attach an listener to read the data reference
        refMeat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                enterText1.setText(String.valueOf(snapshot.child(key).child("calories").getValue()));
                enterText2.setText(String.valueOf(snapshot.child(key).child("fats").getValue()));
                enterText3.setText(String.valueOf(snapshot.child(key).child("protein").getValue()));
                enterText4.setText(String.valueOf(snapshot.child(key).child("carbohydrates").getValue()));
                enterText5.setText(String.valueOf(snapshot.child(key).child("cholesterol").getValue()));
                enterText6.setText(String.valueOf(snapshot.child(key).child("sodium").getValue()));
                enterText7.setText(String.valueOf(snapshot.child(key).child("vitaminA").getValue()));
                enterText8.setText(String.valueOf(snapshot.child(key).child("vitaminC").getValue()));
                enterText9.setText(String.valueOf(snapshot.child(key).child("zserving").getValue()));
                saveTheExtras();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    //methods that will save all the extras to be ready to take to Dairy Screen
    public void saveTheExtras()
    {
        //save the selected item values to a string so it could be passed to Dairy screens
        //calories
        calories = enterText1.getText().toString();
        //fats
        fats = enterText2.getText().toString();
        //proteins
        prots = enterText3.getText().toString();
        //carbohydrates
        carbs = enterText4.getText().toString();
        //serving size
        servingSize = enterText9.getText().toString();
    }

    public void saveToDiary(View view)
    {
        //remember the key and pass it as an extra
        //create new intent
        Intent intent = new Intent(this, DiaryMainScreen.class);
        //pass food item key as extra
        intent.putExtra("key", key);
        //pass the selected food calories and serving size as extra
        intent.putExtra("fats", fats);
        intent.putExtra("prots",prots);
        intent.putExtra("carbs", carbs);
        intent.putExtra("calories", calories);
        intent.putExtra("servingSize", servingSize);
        intent.putExtra("email",email);
        //start activity
        startActivity(intent);
    }
}

