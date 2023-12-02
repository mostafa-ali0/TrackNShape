package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * Citation:
 *      Title: JsonSerializationDemo
 *      Date: 27 October 2023
 *      Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonTest {
    protected void checkFood(String name, int calories, int protein, int carbohydrates
            , int fats, boolean isFoodEaten, Food food) {
        assertEquals(name, food.getName());
        assertEquals(calories, food.getCalories());
        assertEquals(protein, food.getProteins());
        assertEquals(carbohydrates, food.getCarbohydrates());
        assertEquals(fats, food.getFats());
        assertEquals(isFoodEaten, food.isFoodEaten());
    }
}

