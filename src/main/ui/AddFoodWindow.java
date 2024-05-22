package ui;

import model.Food;
import model.MealPlan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Class represents window to add food to meal plan
 */
public class AddFoodWindow extends JFrame implements ActionListener {

    private JPanel topPanel;
    private JPanel bottomPanel;

    private JButton cancel;
    private JButton ok;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    private MealPlan myMealPlan;

    // MODIFIES: this
    // EFFECTS: opens new window to add food to meal plan
    public AddFoodWindow(MealPlan mealPlan) {
        initWindow(mealPlan);
        buildWindowComponents();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes window
    private void initWindow(MealPlan mealPlan) {
        this.myMealPlan = mealPlan;
        setLayout(new BorderLayout());
        setSize(new Dimension(390, 400));
        centreOnScreen();
    }

    // MODIFIES: this
    // EFFECTS: centers window to desktop screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds window components
    private void buildWindowComponents() {
        topPanel = new JPanel(new GridLayout(5, 2, -100, 10));
        buildTopPanel();

        bottomPanel = new JPanel(new FlowLayout());
        buildBottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds top panel to gui
    private void buildTopPanel() {
        label1 = new JLabel("Name");
        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(10, 40));

        label2 = new JLabel("Protein");
        text2 = new JTextField();
        text2.setPreferredSize(new Dimension(80, 40));

        label3 = new JLabel("carbs");
        text3 = new JTextField();
        text3.setPreferredSize(new Dimension(80, 40));

        label4 = new JLabel("fats");
        text4 = new JTextField();
        text4.setPreferredSize(new Dimension(80, 40));

        label5 = new JLabel("calories");
        text5 = new JTextField();
        text5.setPreferredSize(new Dimension(80, 40));

        addElements();
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to panel
    private void addElements() {
        topPanel.add(label1);
        topPanel.add(text1);
        topPanel.add(label2);
        topPanel.add(text2);
        topPanel.add(label3);
        topPanel.add(text3);
        topPanel.add(label4);
        topPanel.add(text4);
        topPanel.add(label5);
        topPanel.add(text5);
    }

    // MODIFIES: this
    // EFFECTS: builds bottom panel in gui
    private void buildBottomPanel() {
        cancel = new JButton("cancel");
        ok = new JButton("ok");
        cancel.addActionListener(this);
        ok.addActionListener(this);

        bottomPanel.add(cancel);
        bottomPanel.add(ok);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: processes clicks in buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        } else if (e.getSource() == ok) {
            runOk();
            this.dispose();
        }
    }

    // MODIFIES: this, mealPlan
    // EFFECTS: parses input and adds it to meal plan
    void runOk() {
        String name = text1.getText();
        int protein = Integer.parseInt(text2.getText());
        int carbs = Integer.parseInt(text3.getText());
        int fats = Integer.parseInt(text4.getText());
        int calories = Integer.parseInt(text5.getText());

        Food food = new Food(name, calories, protein, carbs, fats);
        myMealPlan.addFood(food);
    }
}
