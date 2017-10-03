package com.teambeta.sketcherapp.drawingTools;

import com.teambeta.sketcherapp.model.GeneralObserver;
import com.teambeta.sketcherapp.ui.DrawArea;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * The BrushTool class implements the drawing behavior for when the Brush tool has been selected
 */
public class BrushTool extends DrawingTool {

    private int currentY;
    private int currentX;
    private int lastX;
    private int lastY;
    private int sizeInPixels;
    private Color color;
    private int brushWidth;
    private Graphics2D layer1Graphics;
    private final int DEFAULT_STOKE_VALUE = 1;

    public int getBrushWidth() {
        return brushWidth;
    }

    public void setBrushWidth(int brushWidth) {
        this.brushWidth = brushWidth;
    }

    /**
     * The constructor sets the properties of the tool to their default values
     */
    public BrushTool() {
        color = Color.black;
        registerObservers();
        sizeInPixels = 1;
        lastX = 0;
        lastY = 0;
        currentX = 0;
        currentY = 0;
        brushWidth = 10;
    }

    @Override
    public void onDrag(BufferedImage canvas, BufferedImage[] layers, MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();

        layer1Graphics.drawLine(lastX, lastY, currentX, currentY);
        DrawArea.drawLayersOntoCanvas(layers, canvas);

        lastX = currentX;
        lastY = currentY;
    }

    @Override
    public void onRelease(BufferedImage canvas, BufferedImage[] layers, MouseEvent e) {
    }

    @Override
    public void onClick(BufferedImage canvas, BufferedImage[] layers, MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();

        layer1Graphics.fillOval(currentX - (brushWidth/2),currentY -(brushWidth/2), brushWidth, brushWidth);
        DrawArea.drawLayersOntoCanvas(layers, canvas);
    }
    
    @Override
    public void onPress(BufferedImage canvas, BufferedImage[] layers, MouseEvent e) {
        // Initialize canvas settings that the tool will require.
        initLayer1Graphics(canvas, layers, e);

        // Set the coordinates to the current point when the mouse is pressed.
        currentX = e.getX();
        currentY = e.getY();
        lastX = currentX;
        lastY = currentY;
    }

    /**
     * getColor returns the current color the brush tool is set to.
     *
     * @return the current Color of the LineTool
     */
    @Override
    public Color getColor() {
        return ColorChooser.getColor();
    }

    /**
     * Add a new observer to ColorChooser. Selecting a color in ColorChooser will update the color in this class.
     */
    private void registerObservers() {
        ColorChooser.addObserver(new GeneralObserver() {
            @Override
            public void update() {
                color = ColorChooser.getColor();
            }
        });
    }

    /**
     * Initialize the parameters required for layer1Graphics.
     *
     * @param canvas for drawing the line onto.
     * @param layers first layer by default is layers[0]
     * @param e      MouseEvent
     */
    private void initLayer1Graphics(BufferedImage canvas, BufferedImage[] layers, MouseEvent e) {
        layer1Graphics = (Graphics2D) layers[0].getGraphics();
        layer1Graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        layer1Graphics.setColor(color);
        layer1Graphics.setStroke(new BasicStroke(getBrushWidth(), BasicStroke.CAP_ROUND,    // End-cap style
                BasicStroke.CAP_BUTT));
    }

}
