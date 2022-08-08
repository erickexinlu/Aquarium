package ui;

import model.Tank;

import javax.swing.*;
import java.awt.*;

// This class represents the JPanel in which the tank is housed
// This class is based off of code from PaddleBallFrame at
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab3_m6g6q/commit/8b37f9b6e072a28e6e962ec68cd148c6e767ec12

public class TankPanel extends JPanel {
    private Tank tank;

    public TankPanel(Tank t) {
        setBackground(Color.BLUE);
        this.tank = t;
        this.setLayout(null);
        setPreferredSize(new Dimension(tank.TANK_WIDTH, tank.TANK_HEIGHT));
        this.setBackground(Color.blue);
        this.setVisible(true);
    }
}
