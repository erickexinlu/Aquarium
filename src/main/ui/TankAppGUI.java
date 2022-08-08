package ui;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import model.Fish;
import model.Tank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// This class is composed of a tank, and manages the user interface so the user can interact
// SOURCE: JsonSerializationDemo
// SOURCE: PaddleBallFrame from lab3
public class TankAppGUI extends JFrame {
    private static final int INTERVAL = 40;
    private static final int WIDTH = 750;
    private static final int HEIGHT = 400;

    // SOURCE: JsonSerializationDemo
    private static final String JSON_PATH = "./data/tank.json";
    private TankPanel tankPanel;
    private JPanel menuArea;
    private Scanner input;
    private Tank tank;

    JTextField fishNameField;
    JTextField fishColorField;
    JTextField fishSpeciesField;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes the Tank object, and does startup activities with startTank();
    public TankAppGUI() {
        startFrame();

        tank = new Tank();
        startPanel();
        startButtons();
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);

        this.centreOnScreen();
        this.setVisible(true);
    }


    private void startFrame() {
        this.setTitle("Your fish tank!");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        //handle saving Listener event later
    }

    private void startPanel() {
        tankPanel = new TankPanel(tank);
        this.add(tankPanel, BorderLayout.WEST);
    }

    private void startButtons() {
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



    private class AddFishAction extends AbstractAction {
        AddFishAction() {
            super("Add Fish");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            fishNameField = new JTextField();
            fishColorField = new JTextField();
            fishSpeciesField = new JTextField();
            Object[] fishParams = {"Name", fishNameField,
                                   "Color", fishColorField,
                                   "Species", fishSpeciesField};
            int ok = JOptionPane.showConfirmDialog(null, fishParams, "Fish Details",
                    JOptionPane.OK_CANCEL_OPTION);
            if (ok == (JOptionPane.OK_OPTION)) {
                tank.addFish(new Fish(fishNameField.getText(), fishColorField.getText(), fishSpeciesField.getText()));
            }
        }
    }

    private class RemoveFishAction extends AbstractAction {
        RemoveFishAction() {
            super("Remove Fish");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
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

    private class FeedFishAction extends AbstractAction {
        public FeedFishAction() {
            super("Feed Fish");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tank.feedAllFish();
            JOptionPane.showMessageDialog(null,"Yum!");
        }
    }

    private class StatusFishAction extends AbstractAction {
        public StatusFishAction() {
            super("Fish Status");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, printSummary());
        }
    }

    private class SaveFishAction extends AbstractAction {
        public SaveFishAction() {
            super("Save Fish");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveTank();
        }
    }

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
        return printString;
    }

    // SOURCE: please note that the methods for persistence are based on WorkRoomApp
    // EFFECTS: saves the current tank to file
    public void saveTank() {
        try {
            jsonWriter.open();
            jsonWriter.write(tank);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,"Successfully saved!");
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


//    // MODIFIES: this
//    // EFFECTS: opens the Scanner, initializes the tank ArrayList, and handles input until program is terminated
//    private void startTank() {
//        String userInput;
//        boolean stop = false;
//
//        input = new Scanner(System.in);
//
//        doLoad();
//
//        while (!stop) {
//            // SOURCE: please note that code taking input from the Scanner is modelled after TellerApp
//
//            printInstructions();
//            userInput = input.nextLine();
//            userInput = userInput.toLowerCase();
//
//            if (userInput.equals("quit")) {
//                stop = true;
//                saveTank();
//            } else {
//                parseCommand(userInput);
//            }
//        }
//    }

//    // MODIFIES: this
//    // EFFECTS: calls the appropriate method based on the user command
//    private void parseCommand(String command) {
//        // SOURCE: this is modelled after TellerApp
//        if (command.equals("add")) {
//            doAdd();
//        } else if (command.equals("remove")) {
//            doRemoval();
//        } else if (command.equals("feed")) {
//            doFeed();
//        } else if (command.equals("hunger")) {
//            doHunger();
//        } else if (command.equals("status")) {
//            doStatus();
//        } else {
//            System.out.println("Your command was not accepted.");
//        }
//    }

//    // EFFECTS: prints out user instructions for interacting with the program
//    private void printInstructions() {
//        System.out.println("__________________________________");
//        System.out.println("Enter 'add' to add a new fish.");
//        System.out.println("Enter 'remove' to remove a fish.");
//        System.out.println("Enter 'feed' to feed the fish!");
//        System.out.println("Enter 'hunger' to decrease your fishes' hunger.");
//        System.out.println("Enter 'status' to check up on all the fish.");
//        System.out.println("Enter 'quit' to auto-save and quit.");
//    }

//    // MODIFIES: this
//    // EFFECTS: handles loading based on user input on startup
//    private void doLoad() {
//        if (checkAutoLoad()) {
//            String userInput;
//            System.out.println("We detected a previously saved tank. Would you like to load?");
//            System.out.println("[Y/N]");
//            userInput = input.nextLine();
//            userInput = userInput.toLowerCase();
//
//            if (userInput.equals("y")) {
//                loadTank();
//            } else {
//                System.out.println("A fresh new tank has been prepared for you!");
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds a fish with user input to the tank
//    private void doAdd() {
//        if (tank.isFull()) {
//            System.out.println("Sorry - the tank is at capacity!");
//        } else {
//            System.out.println("Wonderful! What would you like to name your fish?");
//            String name = input.nextLine();
//
//            System.out.println("What colour are they?");
//            String color = input.nextLine();
//
//            System.out.println("Finally, what is their species?");
//            String species = input.nextLine();
//
//            tank.addFish(new Fish(name, color, species));
//            System.out.println("Successfully added!");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: removes a fish from the tank
//    private void doRemoval() {
//        if (tank.isEmpty()) {
//            System.out.println("Sorry - the tank is empty! Add some fish first!");
//        } else {
//            System.out.println("Please type the name of the fish you would like to remove.");
//            doStatus();
//            String fishName = input.nextLine();
//            if (tank.removeFish(fishName)) {
//                System.out.println(fishName + " has gone to fish heaven! Goodbye " + fishName + "!");
//            } else {
//                System.out.println("That fish could not be found.");
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: feeds all the fish, if any
//    private void doFeed() {
//        if (tank.isEmpty()) {
//            System.out.println("You sprinkle some flakes into an empty tank. Why?");
//        } else {
//            tank.feedAllFish();
//            System.out.println("Yum!");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: hungers all the fish
//    private void doHunger() {
//        if (tank.isEmpty()) {
//            System.out.println("Empty tanks don't get hungry!");
//        } else {
//            tank.hungerAllFish();
//            System.out.println("Gurgle gurgle...");
//        }
//    }
//
//    // EFFECTS: prints all the Fish in the tank with their respective attributes
//    private void doStatus() {
//        if (tank.isEmpty()) {
//            System.out.println("There are no fish to view!");
//        } else {
//            System.out.println("The following fish are in the tank:");
//            printSummary();
//        }
//    }








}
