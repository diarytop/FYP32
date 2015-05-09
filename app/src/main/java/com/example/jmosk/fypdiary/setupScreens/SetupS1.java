package com.example.jmosk.fypdiary.setupScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

import mainScreen.MainFoodCategory;
import userLoginScreens.StartLogin;
import userLoginScreens.User;

public class SetupS1 extends ActionBarActivity {

    String bmi;
    String weightUserInput;

    //in order to pass email account to the 5th Screen from user login, we have to pass it
    //thought all the setup screens as an extra
    String email;
    //get user email for saving it in the correct format for future development
    String userEmail;
    //edit text
    EditText weightInput;
    EditText heightInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_s1_activity);
        //get the user email
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        Bundle userEmailData = getIntent().getExtras();
        if (userEmailData == null)
            return;
        userEmail = emailData.getString("userEmail");

        //create user database, as user first time entered the application.
        //if user has selected option no from the Login Success screen
        //if user exists in the database already, then he/she chosen to start over hence -> overwrite the previous progress and data
        //create all the databases using the unique ID
        //connect to it
        Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com");
        Firebase usersRef = ref.child("users");
        //create new user account
        User newUser = new User(userEmail);
        Map<String, User> users = new HashMap<String, User>();
        users.put(email, newUser);
        usersRef.setValue(users);
    }

    public void onClickNext(View view) {

        weightInput = (EditText) findViewById(R.id.weighInput);
        heightInput = (EditText) findViewById(R.id.heighInput);
        //calculate the BMI from user input of height and weight

        if( weightInput.getText().toString().length() == 0  ) {
            weightInput.setError("Enter your weight");

        }
        else if ( heightInput.getText().toString().length() == 0) {
            heightInput.setError("Enter your height");

        }
        else
        {

            calculateBMI();


            //launch new activity
            Intent i = new Intent(this, SetupS2.class);

            //transfer BMI calculation to next activity
            i.putExtra("bmi", bmi);
            //pass weight as extra too;
            i.putExtra("weight", weightUserInput);
            //set the email for extra
            i.putExtra("email", email);
            startActivity(i);
        }
    }

    private void calculateBMI() {

         weightInput = (EditText) findViewById(R.id.weighInput);
        weightUserInput = weightInput.getText().toString();

        heightInput = (EditText) findViewById(R.id.heighInput);
        String heightUserInput = heightInput.getText().toString();
//EditText weightInput = (EditText) findViewById(R.id.weighInput);

            //calculate
            //conver String to a number
            float weight = Float.parseFloat(weightUserInput);
            float height = Float.parseFloat(heightUserInput);
            //calculate the bmi
            float bmiNumber;
            bmiNumber = weight / (height * height);
            //output the result into the string
            bmi = String.valueOf(bmiNumber);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewDatabase(View view)
    {
        // Intent i = new Intent(this, DiaryMain.class);
        // Intent i = new Intent(this, Fruits.class);
        //Intent i = new Intent(this, AddNewFoodItem.class);
        //Intent i = new Intent(this, AddNFIName.class);
        // Intent i = new Intent(this, testDatabase.class);
        // Intent i = new Intent(this, ListViewA.class);
        // Intent i = new Intent(this, DiaryMainScreen.class);
        Intent i = new Intent(this, MainFoodCategory.class);

        //start activity
        startActivity(i);
    }

    public void addItem(View view)
    {
        Intent i = new Intent(this, StartLogin.class);
        startActivity(i);
    }
}
