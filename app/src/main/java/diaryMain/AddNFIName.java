package diaryMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.Firebase;


public class AddNFIName extends Activity {
    String categoryType;
    EditText editText;
    String foodKey;
    //get user email as extra
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nfi_title);
        //get extra
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        //connect to the firebase
        Firebase.setAndroidContext(this);
        //get a category as a flag to connect to appropriate database on the next screen
        //both of them should be passed as extra to the next screen
        //Declare the RadioButton Group
        RadioGroup group = (RadioGroup) findViewById(R.id.category);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //find which radio button is selected
                if (checkedId == R.id.category1) {
                    categoryType = "fruits";
                    //display it in the Toast message
                    String msg = categoryType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.category2) {
                    categoryType = "meat";
                    //display it in the Toast message
                    String msg = categoryType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.category3) {
                    categoryType = "vegetables";
                    //display it in the Toast message
                    String msg = categoryType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.category4) {
                    categoryType = "drinks";
                    //display it in the Toast message
                    String msg = categoryType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.category5) {
                    categoryType = "snacks";
                    //display it in the Toast message
                    String msg = categoryType;
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                }else
                {
                    categoryType = "other";

                }
            }
        });


    }

        public void saveNext(View view)
        {
            //take a name as a key
            editText = (EditText) findViewById(R.id.editText);

            foodKey = String.valueOf(editText.getText());
            if( editText.getText().toString().length() == 0  ) {
                editText.setError("Enter new food name");
                // EditText heightInput = (EditText) findViewById(R.id.heighInput);

            }else {
                //create an intent
                Intent i = new Intent(this, AddNewFoodItem.class);
                //pass the food key value and category type as extras
                i.putExtra("foodKey", foodKey);
                i.putExtra("category", categoryType);
                i.putExtra("email", email);
                //generate the request to the admin db for future adding of the missing item
                sendRequestToAdmin();
                //launch new activity
                startActivity(i);
            }

        }


        public void sendRequestToAdmin()
        {
            Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/requests");
            ref.child(foodKey).setValue(categoryType);
        }



}
