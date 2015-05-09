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
import android.widget.Toast;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import diaryMain.AddNFIName;

public class Fruits extends Activity {
    ListView listView;
    FoodItemAdapter adapter;
    //to be passed as an extra
    String key;
    //create an extra string to indicate that this is Fruit Screen
    //this would be needed for Food Item Description Screen to connect to appropriate database.
    String category;
    //user email
    String email;

    //Map
    Map<String, Object> newPost;
    //
    ArrayList<FoodItem> foodItems;

    //array that will hold only key elements
    public ArrayList<String> fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create an extra string to indicate that this is Fruit Screen
        //this would be needed for Food Item Description Screen to connect to appropriate database.
        category = "fruit";

        foodItems = new ArrayList<FoodItem>();
        fruit = new ArrayList<String>();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fruit_screen_activity);
        Firebase.setAndroidContext(this);
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");
        //get the image header for this screen
        Firebase imgRef = new Firebase("https://fitnessdiary.firebaseio.com/images/foodScreens");
        // Attach an listener to read the data reference
        imgRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                //fruit image
                //get the stored image from the database
                String fruitHeaderImg = String.valueOf(snapshot.child("fruit").getValue());
                System.out.println(String.valueOf(snapshot.child("fruit").getValue()));
                //convert it back to a bitmap
                byte[] fruitHeaderAsBytes = Base64.decode(fruitHeaderImg, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(fruitHeaderAsBytes, 0, fruitHeaderAsBytes.length);
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
        Firebase ref = new Firebase("https://fitnessdiary.firebaseio.com/fruits");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKe) {
                //populate the array for every key value of found in connected database
                String[] foodKeyArray = new String[5];
                for (int i = 0; i < foodKeyArray.length; i++) {
                    foodKeyArray[i] = String.valueOf(snapshot.getKey());
                }

                //add the key values of the fruit database to array
                fruit.add(foodKeyArray[0]);
                //loop of key and it 3 main nutrition values to be added to the list View
                newPost = (Map<String, Object>) snapshot.getValue();
                String keyFood = String.valueOf(snapshot.getKey()); //apple orange grape
                String prots = String.valueOf(newPost.get("protein") + "g");
                String carbs = String.valueOf(newPost.get("carbohydrates") + "g");
                String fats = String.valueOf(newPost.get("fats") + "g");
                foodItems.add(new FoodItem(keyFood, prots, carbs, fats));
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
                                 //display in toast the position of the elemeent
                                 Toast.makeText(Fruits.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
                                 //create new intent
                                 Intent intent = new Intent(getApplicationContext(), FoodItemDescription.class);
                                 //for the element that has been clicked find the corresponding key value in the array
                                 for (String s : Fruits.this.fruit) {
                                     //print out the array
                                     System.out.println("Fruit array" + s);
                                 }

                                 //print out the key in which it has been clicked
                                 System.out.println("CLICKED ON " + Fruits.this.fruit.get(pos - 1));
                                 //save the clicked key as an extra
                                 key = Fruits.this.fruit.get(pos - 1);

                                 intent.putExtra("key", key);
                                 intent.putExtra("category", category);
                                 intent.putExtra("email", email);

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
        i.putExtra("email", email);
        startActivity(i);
    }


}













