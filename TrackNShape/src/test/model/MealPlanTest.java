package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MealPlanTest {

    Food burger;
    Food fries;
    Food donut;
    MealPlan diet;

    @BeforeEach
    void setup() {
        burger = new Food("Burger", 800, 25, 34, 10);
        fries = new Food("Fries", 300);
        donut = new Food ("Donut", 200, 0, 50, 50);
        diet = new MealPlan();
    }

    @Test
    void testConstructor() {
        ArrayList<Food> listOfFood = diet.getMealPlan();
        assertEquals(0, listOfFood.size());
    }

    @Test
    void testAddFoodOnce() {
        diet.addFood(burger);

        ArrayList<Food> listOfFood = diet.getMealPlan();

        assertEquals(burger, listOfFood.get(0));
        assertEquals(1, listOfFood.size());
    }

    @Test
    void testAddFoodMany() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        ArrayList<Food> listOfFood = diet.getMealPlan();

        assertEquals(burger, listOfFood.get(0));
        assertEquals(fries, listOfFood.get(1));
        assertEquals(donut, listOfFood.get(2));
        assertEquals(3, listOfFood.size());
    }

    @Test
    void testRemoveFoodOnce() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        ArrayList<Food> listOfFood = diet.getMealPlan();

        assertTrue(diet.removeFood(burger));
        assertFalse(diet.removeFood(burger));
        assertEquals(2, listOfFood.size());
    }

    @Test
    void testRemoveFoodMany() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        ArrayList<Food> listOfFood = diet.getMealPlan();

        assertTrue(diet.removeFood(burger));
        assertTrue(diet.removeFood(fries));
        assertEquals(1, listOfFood.size());
    }

   @Test
   void testComputeNutritionalValues() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        assertEquals("\t\t\tMacros Overview\n\tCalories: 1300kcals \n" +
                "\tProtein: 25g \n" +
                "\tCarbohydrates: 84g \n" +
                "\tFats: 60g", diet.computeNutritionalValues());
   }

    @Test
    void testFoodStatus() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        burger.eat();
        fries.eat();

        assertEquals("\tFood Eaten\n" +
                "\t1. Burger\n\t2. Fries\n" +
                "\n\tFood Not Eaten\n" +
                "\t1. Donut\n", diet.foodStatus());
    }

    @Test
    void testSearch() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        assertEquals(fries, diet.search("Fries"));
        assertNull(diet.search("random"));
    }

    @Test
    void testToString() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        assertEquals("\tMeal Plan\n" +
                "\t1. Burger\n" +
                "\t2. Fries\n" +
                "\t3. Donut\n", diet.toString());
    }


    @Test
    void testToJson() {
        diet.addFood(burger);
        diet.addFood(fries);
        diet.addFood(donut);

        JSONObject json = diet.toJson();

        assertEquals("{\"Meal Plan\":[{\"carbohydrates\":34,\"fats\":10,\"protein\":25,\"name\":\"Burger\"," +
                        "\"calories\":800,\"eaten\":false},{\"carbohydrates\":0,\"fats\":0,\"protein\":0,\"name\":\"" +
                        "Fries\",\"calories\":300,\"eaten\":false},{\"carbohydrates\":50,\"fats\":50,\"protein\":0,\"" +
                        "name\":\"Donut\",\"calories\":200,\"eaten\":false}]}"
                , json.toString());
    }

}
