package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

/**
 * MealPlan class represents all the food items in a meal plan
 */
public class MealPlan implements Writable {

    private ArrayList<Food> foods;
    private EventLog eventLog;

    // EFFECTS: makes an empty meal plan
    public MealPlan() {
        foods = new ArrayList<>();
        eventLog = EventLog.getInstance();
    }

    // MODIFIES: this
    // EFFECTS: adds food item to meal plan
    public void addFood(Food f) {
        foods.add(f);
        eventLog.logEvent(new Event(f.getName() + " added to meal plan"));
    }

    // MODIFIES: this
    // EFFECTS: removes food item to meal plan
    public boolean removeFood(Food f) {
        eventLog.logEvent(new Event(f.getName() + " removed from meal plan"));
        return foods.remove(f);
    }

    // EFFECTS: returns information of the macronutrients
    public String computeNutritionalValues() {
        int calories = 0;
        int proteins = 0;
        int carbohydrates = 0;
        int fats = 0;

        for (Food f : foods) {
            calories += f.getCalories();
            proteins += f.getProteins();
            carbohydrates += f.getCarbohydrates();
            fats += f.getFats();
        }

        return String.format("\t\t\tMacros Overview\n"
                         + "\tCalories: %dkcals \n\tProtein: %dg \n\tCarbohydrates: %dg \n\tFats: %dg",
                calories, proteins, carbohydrates, fats);
    }


    // EFFECTS: returns information about status of food in meal plan
    public String foodStatus() {
        StringBuilder eatenString = new StringBuilder("\tFood Eaten\n");
        StringBuilder notEatenString = new StringBuilder("\n\tFood Not Eaten\n");
        int serial1 = 1;
        int serial2 = 1;
        for (Food f : foods) {
            if (f.isFoodEaten()) {
                eatenString.append("\t").append(serial1).append(". ").append(f.getName()).append("\n");
                serial1++;
            } else {
                notEatenString.append("\t").append(serial2).append(". ").append(f.getName()).append("\n");
                serial2++;
            }
        }

        return eatenString.append(notEatenString).toString();
    }

    // REQUIRES: non-empty string as name
    // EFFECTS: returns the first occurrence of food that matches the name.
    public Food search(String name) {
        for (Food f : foods) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    // EFFECTS: returns the whole meal plan
    public ArrayList<Food> getMealPlan() {
        return foods;
    }

    // EFFECTS: returns all the food items in the meal plan in string
    public String toString() {
        StringBuilder output = new StringBuilder("\tMeal Plan\n");
        int serial = 1;
        for (Food f : foods) {
            output.append("\t" + serial + ". ").append(f.toString()).append("\n");
            serial++;
        }

        return output.toString();
    }

    // EFFECTS: return JSON object from meal plan
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Meal Plan", foodsToJson());
        return json;
    }

    // EFFECTS: returns things in the meal plan as JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foods) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
