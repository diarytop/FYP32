package userLoginScreens;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jmosk.fypdiary.R;
import com.example.jmosk.fypdiary.setupScreens.SetupS1;
import com.firebase.client.Firebase;

import mainScreen.MainScreen;

//this class will ask user if he/she already has setup the goal or not
//if the user will chose option no - all the databases will be generated for him and the first Setup screen will be access to them
//if the user will chose option yes - the main screen will be shown and user will be connected to their own databases as normal
public class LoginSuccess extends Activity{
    //get the user token

    //user name as an extra
    String userEmail;
    String email;
    //save user email in its original format
    String userEmailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);
        Firebase.setAndroidContext(this);

        //get the extra from the previous screen
        //get extra from Setup Screen 2
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        //get user email from their login screen
        userEmail = emailData.getString("email");
        //save user email in its original format
        userEmailData = userEmail;
        //this would be the name of the user specific database, as firebase doesn`t allow special characters.
        email = userEmail.replaceAll("[-+.^:,]", "");


    }



    //select option yes
    public void optionYes(View view){

        //access the main screen
        Intent i = new Intent(this, MainScreen.class);
        i.putExtra("email", email);
        startActivity(i);
    }


    //select option no
    public void optionNo(View view){

        //access the setup screens
        Intent i = new Intent(this, SetupS1.class);
        //pass email as an extra as the user would have to get their databases to set up
        i.putExtra("email", email);
        i.putExtra("userEmail", userEmailData);
        startActivity(i);

    }

}