package com.example.jmosk.fypdiary.setupScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.Firebase;

import mainScreen.MainScreen;


public class SetupS5 extends ActionBarActivity {

    float maximum;
    float minimum;
    float maximumRest;
    float carbs;
    float prots;
    float fats;

    //get user email to save all the progress
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_s5_activity);
        //connect to the Firebase with final email extra
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        Firebase.setAndroidContext(this);
        Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/calculations");

        //get extra from Setup Screen 3
        Bundle maximumData = getIntent().getExtras();
        //in case there is no text
        if (maximumData == null)
            return;
        //get maximum intake calculation result
        maximum = maximumData.getFloat("maximum");
        //save the maximum calories to the Database
        ref.child("calories/maxCalories").setValue(maximum);
        //duplicate maximum for the calculation method
        maximumRest = maximum;
        //display old maximum from Screen 3 as test
        TextView displayMax = (TextView) findViewById(R.id.displayMaximum);
        String maximumTest = Float.toString(maximum);
        displayMax.setText(maximumTest);


        //get minimum from screen 3
        Bundle minimumData = getIntent().getExtras();
        //in case there is no text
        if(minimumData == null)
            return;
        //get minimum intake calculation result
        minimum = minimumData.getFloat("minimum");
        //save the minimum calories to the Database
        ref.child("calories/minCalories").setValue(minimum);
        TextView displayMin = (TextView) findViewById(R.id.displayMinimum);
        String minMsg = Float.toString(minimum);
        displayMin.setText(minMsg);

        calculateRest();
    }

    public void calculateRest()
    {
        //connect to the database
        //create user dairy
        Firebase refDiary = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/dairy");
        refDiary.child("kiwi/proteins").setValue("15");
        //create user calculation table
        Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/calculations");

        //calculate Carbs 52%
        float partTwoCarbs;
        partTwoCarbs = maximumRest * 0.52f;
        carbs=partTwoCarbs / 4.2f;
        //save the carbs goal to the Database
        ref.child("carbohydrates/carbsGoal").setValue(carbs);
        //create other elements for carbohydrates for nicer looking table
        ref.child("carbohydrates/carbsCons").setValue("0");
        ref.child("carbohydrates/carbsRem").setValue("0");
        //display carbs
        String carbsMsg = Float.toString(carbs);
        TextView displayCarbs = (TextView) findViewById(R.id.displayCarbs);
        displayCarbs.setText(carbsMsg);


        //calculate Fats 27%
        float partTwoFats;
        partTwoFats = maximumRest * 0.27f;
        fats=partTwoFats / 9.5f;
        //save the fats goal to the Database
        ref.child("fats/fatGoal").setValue(fats);
        //create other elements for fats for nicer looking table
        ref.child("fats/fatCons").setValue("0");
        ref.child("fats/fatRem").setValue("0");
        //display Fats
        String fatsMsg = Float.toString(fats);
        TextView displayFats = (TextView) findViewById(R.id.displayFats);
        displayFats.setText(fatsMsg);


        //calculate Proteins 21%
        float partTwoProt;
        partTwoProt = maximumRest * 0.21f;
        prots = partTwoProt / 4.1f;
        //save the prots goal to the Database
        ref.child("proteins/protGoal").setValue(prots);
        //create other elements for protein for nicer looking table
        ref.child("proteins/protCons").setValue("0");
        ref.child("proteins/protRem").setValue("0");
        //display Proteins
        String protsMsg = Float.toString(prots);
        TextView displayProts = (TextView) findViewById(R.id.displayProts);
        displayProts.setText(protsMsg);


    }

    public void onClickNext(View view) {
        Intent i = new Intent(this, MainScreen.class);
        //pass email as an extra
        i.putExtra("email", email);
        //start activity
        startActivity(i);
    }
}
