package ui;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import model.Fish;
import model.Tank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


// SOURCE: JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// SOURCE: PaddleBallFrame from lab3 (cloned from VCS)
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab3_m6g6q/commit/8b37f9b6e072a28e6e962ec68cd148c6e767ec12

// SOURCE: SpaceInvadersStarter (download from edX)
// https://edge.edx.org/assets/courseware/v1/13595daba554c85e2fe669e686cbff91/
// asset-v1:UBC+CPSC210+all+type@asset+block/SpaceInvadersStarter.zip


// This class manages all GUI interactions for the rest of the classes
public class TankAppGUI extends JFrame {
    private static final int INTERVAL = 40;
    private static final int WIDTH = 750;
    private static final int HEIGHT = 400;

    // SOURCE: JsonSerializationDemo
    private static final String JSON_PATH = "./data/tank.json";
    private TankPanel tankPanel;
    private JPanel menuArea;
    private Tank tank;

    JTextField fishNameField;
    JTextField fishColorField;
    JTextField fishSpeciesField;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes all necessary objects and delegates Swing tasks
    public TankAppGUI() {
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
        startFrame();

        tank = new Tank();
        attemptAutoLoad();
        startTankPanel();
        startMenuPanel();
        this.pack();
        this.centreOnScreen();
        addTimer();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: starts a timer that constantly updates the tank and draws it onto the GUI
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tankPanel.repaint();
                tank.update();
            }
        });
        t.start();
    }

    // MODIFIES: this
    // EFFECTS: checks to see if the tank is non-empty, to see if the user wants to load that data
    private void attemptAutoLoad() {
        if (checkAutoLoad()) {
            int save;
            save = JOptionPane.showConfirmDialog(null,
                    "We detected a previously saved tank. Would you like to load?",
                    "Load previous save?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (save == JOptionPane.OK_OPTION) {
                loadTank();
            } else {
                JOptionPane.showMessageDialog(null,
                        "A new tank has been prepared for you!");
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: makes the initial window where everything else is housed
    private void startFrame() {
        this.setTitle("Your fish tank!");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
    }

    // MODIFIES: this, tankPanel
    // EFFECTS: initializes the tankPanel and places it on the left
    private void startTankPanel() {
        tankPanel = new TankPanel(tank);
        this.add(tankPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu panel and places it on the right
    private void startMenuPanel() {
        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(0, 1, 0, 25));
        JButton addFishButton = new JButton(new AddFishAction());
        JButton removeFishButton = new JButton(new RemoveFishAction());
        JButton feedFishButton = new JButton(new FeedFishAction());
        JButton statusFishButton = new JButton(new StatusFishAction());
        JButton saveFishButton = new JButton(new SaveFishAction());

        menuArea.add(addFishButton);
        menuArea.add(removeFishButton);
        menuArea.add(feedFishButton);
        menuArea.add(statusFishButton);
        menuArea.add(saveFishButton);

        this.add(menuArea, BorderLayout.EAST);
    }


    // MODIFIES: this
    // EFFECTS: handles user input for adding fish
    private class AddFishAction extends AbstractAction {
        AddFishAction() {
            super("Add Fish");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (tank.isFull()) {
                JOptionPane.showMessageDialog(null, "The tank is at capacity!");
            } else {
                fishNameField = new JTextField();
                fishColorField = new JTextField();
                fishSpeciesField = new JTextField();
                Object[] fishParams = {"Name", fishNameField,
                        "Color", fishColorField,
                        "Species", fishSpeciesField};
                int ok = JOptionPane.showConfirmDialog(null, fishParams, "Fish Details",
                        JOptionPane.OK_CANCEL_OPTION);
                if (ok == (JOptionPane.OK_OPTION)) {
                    tank.addFish(new Fish(fishNameField.getText(), fishColorField.getText(),
                            fishSpeciesField.getText()));
                }
            }
        }
    }

    // MODIFIES: this, tank
    // EFFECTS: handles user input for removing fish
    private class RemoveFishAction extends AbstractAction {
        RemoveFishAction() {
            super("Remove Fish");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (tank.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The tank is currently empty!");
            } else {
                String nameToRemove = JOptionPane.showInputDialog(null,
                        printSummary() + "What is the name of the fish you would like to remove?");
                if (tank.removeFish(nameToRemove)) {
                    JOptionPane.showMessageDialog(null,
                            nameToRemove + " has gone to fish heaven! Bye "
                                    + nameToRemove + "!");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "That fish could not be found! Please try again.");
                }
            }
        }
    }

    // MODIFIES: this, tank
    // EFFECTS: feeds all the fish and displays it to the GUI
    private class FeedFishAction extends AbstractAction {
        public FeedFishAction() {
            super("Feed Fish");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tank.feedAllFish();
            JOptionPane.showMessageDialog(null, "Yum!");
        }
    }

    // EFFECTS: Displays the status of the fish in the tank
    private class StatusFishAction extends AbstractAction {
        public StatusFishAction() {
            super("Fish Status");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, printSummary());
        }
    }

    // EFFECTS: saves fish to the json file
    private class SaveFishAction extends AbstractAction {
        public SaveFishAction() {
            super("Save Fish");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveTank();
        }
    }

    // EFFECTS: centers the JFrame in the middle of the user's screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // EFFECTS: prints out each fish's profile to the system
    public String printSummary() {
        //ArrayList<String> fishSummaries = new ArrayList<String>();
        String printString = "";
        for (int i = 0; i < tank.size(); i++) {
            //fishSummaries.add(tank.getFish(i).getSummary());
            printString = printString.concat(tank.getFish(i).getSummary());
            printString = printString.concat("\n");
        }
        if (tank.isEmpty()) {
            printString = "The tank is currently empty.";
        }
        return printString;
    }

    // SOURCE: please note that the methods for persistence are based on WorkRoomApp
    // EFFECTS: saves the current tank to file
    public void saveTank() {
        try {
            jsonWriter.open();
            jsonWriter.write(tank);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully saved!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "An error occurred writing to the filepath at " + JSON_PATH);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tank on file
    public void loadTank() {
        try {
            tank = jsonReader.read();
            System.out.println("Successfully loaded tank from " + JSON_PATH + "! Welcome back!");
        } catch (IOException e) {
            System.out.println("An error occurred when reading from " + JSON_PATH);
        }
    }

    // EFFECTS: returns true if there are fish in the save file to load from
    public boolean checkAutoLoad() {
        try {
            Tank tempTank = new Tank();
            tempTank = jsonReader.read();
            return !tempTank.isEmpty();
        } catch (IOException e) {
            System.out.println("An error occurred while checking auto save file");
        }
        return false;
    }
}
