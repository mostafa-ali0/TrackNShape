package ui;

import model.Food;
import model.MealPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Class represents window popup to remove food from meal plan
 */

public class RemoveFoodWindow extends JFrame implements ActionListener {

    JPanel functionPanel;
    JPanel buttonPanel;

    JButton cancel;
    JButton ok;

    JTextField foodNameText;

    MealPlan mealPlan;

    // EFFECTS: opens the window to remove food
    public RemoveFoodWindow(MealPlan mealPlan) {
        initWindow(mealPlan);
        buildWindowComponents();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes window
    private void initWindow(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
        setMinimumSize(new Dimension(390, 400));
        centreOnScreen();
        setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: builds and adds window components
    private void buildWindowComponents() {
        functionPanel = new JPanel();
        buttonPanel = new JPanel();

        buildFunctionPanel();

        JPanel mealPlanPanel = new JPanel();
        mealPlanPanel.setLayout(new GridLayout(4, 1));
        mealPlanPanel.setBackground(Color.green);

        int counter = 1;
        for (Food f : mealPlan.getMealPlan()) {
            JLabel l = new JLabel(counter + " " + f.getName());
            l.setHorizontalAlignment(SwingConstants.CENTER);
            mealPlanPanel.add(l);
            counter++;
        }
        functionPanel.add(mealPlanPanel);

        add(functionPanel, BorderLayout.NORTH);

        ok = new JButton("ok");
        ok.addActionListener(this);
        cancel = new JButton("cancel");
        cancel.addActionListener(this);

        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: builds function panel
    private void buildFunctionPanel() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        JLabel question = new JLabel("which food");
        foodNameText = new JTextField();
        container.add(question, BorderLayout.WEST);
        container.add(foodNameText, BorderLayout.CENTER);
        functionPanel.add(container);
        functionPanel.setLayout(new GridLayout(3, 1));
    }

    // MODIFIES: this
    // EFFECTS: centers window in screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    @Override
    // MODIFIES: this, mealPlan
    // EFFECTS: processes clicks on buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            removeFood();
            this.dispose();
        } else if (e.getSource() == cancel) {
            this.dispose();
        }
    }

    // MODIFIES: this, mealPlan
    // EFFECTS: removes food from meal plan
    void removeFood() {
        Food foodToRemove = mealPlan.search(foodNameText.getText());
        mealPlan.removeFood(foodToRemove);
    }
}
