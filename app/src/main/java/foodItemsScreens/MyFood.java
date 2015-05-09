package foodItemsScreens;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import diaryMain.AddNFIName;

public class MyFood extends Activity {
    ListView listView;
    FoodItemAdapter adapter;
    //to be passed as an extra
    String key;
    //create an extra string to indicate that this is Fruit Screen
    //this would be needed for Food Item Description Screen to connect to appropriate database.
    String category;

    //Map
    Map<String, Object> newPost;
    //
    ArrayList<FoodItem> foodItems;
    String email;

    //array that will hold only key elements
    public ArrayList<String> myFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create an extra string to indicate that this is Fruit Screen
        //this would be needed for Food Item Description Screen to connect to appropriate database.
        category = "myFood";

        foodItems = new ArrayList<FoodItem>();
        myFood = new ArrayList<String>();
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_screen_activity);
        Firebase.setAndroidContext(this);
        //get the image header for this screen
        Firebase imgRef = new Firebase("https://fitnessdiary.firebaseio.com/images/foodScreens");
        // Attach an listener to read the data reference
        imgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                //fruit image
                //get the stored image from the database
                String snacksHeaderImg = String.valueOf(snapshot.child("other").getValue());

                //convert it back to a bitmap
                byte[] snacksHeaderAsBytes = Base64.decode(snacksHeaderImg, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(snacksHeaderAsBytes, 0, snacksHeaderAsBytes.length);
                //set it to imageView
                ImageView imageView = (ImageView) findViewById(R.id.specImageView);
                //make it visible
                imageView.setVisibility(View.VISIBLE);
                //image
                imageView.setImageBitmap(bmp);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Get all the data from fruit database
        Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/users/"+email+"/myFood");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKe) {
                //populate the array for every key value of found in connected database
                String[] foodKeyArray = new String[5];
                for (int i = 0; i < foodKeyArray.length; i++) {
                    foodKeyArray[i] = String.valueOf(snapshot.getKey());
                }

                //add the key values of the fruit database to array
                myFood.add(foodKeyArray[0]);
                //loop of key and it 3 main nutrition values to be added to the list View
                newPost = (Map<String, Object>) snapshot.getValue();
                String keyFood = String.valueOf(snapshot.getKey()); //apple orange grape
                String prots = String.valueOf(newPost.get("protein") + "g");
                String carbs = String.valueOf(newPost.get("carbohydrates") + "g");
                String appleFats = String.valueOf(newPost.get("fats") + "g");
                foodItems.add(new FoodItem(keyFood, prots, carbs, appleFats));
                selectedItem();


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


                     //method that populates the list view and has a on Click Listener to define which element
                     //in th list View has been selected by clickin gon that element
                     public void selectedItem() {

                         listView = (ListView) findViewById(R.id.listView);


                         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             @Override
                             public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                                 int pos = position + 1;
                                  //create new intent
                                 Intent intent = new Intent(getApplicationContext(), FoodItemDescription.class);
                                 //save the clicked key as an extra
                                 key = MyFood.this.myFood.get(pos - 1);
                                 intent.putExtra("key", key);
                                 intent.putExtra("category", category);
                                 startActivity(intent);


                             }

                         });

                         adapter = new FoodItemAdapter(this, android.R.layout.simple_list_item_1, foodItems);

                         listView.setAdapter(adapter);
                     }

    public  void newItem(View view)
    {
        //create new intent
        Intent i = new Intent(this, AddNFIName.class);
        i.putExtra("email",email);
        startActivity(i);
    }

}











