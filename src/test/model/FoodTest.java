package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    Food burger;
    Food fries;

    @BeforeEach
    void setup() {
        burger = new Food("Burger", 800, 25, 34, 10);
        fries = new Food("Fries", 300);
    }

    @Test
    void testConstructor() {
        assertEquals("Burger", burger.getName());
        assertEquals(800, burger.getCalories());
        assertEquals(25, burger.getProteins());
        assertEquals(34, burger.getCarbohydrates());
        assertEquals(10, burger.getFats());

        assertEquals("Fries", fries.getName());
        assertEquals(300, fries.getCalories());
        assertEquals(0, fries.getProteins());
        assertEquals(0, fries.getCarbohydrates());
        assertEquals(0, fries.getFats());
    }

    @Test
    void testEat() {
        assertFalse(burger.isFoodEaten());

        burger.eat();
        assertTrue(burger.isFoodEaten());
    }

    @Test
    void testToString() {
        assertEquals("Burger", burger.toString());
    }

    @Test
    void testToJson() {
        JSONObject json = burger.toJson();
        assertEquals("{\"carbohydrates\":34,\"fats\":10,\"protein\":25,\"name\":\"Burger\",\"calories\":800," +
                        "\"eaten\":false}"
                , json.toString());
    }
}