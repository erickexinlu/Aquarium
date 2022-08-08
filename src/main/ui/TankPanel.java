package ui;

import model.Fish;
import model.Tank;

import javax.swing.*;
import java.awt.*;


// SOURCE: JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// SOURCE: PaddleBallFrame from lab3 (cloned from VCS)
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab3_m6g6q/commit/8b37f9b6e072a28e6e962ec68cd148c6e767ec12

// SOURCE: SpaceInvadersStarter (download from edX)
// https://edge.edx.org/assets/courseware/v1/13595daba554c85e2fe669e686cbff91/
// asset-v1:UBC+CPSC210+all+type@asset+block/SpaceInvadersStarter.zip

// This class represents the JPanel in which the tank is housed
public class TankPanel extends JPanel {
    private Tank tank;
    public static final Color FISH_COLOR = new Color(10, 188, 132);

    // EFFECTS: intializes the blue tank panel on the left
    public TankPanel(Tank t) {
        setBackground(Color.BLUE);
        this.tank = t;
        setPreferredSize(new Dimension(Tank.TANK_WIDTH, Tank.TANK_HEIGHT));
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: handles painting in the JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFishes(g);
    }

    // MODIFIES: this
    // EFFECTS: draws each fish onto the Panel
    private void drawFishes(Graphics g) {
        for (Fish f : tank.getTank()) {
            drawFish(g, f);
        }
    }

    // MODIFIES: this
    // EFFECTS: draws an individual fish circle onto the Panel
    private void drawFish(Graphics g, Fish f) {
        Color tempColor = g.getColor();
        g.setColor(FISH_COLOR);
        g.fillOval(f.getPosX() - Fish.DIAMETER / 2,
                f.getPosY() - Fish.DIAMETER / 2,
                Fish.DIAMETER, Fish.DIAMETER);
        g.setColor(tempColor);
    }
}
