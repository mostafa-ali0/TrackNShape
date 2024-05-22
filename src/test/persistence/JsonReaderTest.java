package persistence;

import model.Food;
import model.MealPlan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/***
 * Citation:
 *      Title: JsonSerializationDemo
 *      Date: 27 October 2023
 *      Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/emptyDir.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMealPlan() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMealPlan.json");
        try {
            MealPlan mp = reader.read();
            assertEquals(0, mp.getMealPlan().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMealPlan() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMealPlan.json");
        try {
            MealPlan mp = reader.read();
            ArrayList<Food> foods = mp.getMealPlan();
            assertEquals(3, foods.size());
            checkFood("Burger", 800, 0, 0, 0, false,  foods.get(0));
            checkFood("Fries", 250, 12, 10, 14, true, foods.get(1));
            checkFood("Ice cream", 450, 29, 9, 12, false,
                    foods.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
