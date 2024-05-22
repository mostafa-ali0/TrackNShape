package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
TrackNShape application
 */
public class TrackNShapeApp {

    private Scanner input;
    private MealPlan mealPlan;

    private static final String JSON_STORE = "./data/MealPlan.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: gets the application started
    public TrackNShapeApp() throws FileNotFoundException {
        runApp();
    }

    // EFFECTS: initializes fields of app.
    private void init() {
        input = new Scanner(System.in).useDelimiter("\n");
        mealPlan = new MealPlan();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: takes user input to change and show information
    private void runApp() {
        init();

        while (true) {
            openMenu();
            System.out.print("\n\t\tSelect an option: ");
            String command = input.next().toLowerCase();
            System.out.println();

            if (command.equals("x")) {
                break;
            } else {
                choose(command);
            }
        }

        System.out.println("===========  S E E  Y O U  S O O N!  ===========");
    }

    // MODIFIES: this, diet
    // EFFECTS: chooses user option
    @SuppressWarnings("methodlength")
    private void choose(String command) {
        if (command.equals("0")) {
            quickAddFood();
        } else if (command.equals("1")) {
            addFood();
        } else if (command.equals("2")) {
            viewMealPlan();
        } else if (command.equals("3")) {
            eatFood();
        } else if (command.equals("4")) {
            removeFood();
        } else if (command.equals("5")) {
            checkFoodStatus();
        } else if (command.equals("6")) {
            viewMacros();
        } else if (command.equals("7")) {
            saveMealPlan();
        } else if (command.equals("8")) {
            loadMealPlan();
        } else {
            System.out.println("\tYour input does not match any option!");
        }
    }

    // MODIFIES: this, diet
    // EFFECTS: quickly adds food to meal plan
    private void quickAddFood() {
        System.out.print("\tName: ");
        String name = input.next();

        System.out.print("\tCalories (kcals): ");
        int calories = input.nextInt();

        Food f = new Food(name, calories);
        mealPlan.addFood(f);
        System.out.println("\n\t" + name + " has been added!");
    }

    // MODIFIES: this, diet
    // EFFECTS: adds food to meal plan
    private void addFood() {
        System.out.print("\tName: ");
        String name = input.next();

        System.out.print("\tCalories (kcals): ");
        int calories = input.nextInt();

        System.out.print("\tProtein (g): ");
        int protein = input.nextInt();

        System.out.print("\tCarbohydrates (g): ");
        int carbs = input.nextInt();

        System.out.print("\tFats (g): ");
        int fats = input.nextInt();

        Food f = new Food(name, calories, protein, carbs, fats);
        mealPlan.addFood(f);
        System.out.println("\n\t" + name + " has been added!");
    }

    // EFFECTS: shows all the items in meal plan
    private void viewMealPlan() {
        System.out.println(mealPlan.toString());
    }

    // requires: food to eat exists in meal plan
    // MODIFIES: this, f
    // EFFECTS: changes state to food to eaten
    private void eatFood() {
        viewMealPlan();
        System.out.print("\tEnter name: ");
        String name = input.next();

        Food f = mealPlan.search(name);
        f.eat();
        System.out.println("\tYou've eaten " + name + " ,yum!");
    }

    // MODIFIES: this, diet
    // EFFECTS: removes food from meal plan
    private void removeFood() {
        viewMealPlan();
        System.out.print("\tEnter name: ");
        String name = input.next();
        System.out.println();

        Food f = mealPlan.search(name);
        if (mealPlan.removeFood(f)) {
            System.out.println("\tRemoved food!");
        } else {
            System.out.println("\tFood not found!");
        }
    }

    // EFFECTS: shows all foods status
    private void checkFoodStatus() {
        System.out.print(mealPlan.foodStatus());
    }

    // EFFECTS: shows macros of meal plan
    private void viewMacros() {
        System.out.println(mealPlan.computeNutritionalValues());
    }

    // EFFECTS: prints menu options
    private void openMenu() {
        System.out.println("\n==================  M E N U  ==================");
        System.out.println("\t 0 --> Quick add");
        System.out.println("\t 1 --> +ADD Food");
        System.out.println("\t 2 --> View meal plan");
        System.out.println("\t 3 --> Eat from meal plan");
        System.out.println("\t 4 --> -REMOVE Food");
        System.out.println("\t 5 --> Food eaten/not eaten");
        System.out.println("\t 6 --> Macros Overview");
        System.out.println("\t 7 --> Save Meal Plan to File");
        System.out.println("\t 8 --> Load Meal Plan from File");
        System.out.println("\t x --> EXIT!");
    }

    // EFFECTS: saves the meal plan to file
    private void saveMealPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(mealPlan);
            jsonWriter.close();
            System.out.println("Saved meal plan to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads meal plan from file
    private void loadMealPlan() {
        try {
            mealPlan = jsonReader.read();
            System.out.println("Loaded meal plan from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
