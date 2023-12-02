package model;


import org.json.JSONObject;
import persistence.Writable;

/**
 * Food class represents a food item having a name, calories,
 * proteins, carbohydrates, fats and status of food (eaten/not eaten).
 */
public class Food implements Writable {

    private String name;
    private int calories;                 // calories in kcals
    private int proteins;                 // proteins in grams
    private int carbohydrates;            // carbohydrates in grams
    private int fats;                     // fats in grams
    private boolean isFoodEaten;


    // REQUIRES: name is non-empty string, calories >= 0, proteins >= 0,
    //           carbohydrates >= 0, fats >= 0
    // EFFECTS: creates a Food instance with name, calories, proteins,
    //          carbohydrates, fats and isFoodEaten = false.
    public Food(String name, int calories, int proteins, int carbohydrates, int fats) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        isFoodEaten = false;
    }

    // REQUIRES: name is non-empty string, calories >= 0
    // EFFECTS: creates a Food instance with name, calories, proteins = 0,
    //          carbohydrates = 0, fats = 0 and isFoodEaten = false.
    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
        proteins = 0;
        carbohydrates = 0;
        fats = 0;
        isFoodEaten = false;
    }

    // MODIFIES: this
    // EFFECTS: changes status of food to eaten
    public void eat() {
        isFoodEaten = true;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getFats() {
        return fats;
    }

    public Boolean isFoodEaten() {
        return isFoodEaten;
    }

    // EFFECTS: returns a string version of Food
    public String toString() {
        return String.format("%s", this.name);
    }

    // EFFECTS: converts the food object to json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calories", calories);
        json.put("protein", proteins);
        json.put("carbohydrates", carbohydrates);
        json.put("fats", fats);

        json.put("eaten", isFoodEaten);

        return json;
    }
}
