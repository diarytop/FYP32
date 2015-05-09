package com.example.jmosk.fypdiary.setupScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmosk.fypdiary.R;


public class SetupS4 extends ActionBarActivity {

    //new maximum calorie intake after calculations
    float maximum;
    //maximum for further calculations
    float newMaximum;
    //minimum from the previous screen
    float minimum;
    //declare radio group
    RadioGroup group;
    //email
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_s4_activity);
        //get email from screen 3
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        //get extra from Setup Screen 3
        Bundle maximumData = getIntent().getExtras();
        //in case there is no text
        if (maximumData == null)
            return;
        //get maximum intake calculation result
        maximum = maximumData.getFloat("maximum");
        newMaximum = maximum;
        //display old maximum from Screen 3 as test
        TextView extraMaxTest = (TextView) findViewById(R.id.extraMaxTest);
        String maximumTest = Float.toString(maximum);
        extraMaxTest.setText(maximumTest);


        //get minimum from screen 3
        Bundle minimumData = getIntent().getExtras();
        //in case there is no text
        if(minimumData == null)
            return;
        //get minimum intake calculation result
        minimum = minimumData.getFloat("minimum");
        TextView extraMinTest = (TextView) findViewById(R.id.extraMinTest);
        String minMsg = Float.toString(minimum);
        extraMinTest.setText(minMsg);


        //Declare the RadioButton Group
        group = (RadioGroup) findViewById(R.id.goalSelection);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //find which radio button is selected
                if(checkedId == R.id.radioGoal1)
                {
                    //do the calculation
                    //second part of calculation
                    float secondPart = maximum * 0.2f;
                    newMaximum = maximum - secondPart;
                    //display it in the Toast message
                    String msg = Float.toString(newMaximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.radioGoal2)
                {
                    //do the calculation
                    //second part of calculation
                    float secondPart = maximum * 0.1f;
                    newMaximum = maximum - secondPart;
                    //display it in the Toast message
                    String msg = Float.toString(newMaximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioGoal3)
                {
                    //do the calculation
                    //do nothing
                    //display it in the Toast message
                    String msg = Float.toString(newMaximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioGoal4)
                {
                    //do the calculation
                    //second part of calculation
                    float secondPart = maximum * 0.1f;
                    newMaximum = maximum + secondPart;
                    //display it in the Toast message
                    String msg = Float.toString(newMaximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }else
                {
                    //do the calculation
                    //second part of calculation
                    float secondPart = maximum * 0.2f;
                    newMaximum = maximum + secondPart;
                    //display it in the Toast message
                    String msg = Float.toString(newMaximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void onClickNext(View view) {
        Intent i = new Intent(this, SetupS5.class);
        //transfer maximum  calculation to the next activity
        i.putExtra("maximum", newMaximum);
        //transfer minimum calculation to the next activity so it could be passed to final screen
        i.putExtra("minimum", minimum);
        //pass email as an extra
        i.putExtra("email", email);
        //start activity
        startActivity(i);
    }
}