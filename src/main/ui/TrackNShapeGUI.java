package ui;

import model.Event;
import model.EventLog;
import model.MealPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/***
 * Class represents TrackNShape GUI
 */
public class TrackNShapeGUI implements ActionListener {

    private static final String JSON_STORE = "./data/MealPlan.json";
    private JFrame app;
    public static final int appWidth = 390;
    public static final int appHeight = 700;

    private MealPlan myMealPlan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private EventLog appLogHistory;

    JPanel logoPanel;
    JPanel functionPanel;
    JPanel fileSaveLoadPanel;

    JButton saveBtn;
    JButton loadBtn;

    JButton addFoodBtn;
    JButton quickAddBtn;
    JButton viewMealPlanBtn;
    JButton removeFoodBtn;
    JButton foodEatenBtn;
    JButton viewMacrosBtn;
    Dimension functionButtonDimensions = new Dimension(170, 70);

    // EFFECTS: runs the TrackNShape gui
    public TrackNShapeGUI() {
        initApp();
        buildAppComponents();
        app.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the gui
    private void initApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myMealPlan = new MealPlan();
        appLogHistory = EventLog.getInstance();

        setAppProperties();
    }

    // MODIFIES: this
    // EFFECTS: sets properties of the gui
    private void setAppProperties() {
        app = new JFrame("Track N Shape");
        app.setSize(appWidth, appHeight);
        app.setResizable(false);
        app.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        app.setLayout(new BorderLayout());
        centreOnScreen();

        /**
         * Citation for addWindowListener feature (line 71-81)
         * Date accessed on: 27 November 2023
         * Source: https://stackoverflow.com/questions/16295942/java-swing-adding-action-listener-for-exit-on-close
         */
        app.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : appLogHistory) {
                    event.logToConsole();
                }
                e.getWindow().dispose();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: centers the gui in the desktop screen
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        app.setLocation((screen.width - app.getWidth()) / 2, (screen.height - app.getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: builds the components of the gui
    private void buildAppComponents() {
        logoPanel = new JPanel();
        functionPanel = new JPanel();
        fileSaveLoadPanel = new JPanel();

        buildLogoPanel();
        buildFunctionPanel();
        buildFileSaveLoadPanel();

        app.add(logoPanel, BorderLayout.NORTH);
        app.add(functionPanel, BorderLayout.CENTER);
        app.add(fileSaveLoadPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds the logo panel to the gui
    private void buildLogoPanel() {
        JLabel imgLabel = new JLabel();

        ImageIcon image = new ImageIcon("./data/image.png");
        Image scaledImg = image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        image.setImage(scaledImg);
        imgLabel.setIcon(image);
        imgLabel.setPreferredSize(new Dimension(appWidth, 150));
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        logoPanel.setBackground(Color.black);
        logoPanel.add(imgLabel);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds the function panel to the gui
    private void buildFunctionPanel() {
        functionPanel.setLayout(new GridLayout(2, 1));
        functionPanel.setBackground(Color.black);

        JLabel functionLabel = new JLabel("TRACK N SHAPE");
        functionLabel.setFont(new Font("DialogInput", Font.PLAIN, 40));
        functionLabel.setForeground(Color.white);
        functionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel funtionButtonPanel = new JPanel();
        funtionButtonPanel.setLayout(new FlowLayout());
        funtionButtonPanel.setBackground(Color.white);

        addEventListenersAndPropertiesToButtons();

        funtionButtonPanel.add(addFoodBtn);
        funtionButtonPanel.add(quickAddBtn);
        funtionButtonPanel.add(viewMealPlanBtn);
        funtionButtonPanel.add(removeFoodBtn);
        funtionButtonPanel.add(foodEatenBtn);
        funtionButtonPanel.add(viewMacrosBtn);

        functionPanel.add(functionLabel);
        functionPanel.add(funtionButtonPanel);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds event listeners to buttons
    private void addEventListenersAndPropertiesToButtons() {
        addFoodBtn = new JButton("Add food");
        addFoodBtn.setPreferredSize(functionButtonDimensions);
        addFoodBtn.addActionListener(this);
        quickAddBtn = new JButton("Quick add");
        quickAddBtn.addActionListener(this);
        quickAddBtn.setPreferredSize(functionButtonDimensions);
        viewMealPlanBtn = new JButton("View meal plan");
        viewMealPlanBtn.addActionListener(this);
        viewMealPlanBtn.setPreferredSize(functionButtonDimensions);
        removeFoodBtn = new JButton("Remove food");
        removeFoodBtn.addActionListener(this);
        removeFoodBtn.setPreferredSize(functionButtonDimensions);
        foodEatenBtn = new JButton("View eat/not eaten");
        foodEatenBtn.addActionListener(this);
        foodEatenBtn.setPreferredSize(functionButtonDimensions);
        viewMacrosBtn = new JButton("View macros");
        viewMacrosBtn.addActionListener(this);
        viewMacrosBtn.setPreferredSize(functionButtonDimensions);
    }

    // MODIFIES: this
    // EFFECTS: builds and adds the save/load button panel to the gui
    void buildFileSaveLoadPanel() {
        fileSaveLoadPanel.setBackground(Color.black);
        fileSaveLoadPanel.setPreferredSize(new Dimension(appWidth, 50));

        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);

        fileSaveLoadPanel.add(saveBtn);
        fileSaveLoadPanel.add(loadBtn);
    }

    @Override
    // MODIFIES: this, myMealPlan
    // EFFECTS: processes clicks on buttons to perform corresponding actions
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFoodBtn) {
            addFood();
        } else if (e.getSource() == quickAddBtn) {
            quickAdd();
        } else if (e.getSource() == viewMealPlanBtn) {
            viewMealPlan();
        } else if (e.getSource() == removeFoodBtn)  {
            removeFood();
        } else if (e.getSource() == foodEatenBtn)  {
            viewEaten();
        } else if (e.getSource() == viewMacrosBtn) {
            viewMacros();
        } else if (e.getSource() == saveBtn) {
            saveApp();
        } else if (e.getSource() == loadBtn) {
            loadApp();
        }
    }

    // MODIFIES: myMealPlan
    // EFFECTS: creates new window where you can add food to meal plan
    void addFood() {
        new AddFoodWindow(myMealPlan);
    }

    void quickAdd() {

    }

    // MODIFIES: myMealPlan
    // EFFECTS: creates new window where you can view meal plan
    void viewMealPlan() {
        new ViewMealPlanWindow(myMealPlan);
    }

    // MODIFIES: myMealPlan
    // EFFECTS: creates new window where you can remove food from meal plan
    void removeFood() {
        new RemoveFoodWindow(myMealPlan);
    }

    void viewEaten() {

    }

    void viewMacros() {

    }

    // EFFECTS: saves the meal plan to file
    void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(myMealPlan);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads meal plan from file
    void loadApp() {
        try {
            myMealPlan = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }










}
