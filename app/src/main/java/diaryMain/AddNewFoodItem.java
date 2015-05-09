package diaryMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.Firebase;

import mainScreen.MainFoodCategory;


public class AddNewFoodItem extends Activity {
    //extras
    String foodKey;
    String category;

    //strings of user input edit text
    String calories;
    String fat;
    String carbs;
    String protein;
    String chol;
    String sodium;
    String vitaminA;
    String vitaminC;
    String servSize;
    //get user email
    String email;

    //mandatory fields
    TextView enterText1;
    TextView enterText2;
    TextView enterText3;
    TextView enterText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_fi_activity);
        Firebase.setAndroidContext(this);
        //get email extra
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        //get the extras from the previous screens
        //to accept extra information
        Bundle foodKeyData = getIntent().getExtras();
        //in case there is no data
        if (foodKeyData == null)
            return;
        //get food key
        foodKey = foodKeyData.getString("foodKey");
        //set the food Key as the title of this screen
        TextView AddNFITitle = (TextView) findViewById(R.id.foodKeyDescription);
        AddNFITitle.setText(foodKey);

        //get the category extra
        Bundle categoryData = getIntent().getExtras();
        //in case there is no data
        if (categoryData == null)
            return;
        //get the category
        category = categoryData.getString("category");

    }

    //method to get all fields into database
    public void getUserInput() {
        //get user input of calories and make it a String
        enterText1 = (TextView) findViewById(R.id.enterText1);
        //declare text Views first and check if they are not empty
        //they are mandatory fields to enter
        enterText2 = (TextView) findViewById(R.id.enterText2);
        enterText3 = (TextView) findViewById(R.id.enterText3);
        enterText4 = (TextView) findViewById(R.id.enterText4);

//        if(enterText1.getText().toString().length() == 0 )
//        {
//            enterText1.setError("Enter food calories");
//
//        }else if(enterText2.getText().toString().length() == 0 )
//        {
//            enterText2.setError("Enter food fat index");
//
//        }else if(enterText3.getText().toString().length() == 0)
//        {
//            enterText3.setError("Enter food carbohydrates value");
//
//        }else if(enterText4.getText().toString().length() == 0 )
//        {
//            enterText4.setError("Enter food protein value");
//
//        }else {
            //if the mandatory fields are not empty, save the values
            calories = String.valueOf(enterText1.getText());
            //get user input of fats
            fat = String.valueOf(enterText2.getText());
            //get user input of carbs
            carbs = String.valueOf(enterText3.getText());
            //get user input of protein
            protein = String.valueOf(enterText4.getText());
            //get user input of cholesterol
            TextView enterText5 = (TextView) findViewById(R.id.enterText5);
            chol = String.valueOf(enterText5.getText());
            //get user input of sodium
            TextView enterText6 = (TextView) findViewById(R.id.enterText6);
            sodium = String.valueOf(enterText6.getText());
            //get user input of vitamin A
            TextView enterText7 = (TextView) findViewById(R.id.enterText7);
            vitaminA = String.valueOf(enterText7.getText());
            //get user input of vitamin C
            TextView enterText8 = (TextView) findViewById(R.id.enterText8);
            vitaminC = String.valueOf(enterText8.getText());
            //get user input of serving size
            TextView enterText9 = (TextView) findViewById(R.id.enterText9);
            servSize = String.valueOf(enterText9.getText());




    }

    //connect to fruit database
    public void addItemToUserDatabase() {
        //create a reference to the database
        final Firebase refFruit = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/myFood");
        refFruit.child(foodKey).setValue(foodKey);
        refFruit.child(foodKey).child("calories").setValue(calories);
        refFruit.child(foodKey).child("fats").setValue(fat);
        refFruit.child(foodKey).child("protein").setValue(protein);
        refFruit.child(foodKey).child("carbohydrates").setValue(carbs);
        refFruit.child(foodKey).child("cholesterol").setValue(chol);
        refFruit.child(foodKey).child("sodium").setValue(calories);
        refFruit.child(foodKey).child("vitaminA").setValue(vitaminA);
        refFruit.child(foodKey).child("vitaminC").setValue(vitaminC);
        refFruit.child(foodKey).child("zserving").setValue(servSize);

    }

    //when user saves the item it returns back to Main Food category screen
       public void saveTheData(View view) {
           //get user input of calories and make it a String
           enterText1 = (TextView) findViewById(R.id.enterText1);
           //declare text Views first and check if they are not empty
           //they are mandatory fields to enter
           enterText2 = (TextView) findViewById(R.id.enterText2);
           enterText3 = (TextView) findViewById(R.id.enterText3);
           enterText4 = (TextView) findViewById(R.id.enterText4);

           if(enterText1.getText().toString().length() == 0 )
           {
               enterText1.setError("Enter food calories");

           }else if(enterText2.getText().toString().length() == 0 )
           {
               enterText2.setError("Enter food fat index");

           }else if(enterText3.getText().toString().length() == 0)
           {
               enterText3.setError("Enter food carbohydrates value");

           }else if(enterText4.getText().toString().length() == 0 )
           {
               enterText4.setError("Enter food protein value");

           }else {
               //get user input
               getUserInput();
               //save to database
               addItemToUserDatabase();
               //create an Intent
               Intent i = new Intent(this, MainFoodCategory.class);
               Toast.makeText(getApplicationContext(), foodKey + " has been successfully saved", Toast.LENGTH_LONG).show();
               i.putExtra("email", email);
               //launch the activity
               startActivity(i);
           }
    }


}
















