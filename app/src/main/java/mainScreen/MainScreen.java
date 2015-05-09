package mainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainScreen extends ActionBarActivity {
    //get user email
    String email;

    //minimum and maximum calories
    TextView minimumCalories;
    TextView caloriesCount;

    //all the textfields in the table of prots, fat,carbs
    //proteins
    TextView protsGoal;
    TextView protsCons;
    TextView protsRem;
    //carbohydrates
    TextView carbsGoal;
    TextView carbsCons;
    TextView carbsRem;
    //fats
    TextView fatsGoal;
    TextView fatsCons;
    TextView fatsRem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_act);
        //connect to the Firebase API
        Firebase.setAndroidContext(this);
        //fetch user email to access their database
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        try {

            //create a reference to the database
            Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/calculations");
            // Attach an listener to read the data reference
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot snapshot) {
                    //display minimum and maximum calories from the database
                    //minimum
                    minimumCalories = (TextView) findViewById(R.id.minimumCalories);
                    minimumCalories.setText(String.valueOf(snapshot.child("calories").child("minCalories").getValue()));
                    //maximum
                    caloriesCount = (TextView) findViewById(R.id.caloriesCount);
                    caloriesCount.setText(String.valueOf(snapshot.child("calories").child("maxCalories").getValue()));
                    //proteins
                    protsGoal = (TextView) findViewById(R.id.protsGoal); //goal
                    protsGoal.setText(String.valueOf(snapshot.child("proteins").child("protGoal").getValue()) + "g");
                    protsCons = (TextView) findViewById(R.id.protsCons); //consumed
                    protsCons.setText(String.valueOf(snapshot.child("proteins").child("protCons").getValue()) + "g");
                    protsRem = (TextView) findViewById(R.id.protsRem); //remaining
                    protsRem.setText(String.valueOf(snapshot.child("proteins").child("protRem").getValue()) + "g");
                    //carbs
                    carbsGoal = (TextView) findViewById(R.id.carbsGoal); //goal
                    carbsGoal.setText(String.valueOf(snapshot.child("carbohydrates").child("carbsGoal").getValue()) + "g");
                    carbsCons = (TextView) findViewById(R.id.carbsCons); //consumed
                    carbsCons.setText(String.valueOf(snapshot.child("carbohydrates").child("carbsCons").getValue()) + "g");
                    carbsRem = (TextView) findViewById(R.id.carbsRem); //remaining
                    carbsRem.setText(String.valueOf(snapshot.child("carbohydrates").child("carbsRem").getValue()) + "g");
                    //fats
                    fatsGoal = (TextView) findViewById(R.id.fatGoal); //goal
                    fatsGoal.setText(String.valueOf(snapshot.child("fats").child("fatGoal").getValue()) + "g");
                    fatsCons = (TextView) findViewById(R.id.fatCons); // consumed
                    fatsCons.setText(String.valueOf(snapshot.child("fats").child("fatCons").getValue()) + "g");
                    fatsRem = (TextView) findViewById(R.id.fatRem); // remaining
                    fatsRem.setText(String.valueOf(snapshot.child("fats").child("fatRem").getValue()) + "g");
                }


                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }


            });






        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException);
        }

    }

    public void addToDiary(View view)
    {
        Intent i = new Intent(this, MainFoodCategory.class);
        i.putExtra("email",email);
        startActivity(i);

    }
}
