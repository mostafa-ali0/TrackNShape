package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/***
 * Citation:
 *      Title: JsonSerializationDemo
 *      Date: 27 October 2023
 *      Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

// Represents a reader that reads meal plan from JSON data stored in file
public class JsonReader {
    private String sourceDir;

    // EFFECTS: constructs Json reader to read from source file
    public JsonReader(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    // EFFECTS: reads mael plan from file and returns it
    // throws IOException if an error occurs reading data from file
    public MealPlan read() throws IOException {
        String jsonToString = readFile(sourceDir);
        JSONObject json = new JSONObject(jsonToString);
        return parse(json);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String sourceDir) throws IOException {
        StringBuilder fileText = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(sourceDir), StandardCharsets.UTF_8)) {
            stream.forEach(s -> fileText.append(s));
        }

        return fileText.toString();
    }

    // EFFECTS: parses meal plan from JSON object and returns it
    private MealPlan parse(JSONObject json) {
        MealPlan mp = new MealPlan();
        addFoods(mp, json);
        return mp;
    }

    // MODIFIES: mp
    // EFFECTS: parses Food from JSON object and adds them to meal plan
    private void addFoods(MealPlan mp, JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("Meal Plan");
        for (Object jsonObj : jsonArray) {
            JSONObject nextFood = (JSONObject) jsonObj;
            addFood(mp, nextFood);
        }
    }

    // MODIFIES: mp
    // EFFECTS: parses food from JSON object and adds it to meal plan
    private void addFood(MealPlan mealPlan, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calories = jsonObject.getInt("calories");
        int protein = jsonObject.getInt("protein");
        int carbs = jsonObject.getInt("carbohydrates");
        int fats = jsonObject.getInt("fats");

        boolean isEaten = jsonObject.getBoolean("eaten");

        Food food = new Food(name, calories, protein, carbs, fats);
        if (isEaten) {
            food.eat();
        }
        mealPlan.addFood(food);
    }
}
