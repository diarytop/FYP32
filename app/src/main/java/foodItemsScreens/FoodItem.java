package foodItemsScreens;

//class that represents food items in the database
public class FoodItem {

    //attributes that food item contains in the database
     String title;
    String fats;
    String protein;
    String carbohydrate;
    int vitaminA;
    int calories;
     String serving;





    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public FoodItem() { }

    //general class that uses all the items
   public  FoodItem(String title,String fats, String protein, String carbohydrate, int vitaminA, int calories, String serving){
       this.title = title;
        this.fats = fats;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.vitaminA = vitaminA;
        this.calories = calories;
        this.serving = serving;
    }

    //constructer using only the main elements
    public FoodItem(String title, String fats, String protein, String carbohydrate)
    {
        this.title = title;
        this.fats = fats;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
    }

    //get methods for all the properties
    public String getTitle() {
        return title;
    }

    public String getFats() {
        return fats;
    }

    public String getProtein() {
        return protein;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public int getVitaminB() {
        return vitaminA;
    }

    public int getCalories() {
        return calories;
    }

    public String getServing() {
        return serving;
    }




}
