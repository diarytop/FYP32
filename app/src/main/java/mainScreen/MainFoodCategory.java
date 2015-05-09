package mainScreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.example.jmosk.fypdiary.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import foodItemsScreens.Drinks;
import foodItemsScreens.Fruits;
import foodItemsScreens.Meat;
import foodItemsScreens.MyFood;
import foodItemsScreens.Snacks;
import foodItemsScreens.Veg;


public class MainFoodCategory extends Activity{
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_category_screen);
        //connect to the firebase.
        Firebase.setAndroidContext(this);
        //set all the images for this screen taking the data from the database.
        setImages();
        //get the extra (user email from the previous screen (Main screen)
        Bundle emailData = getIntent().getExtras();
        if (emailData == null)
            return;
        email = emailData.getString("email");

    }

    //this method for setting the images that were stored in the database to ImageView
    public void setImages()
    {
        //read the data from the Firebase
        final Firebase refRead = new Firebase("https://fitnessdiary.firebaseio.com/images");
        // Attach an listener to read the data reference
        refRead.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                //fruit image
                //get the stored image from the database
                String fruitImageRead = String.valueOf(snapshot.child("fruit").getValue());
                //convert it back to a bitmap
                byte[] imageAsBytes = Base64.decode(fruitImageRead, Base64.DEFAULT);
                Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                //set it to imageView
                ImageView fruitImgView = (ImageView) findViewById(R.id.fruitsImgView);
                //make it visible
                fruitImgView.setVisibility(View.VISIBLE);
                //image
                fruitImgView.setImageBitmap(bmp);

                //vegetable image
                //get the stored image from the database
                String vegImageRead = String.valueOf(snapshot.child("veg").getValue());
                //convert it back to a bitmap
                byte[] imageAsBytesVeg = Base64.decode(vegImageRead, Base64.DEFAULT);
                Bitmap bmpVeg = BitmapFactory.decodeByteArray(imageAsBytesVeg, 0, imageAsBytesVeg.length);
                //set it to imageView
                ImageView vegImgView = (ImageView) findViewById(R.id.vegImgView);
                //make it visible
                vegImgView.setVisibility(View.VISIBLE);
                //image
                vegImgView.setImageBitmap(bmpVeg);

                //meat image
                //get the stored image from the database
                String meatImageRead = String.valueOf(snapshot.child("meat").getValue());
                //convert it back to a bitmap
                byte[] imageAsBytesMeat = Base64.decode(meatImageRead, Base64.DEFAULT);
                Bitmap bmpMeat = BitmapFactory.decodeByteArray(imageAsBytesMeat, 0, imageAsBytesMeat.length);
                //set it to imageView
                ImageView meatImgView = (ImageView) findViewById(R.id.meatImgView);
                //make it visible
                meatImgView.setVisibility(View.VISIBLE);
                //image
                meatImgView.setImageBitmap(bmpMeat);

                //drinks image
                //get the stored image from the database
                String drinksImageRead = String.valueOf(snapshot.child("drinks").getValue());
                //convert it back to a bitmap
                byte[] imageAsBytesDrinks = Base64.decode(drinksImageRead, Base64.DEFAULT);
                Bitmap bmpDrinks = BitmapFactory.decodeByteArray(imageAsBytesDrinks, 0, imageAsBytesDrinks.length);
                //set it to imageView
                ImageView drinksImgView = (ImageView) findViewById(R.id.drinksImgView);
                //make it visible
                drinksImgView.setVisibility(View.VISIBLE);
                //image
                drinksImgView.setImageBitmap(bmpDrinks);

                //snacks image
                //get the stored image from the database
                String snacksImageRead = String.valueOf(snapshot.child("snacks").getValue());
                //convert it back to a bitmap
                byte[] imageAsBytesSnacks = Base64.decode(snacksImageRead, Base64.DEFAULT);
                Bitmap bmpSnacks = BitmapFactory.decodeByteArray(imageAsBytesSnacks, 0, imageAsBytesSnacks.length);
                //set it to imageView
                ImageView snacksImgView = (ImageView) findViewById(R.id.snacksImgView);
                //make it visible
                snacksImgView.setVisibility(View.VISIBLE);
                //image
                snacksImgView.setImageBitmap(bmpSnacks);

                //my food items image
                //get the stored image from the database
                String myFoodImageRead = String.valueOf(snapshot.child("other").getValue());
                //convert it back to a bitmap
                byte[] imageAsBytesMyFood = Base64.decode(myFoodImageRead, Base64.DEFAULT);
                Bitmap bmpMyFood = BitmapFactory.decodeByteArray(imageAsBytesMyFood, 0, imageAsBytesMyFood.length);
                //set it to imageView
                ImageView myFoodImgView = (ImageView) findViewById(R.id.myFoodImgView);
                //make it visible
                myFoodImgView.setVisibility(View.VISIBLE);
                //image
                myFoodImgView.setImageBitmap(bmpMyFood);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

    }

    /*
    below would be all the methods for the images -> when they would be clicked
    it would take user to appropriate food category screen
     */
    //when fruit image is clicked take user to the Fruit Screen
    public void fruitNext(View view)
    {
        Intent i = new Intent(this, Fruits.class);
        //put email key as extra for the next screen
        i.putExtra("email", email);
        startActivity(i);

    }

    //when vegetables image is clicked take user to the Vegetables Screen
    public void vegNext(View view)
    {
        Intent i = new Intent(this, Veg.class);
        //put email key as extra for the next screen
        i.putExtra("email", email);
        startActivity(i);

    }

    //when meat image is clicked take user to the Meat Screen
    public void meatNext(View view)
    {
        Intent i = new Intent(this, Meat.class);
        //put email key as extra for the next screen
        i.putExtra("email", email);
        startActivity(i);

    }

    //when snacks image is clicked take user to the Snacks Screen
    public void snacksNext(View view)
    {
        Intent i = new Intent(this, Snacks.class);
        //put email key as extra for the next screen
        i.putExtra("email", email);
        startActivity(i);

    }

    //when drinks image is clicked take user to the Drinks Screen
    public void drinksNext(View view)
    {
        Intent i = new Intent(this, Drinks.class);
        //put email key as extra for the next screen
        i.putExtra("email", email);
        startActivity(i);

    }


    //when other image is clicked take user to the MyFood Screen
    public void myFoodNext(View view)
    {
        Intent i = new Intent(this, MyFood.class);
        //put email key as extra for the next screen
        i.putExtra("email", email);
        startActivity(i);

    }

}
