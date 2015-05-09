package diaryMain;


public class DairyFoodItem {
    //attributes that food item contains in the database
    String title;
    String calories;
    String serving;





    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public DairyFoodItem() { }

    public String getCalories() {
        return calories;
    }

    public String getServing() {
        return serving;
    }

    public String getTitle() {
        return title;
    }

    //general class that uses all the items
    public  DairyFoodItem(String title,String calories, String serving){
        this.title = title;
        this.calories = calories;
        this.serving = serving;
    }
}
