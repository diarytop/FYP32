package com.example.jmosk.fypdiary.setupScreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmosk.fypdiary.R;


public class SetupS3 extends ActionBarActivity {

    float minimum;
    float maximum;
    TextView testMinimum;
    RadioGroup group;
    String email;
    int checkedId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_s3_activity);
        //get email from Screen 2
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");

        //get extra from Setup Screen 2
        Bundle minimumData = getIntent().getExtras();
        //in case there is no text
        if (minimumData == null)
            return;
        //get minimum intake calculation result from screen 2
        float minimumMessage = minimumData.getFloat("minimum");
        minimum = minimumMessage;


        //Declare the RadioButton Group
        group = (RadioGroup) findViewById(R.id.optionSelection);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //find which radio button is selected
                if(checkedId == R.id.radioOption1)
                {
                    //do the calculation
                    maximum = minimum * 1.2f;
                    //display it in the Toast message
                    String msg = Float.toString(maximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                } else if(checkedId == R.id.radioOption2)
                {
                    //do the calculation
                    maximum = minimum * 1.375f;
                    //display it in the Toast message
                    String msg = Float.toString(maximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioOption3)
                {
                    //do the calculation
                    maximum = minimum * 1.375f;
                    //display it in the Toast message
                    String msg = Float.toString(maximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioOption4)
                {
                    //do the calculation
                    maximum = minimum * 1.55f;
                    //display it in the Toast message
                    String msg = Float.toString(maximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.radioOption5)
                {
                    //do the calculation
                    maximum = minimum * 1.75f;
                    //display it in the Toast message
                    String msg = Float.toString(maximum);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
                 else
                {

                }
            }
        });

    }

    public void checkIfSelected()
    {

    }

    public void onClickNext(View view) {

        Intent i = new Intent(this, SetupS4.class);
        //transfer maximum  calculation to the next activity
        i.putExtra("maximum", maximum);
        //transfer minimum calculation to the next activity so it could be passed to final screen
        i.putExtra("minimum", minimum);
        //pas email as an extra
        i.putExtra("email", email);
        //start activity
        startActivity(i);
    }
}
