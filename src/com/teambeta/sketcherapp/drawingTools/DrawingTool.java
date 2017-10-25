package com.teambeta.sketcherapp.drawingTools;

import com.teambeta.sketcherapp.model.ImageLayer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * DrawingTool defines the template methods for all drawing tools
 */
public abstract class DrawingTool {
    /**
     * @return Returns the color the drawing tool is set to.
     */
    public abstract Color getColor();

    /**
     * onDrag defines the graphical behavior of the tool when the mouse is dragged.
     *
     * @param canvas                 The final image to mix onto.
     * @param currentlySelectedLayer
     * @param layers                 The canvas layers to draw on.
     * @param e                      The mouse event that needs to be responded to.
     */
    public abstract void onDrag(BufferedImage canvas, ImageLayer currentlySelectedLayer,
                                BufferedImage[] layers, MouseEvent e);

    /**
     * onDrag defines the graphical behavior of the tool when the mouse is dragged.
     *
     * @param canvas                 The final image to mix onto.
     * @param layers                 The canvas layers to draw on.
     * @param e                      The mouse event that needs to be responded to.
     * @param currentlySelectedLayer The canvas layer to draw on.
     */
    public abstract void onRelease(BufferedImage canvas, BufferedImage[] layers, MouseEvent e,
                                   ImageLayer currentlySelectedLayer);

    /**
     * onClick defines the graphical behavior of the tool when the mouse is released.
     *
     * @param canvas                 The final image to mix onto.
     * @param layers                 The canvas layers to draw on.
     * @param e                      The mouse event that needs to be responded to.
     * @param currentlySelectedLayer The canvas layer to draw on.
     */
    public abstract void onClick(BufferedImage canvas, BufferedImage[] layers, MouseEvent e,
                                 ImageLayer currentlySelectedLayer);

    /**
     * onPress defines the graphical behavior of the tool when the mouse is released.
     *
     * @param canvas                 The final image to mix onto.
     * @param layers                 The canvas layers to draw on.
     * @param e                      The mouse event that needs to be responded to.
     * @param currentlySelectedLayer The canvas layers to draw on.
     */
    public abstract void onPress(BufferedImage canvas, BufferedImage[] layers, MouseEvent e,
                                 ImageLayer currentlySelectedLayer);

    /**
     * @return Returns the current width of the tool
     */
    public abstract int getToolWidth();

    /**
     * setToolWidth sets the width of a tool so the user can change how thick or thin it comes up on the canvas
     *
     * @param width The Width of the tool
     */

    public abstract void setToolWidth(int width);

    /**
     * setFillState updates the fill state of tools that support filling.
     * By default, nothing will occur if the tool doesn't have support.
     *
     * @param fillState The boolean expression of the required fill state
     */
    public abstract void setFillState(boolean fillState);
}
