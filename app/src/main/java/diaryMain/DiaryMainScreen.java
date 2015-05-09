package diaryMain;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import mainScreen.MainScreen;

public class DiaryMainScreen extends Activity {
    ListView listView;
    FoodAdapter adapter;

    ArrayList<DairyFoodItem> dairyItem;
    //key that has been selected from any food screen
    String key;
    //Make strings global to be stored in Map
    String calories;
    String servingSize;
    //user email
    String email;
    //extras for the user Dairy Database;
    String protein;
    String carbohydrates;
    String fats;
    //the values from the databases before calculations
    //proteins
    int iGoalProt;
    int iConProt;
    //carbs
    int iConCarb;
    int iGoalCarb;
    //fats
    int iConFats;
    int iGoalFats;



    //the new calculation values
    //proteins
    int iProteinCon;
    int iProteinRem;
    //carbs
    int iCarbohydratesCon;
    int iCarbohydratesRem;
    //fats
    int iFatsCon;
    int iFatsRem;
    //calories
    int iCaloriesMax;

    //string that would receive values from the Firebase
    //ust be global otherwise will return null
    //proteins
    String alreadyConProt;
    String alreadyGoalProt;

    //carbohydrates
    String alreadyConCarb;
    String alreadyGoalCarb;
    //fats
    String alreadyConFats;
    String alreadyGoalFats;
    //calories
    String alreadyMaxCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //get user email
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");

        dairyItem = new ArrayList<DairyFoodItem>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main_screen_activity);
        Firebase.setAndroidContext(this);
        dairyAction();

    }


    public void getExtras() {


        //get the food related extras from previous food screen
        //key
        Bundle keyData = getIntent().getExtras();
        //in case there is no text
        if (keyData == null)
            return;
        key = keyData.getString("key");

        //proteins
        Bundle proteinData = getIntent().getExtras();
        //in case there is no data
        if (proteinData == null)
            return;
        //get the category
        protein = proteinData.getString("prots");

        //carbohydrates
        Bundle carbohydrateData = getIntent().getExtras();
        //in case there is no data
        if (carbohydrateData == null)
            return;
        //get the category
        carbohydrates = carbohydrateData.getString("carbs");

        //fats
        Bundle fatsData = getIntent().getExtras();
        //in case there is no data
        if (fatsData == null)
            return;
        //get the category
        fats = fatsData.getString("fats");

        //calories
        Bundle caloriesData = getIntent().getExtras();
        //in case there is no data
        if (caloriesData == null)
            return;
        //get the category
        calories = caloriesData.getString("calories");

        //serving size
             Bundle servingSizeData = getIntent().getExtras();
             //in case there is no data
             if (servingSizeData == null)
                 return;
             //get the category
             servingSize = servingSizeData.getString("servingSize");


    }


    public void dairyAction()
    {
        getExtras();
        saveItemToDairy();
        addItems();
        getExistingCalculationValuesFromDB();

    }


    public void addItems() {

        //Get all the data from fruit database
        Firebase ref1 = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/dairy");
        ref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKe) {
                //get the items from the database and display it in the dairy
                Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();
                String foodKey = String.valueOf(snapshot.getKey());
                String foodCalories = String.valueOf(newPost.get("calories"));
                String foodServingSize = String.valueOf(newPost.get("zserving"));
                dairyItem.add(new DairyFoodItem(foodKey, foodCalories, foodServingSize));
                displayItems();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
    }

    public void displayItems()
    {
        listView=(ListView)findViewById(R.id.listView);
        adapter=new FoodAdapter(this,android.R.layout.simple_list_item_1, dairyItem);
        listView.setAdapter(adapter);
    }

    public void saveItemToDairy() {
        //Get a reference to the user dairy and save the extras there
        Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/dairy");
        ref.child(key).setValue(key);
        ref.child(key).child("protein").setValue(protein);
        ref.child(key).child("carbohydrates").setValue(carbohydrates);
        ref.child(key).child("fats").setValue(fats);
        ref.child(key).child("calories").setValue(calories);
        ref.child(key).child("zserving").setValue(servingSize);

    }


    //method witch gets proteins values from calculations: consumed, goal
    //save all the fetched values to global variables;
    public void getExistingCalculationValuesFromDB() {
        Firebase refProt = new Firebase("https://fitnessdiary.firebaseio.com/users/" + email + "/calculations");
        refProt.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //proteins
                alreadyConProt = String.valueOf(dataSnapshot.child("proteins").child("protCons").getValue());
                alreadyGoalProt = String.valueOf(dataSnapshot.child("proteins").child("protGoal").getValue());
                //put into an int for calculations
                iConProt = Integer.parseInt(alreadyConProt);
                iGoalProt = Integer.parseInt(alreadyGoalProt);

                //carbohydrates
                alreadyConCarb = String.valueOf(dataSnapshot.child("carbohydrates").child("carbsCons").getValue());
                alreadyGoalCarb = String.valueOf(dataSnapshot.child("carbohydrates").child("carbsGoal").getValue());
                //put them into string
                iConCarb = Integer.parseInt(alreadyConCarb);
                iGoalCarb = Integer.parseInt(alreadyGoalCarb);

                //fats
                alreadyConFats = String.valueOf(dataSnapshot.child("fats").child("fatCons").getValue());
                alreadyGoalFats = String.valueOf(dataSnapshot.child("fats").child("fatGoal").getValue());
                //cast to an int
                iConFats = Integer.parseInt(alreadyConCarb);
                iGoalFats = Integer.parseInt(alreadyGoalFats);

                //calories
                alreadyMaxCal = String.valueOf(dataSnapshot.child("calories").child("maxCalories").getValue());
                //cast to an int
                iCaloriesMax = Integer.parseInt(alreadyMaxCal);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


    public void newValueCalculation()
    {
        calculateProteins();
        calculationsOfCarbs();
        calculationsOfFats();
        calculationsOfMaxCalories();
    }

    public void calculationsOfFats()
    {

    }

    public void calculationsOfCarbs()
    {
        //get new food item,which has been added to diary
        //cast to int
        int iFoodConCarb = Integer.parseInt(carbohydrates);
        //calculate new consumed protein
        iConCarb=iConProt+iFoodConCarb;
        //save the value to global variable so it could be saved to DB
        //new consumed proteins
        iCarbohydratesCon = iConProt;
        //new remaining proteins
        iCarbohydratesRem = iGoalCarb - iCarbohydratesCon;
    }

    public void calculationsOfMaxCalories()
    {
        
    }


    public void calculateProteins()
    {
        //get new food item,which has been added to diary
        //cast to int
        int iFoodConProt = Integer.parseInt(protein);
        //calculate new consumed protein
        iConProt=iConProt+iFoodConProt;
        //save the value to global variable so it could be saved to DB
        //new consumed proteins
        iProteinCon = iConProt;
        //new remaining proteins
        iProteinRem = iGoalProt - iProteinCon;
    }

    //save all the new values to the user db
    public void saveCalculations()
    {
        //call all the calculations methods
        Firebase calRef = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/calculations");
        //save new consumed proteins
        calRef.child("proteins/protCons").setValue(iProteinCon);
        calRef.child("proteins/protRem").setValue(iProteinRem);
        //carbohydrates

        //fats

        //calories

    }

    public void backToMain(View view)
    {
        Intent i = new Intent(this, MainScreen.class);
        i.putExtra("email",email);
        newValueCalculation();
        saveCalculations();
        startActivity(i);
    }

}





