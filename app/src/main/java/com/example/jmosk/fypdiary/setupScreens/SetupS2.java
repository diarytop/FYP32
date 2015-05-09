package com.example.jmosk.fypdiary.setupScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jmosk.fypdiary.R;


public class SetupS2 extends ActionBarActivity {

    String weight;
    float minimum;
    String bmiNumberMessage;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_s2_activity);
        //get email extra
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");

        //to accept extra information
        Bundle bmiData = getIntent().getExtras();
        //in case there is no text
        if (bmiData == null)
            return;

        //get BMI result
        bmiNumberMessage = bmiData.getString("bmi");
        TextView showMeBMI = (TextView) findViewById(R.id.showMeBMI);
        showMeBMI.setText(bmiNumberMessage);

        //receive weight as extra
        Bundle weightData = getIntent().getExtras();
        weight = weightData.getString("weight");
        calculateMinimumIntake();
        //display as test
        //the formula is working and the minimum calories calculated correctly.
        TextView minimumTest = (TextView) findViewById(R.id.minimumTest);
        String minimumString = Float.toString(minimum);
        minimumTest.setText(minimumString);

    }

    private void calculateMinimumIntake() {

        //make weight in number;
        float weightNumber = Float.parseFloat(weight);
        //make bmi in number
        float bmiNumber = Float.parseFloat(bmiNumberMessage);
        float partTwo = 100 - bmiNumber;
        float y = weightNumber * partTwo / 100;

        minimum = 370 + (21 * y);


    }

    public void onClickNext2(View view) {
        //launch new activity
        Intent i = new Intent(this, SetupS3.class);
        //transfer minimum intake to the next activity
        i.putExtra("minimum", minimum);
        //pass email as an extra to a next screen
        i.putExtra("email",email);
        //start activity
        startActivity(i);
    }
}
