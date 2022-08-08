package ui;

import model.Fish;
import model.Tank;

import javax.swing.*;
import java.awt.*;

// This class represents the JPanel in which the tank is housed
// This class is based off of code from PaddleBallGame at
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab3_m6g6q/commit/8b37f9b6e072a28e6e962ec68cd148c6e767ec12

public class TankPanel extends JPanel {
    private Tank tank;
    public static final Color FISH_COLOR = new Color(10, 188, 132);

    public TankPanel(Tank t) {
        setBackground(Color.BLUE);
        this.tank = t;
        setPreferredSize(new Dimension(Tank.TANK_WIDTH, Tank.TANK_HEIGHT));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFishes(g);
    }

    private void drawFishes(Graphics g) {
        for (Fish f : tank.getTank()) {
            drawFish(g, f);
        }
    }

    private void drawFish(Graphics g, Fish f) {
        Color tempColor = g.getColor();
        g.setColor(FISH_COLOR);
        g.fillOval(f.getPosX() - Fish.DIAMETER / 2,
                f.getPosY() - Fish.DIAMETER / 2,
                Fish.DIAMETER, Fish.DIAMETER);
        g.setColor(tempColor);
    }
}
