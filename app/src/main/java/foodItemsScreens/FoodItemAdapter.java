package foodItemsScreens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jmosk.fypdiary.R;

import java.util.ArrayList;
import java.util.List;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {
    private ArrayList<FoodItem> objects;

    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    public FoodItemAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.objects = (ArrayList<FoodItem>) objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.fooditems_listview, null);

        }

        FoodItem foodItem = objects.get(position);
        ((TextView) convertView.findViewById(R.id.title)).setText(foodItem.getTitle());
        ((TextView) convertView.findViewById(R.id.proteins)).setText(foodItem.getProtein());
        ((TextView) convertView.findViewById(R.id.carbs)).setText(foodItem.getCarbohydrate());
        ((TextView) convertView.findViewById(R.id.fats)).setText(foodItem.getFats());

        return convertView;
    }
}


