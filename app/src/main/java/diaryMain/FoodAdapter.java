package diaryMain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jmosk.fypdiary.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends ArrayAdapter<DairyFoodItem>{
    private ArrayList<DairyFoodItem> objects;

    LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    public FoodAdapter(Context context, int resource, List objects)
    {
        super(context, resource, objects);
        this.objects = (ArrayList<DairyFoodItem>) objects;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.dairy_item_view, null);
        }

        DairyFoodItem dairyFoodItem = objects.get(position);

        ((TextView)convertView.findViewById(R.id.nameTextField)).setText(dairyFoodItem.getTitle());
        ((TextView)convertView.findViewById(R.id.nameTextField1)).setText(dairyFoodItem.getCalories());
        ((TextView)convertView.findViewById(R.id.nameTextField2)).setText(dairyFoodItem.getServing());
        ((TextView)convertView.findViewById(R.id.nameTextField3)).setText("");

        return convertView;
    }

}
