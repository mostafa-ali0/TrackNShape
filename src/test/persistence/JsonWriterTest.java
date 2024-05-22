package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Citation:
 *      Title: JsonSerializationDemo
 *      Date: 27 October 2023
 *      Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMealPlan() {
        try {
            MealPlan mealPlan = new MealPlan();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMealPlan.json");
            writer.open();
            writer.write(mealPlan);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMealPlan.json");
            mealPlan = reader.read();
            assertEquals(0, mealPlan.getMealPlan().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMealPlan() {
        try {
            MealPlan mealPlan = new MealPlan();
            mealPlan.addFood(new Food("Pizza", 1000));

            Food f1 = new Food("Pineapple", 20, 0, 3, 1);
            f1.eat();
            mealPlan.addFood(f1);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealPlan.json");
            writer.open();
            writer.write(mealPlan);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealPlan.json");
            mealPlan = reader.read();
            ArrayList<Food> foods = mealPlan.getMealPlan();
            assertEquals(2, foods.size());

            checkFood("Pizza", 1000, 0, 0, 0, false, foods.get(0));
            checkFood("Pineapple", 20, 0, 3, 1, true, foods.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
