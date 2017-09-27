package com.teambeta.sketcherapp.drawingTools;

import com.teambeta.sketcherapp.model.GeneralObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Color chooser for the application. Appears as a box, and when clicked, user is able to select a color for drawing
 * tools.
 */
public class ColorChooser extends JPanel {
    private static Color color;
    private static final int SQUARE_POS_XY = 10;
    private static final int SQUARE_LENGTH = 50;
    private static final int PANEL_LENGTH = 50;
    private static List<GeneralObserver> observers;

    // Constructor that displays default color as a square panel (graphic selector).
    public ColorChooser() {
        observers = new ArrayList<>();
        color = Color.BLACK;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeColor();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(getColor());
        graphics.fillRect(SQUARE_POS_XY, SQUARE_POS_XY, SQUARE_LENGTH, SQUARE_LENGTH);
        graphics.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_LENGTH, PANEL_LENGTH);
    }


    /**
     * Changes color for the graphic selector.
     */
    private void changeColor() {
        color = JColorChooser.showDialog(null, "Select a Color", color);
        paintComponent(getGraphics());
        notifyObservers();
    }


    /**
     * Gets the color of the graphic selector.
     *
     * @return color of the graphic selector.
     */
    public static Color getColor() {
        return color;
    }

    /**
     * Adds observers to a list.
     *
     * @param observer to notify when color is updated.
     */
    public static void addObserver(GeneralObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers in list when color is updated.
     */
    private static void notifyObservers() {
        for (GeneralObserver observer : observers) {
            observer.update();
        }
    }
}