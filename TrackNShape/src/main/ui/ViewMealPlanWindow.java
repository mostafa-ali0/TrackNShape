package ui;

import model.Food;
import model.MealPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Class representing window to view meal plan
 */

public class ViewMealPlanWindow extends JFrame implements ActionListener {

    MealPlan mealPlan;

    JButton ok;

    JPanel topPanel;
    JPanel bottomPanel;

    // EFFECTS: runs the window to view meal plan
    public ViewMealPlanWindow(MealPlan mealPlan) {
        initWindow(mealPlan);
        buildWindowComponents();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes window
    private void initWindow(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
        setLayout(new GridLayout());
        setMinimumSize(new Dimension(800, 500));
    }

    // MODIFIES: this
    // EFFECTS: builds and add components to gui
    private void buildWindowComponents() {
        topPanel = new JPanel(new FlowLayout());
        buildTopPanel();

        bottomPanel = new JPanel(new FlowLayout());
        addBottomButtons();

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds top panel to gui
    private void buildTopPanel() {
        for (Food f : mealPlan.getMealPlan()) {
            JPanel mealPlanPanel = new JPanel();

            JLabel name = new JLabel("name: " + f.getName());
            JLabel calories = new JLabel("calories: " + f.getCalories());
            JLabel pro = new JLabel("protein: " + f.getProteins());
            JLabel carbs = new JLabel("carbs: " + f.getCarbohydrates());
            JLabel fats = new JLabel("fats: " + f.getFats());

            mealPlanPanel.add(name);
            mealPlanPanel.add(calories);
            mealPlanPanel.add(pro);
            mealPlanPanel.add(carbs);
            mealPlanPanel.add(fats);

            topPanel.add(mealPlanPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: builds and adds buttons to gui
    private void addBottomButtons() {
        ok = new JButton("ok");
        ok.addActionListener(this);

        bottomPanel.add(ok);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: processes clicks on buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            this.dispose();
        }
    }
}
