package com.teambeta.sketcherapp.drawingTools;

import com.teambeta.sketcherapp.model.GeneralObserver;
import com.teambeta.sketcherapp.model.ImageLayer;
import com.teambeta.sketcherapp.ui.DrawArea;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * The PaintBucket class implements the drawing behavior for when the PaintBucket tool has been selected
 */
public class PaintBucketTool extends DrawingTool {
    private Color color;

    /**
     * The constructor sets the properties of the tool to their default values
     */
    public PaintBucketTool() {
        color = Color.black;
        registerObservers();
    }

    /**
     * Add a new observer to ColorChooser. Selecting a color in ColorChooser will update the color in this class
     */
    private void registerObservers() {
        ColorChooser.addObserver(new GeneralObserver() {
            @Override
            public void update() {
                color = ColorChooser.getColor();
            }
        });
    }

    @Override
    public Color getColor() {
        return ColorChooser.getColor();
    }

    @Override
    public void onDrag(BufferedImage canvas, ImageLayer currentlySelectedLayer, BufferedImage[] layers, MouseEvent e) {
    }

    @Override
    public void onRelease(BufferedImage canvas, BufferedImage[] layers, MouseEvent e, ImageLayer currentlySelectedLayer) {
    }

    @Override
    public void onClick(BufferedImage canvas, BufferedImage[] layers, MouseEvent e, ImageLayer currentlySelectedLayer) {
        Graphics2D layer1Graphics = (Graphics2D) layers[0].getGraphics();
        layer1Graphics.setColor(this.getColor());
        //get the coordinates of where the user clicked the mouse
        int currentX = e.getX();
        int currentY = e.getY();
        //get the color of the pixel clicked
        Color colorToReplace = new Color(layers[0].getRGB(currentX, currentY));

        //fill the connected area of the clicked pixels color with the paint buckets color.
        floodFill(layers[0], currentX, currentY, colorToReplace);
        DrawArea.drawLayersOntoCanvas(layers, canvas);
    }

    private void floodFill(BufferedImage layer, int x, int y, Color colorToReplace) {
        //TODO:fix problem with anti-aliasing causing gaps in the fill
        Queue<Integer> xCoordsOfPixelsToColor = new LinkedList<>();
        Queue<Integer> yCoordsOfPixelsToColor = new LinkedList<>();
        int xOfCurrentPixel = x;
        int yOfCurrentPixel = y;
        xCoordsOfPixelsToColor.add(xOfCurrentPixel);
        yCoordsOfPixelsToColor.add(yOfCurrentPixel);
        int replacementColorRGB = this.color.getRGB();
        int colorToReplaceRGB = colorToReplace.getRGB();
        if (replacementColorRGB == colorToReplaceRGB) {
            //early exit
            return;
        }
        int eastMostXCoord;
        int downPixelY;
        int upPixelY;
        int westMostXCoord;
        Graphics2D layer1Graphics = (Graphics2D) layer.getGraphics();
        layer1Graphics.setColor(this.color);

        while (!xCoordsOfPixelsToColor.isEmpty() || !yCoordsOfPixelsToColor.isEmpty()) {
            xOfCurrentPixel = xCoordsOfPixelsToColor.remove();
            yOfCurrentPixel = yCoordsOfPixelsToColor.remove();
            eastMostXCoord = xOfCurrentPixel;
            westMostXCoord = xOfCurrentPixel;

            downPixelY = yOfCurrentPixel - 1;
            //TODO: find out why +2 works and not +1.
            upPixelY = yOfCurrentPixel + 2;
            while (eastMostXCoord < layer.getWidth() &&
                    layer.getRGB(eastMostXCoord, yOfCurrentPixel) == colorToReplaceRGB) {
                if (downPixelY < layer.getHeight() && (downPixelY >= 0) &&
                        (layer.getRGB(eastMostXCoord, downPixelY) - colorToReplaceRGB) == 0) {
                    xCoordsOfPixelsToColor.add(eastMostXCoord);
                    yCoordsOfPixelsToColor.add(downPixelY);
                }
                if (upPixelY < layer.getHeight() && (upPixelY >= 0) &&
                        (layer.getRGB(eastMostXCoord, upPixelY) - colorToReplaceRGB) == 0) {
                    xCoordsOfPixelsToColor.add(eastMostXCoord);
                    yCoordsOfPixelsToColor.add(upPixelY);
                }
                eastMostXCoord++;
            }
            while (westMostXCoord >= 0 &&
                    (layer.getRGB(westMostXCoord, yOfCurrentPixel) - colorToReplaceRGB) == 0) {
                if (downPixelY < layer.getHeight() && (downPixelY >= 0) &&
                        layer.getRGB(westMostXCoord, downPixelY) - colorToReplaceRGB == 0) {
                    xCoordsOfPixelsToColor.add(westMostXCoord);
                    yCoordsOfPixelsToColor.add(downPixelY);
                }
                if (upPixelY < layer.getHeight() && (upPixelY >= 0) &&
                        (layer.getRGB(westMostXCoord, upPixelY) - colorToReplaceRGB) == 0) {
                    xCoordsOfPixelsToColor.add(westMostXCoord);
                    yCoordsOfPixelsToColor.add(upPixelY);
                }
                westMostXCoord--;
            }
            if (westMostXCoord != eastMostXCoord) {
                layer1Graphics.drawRect
                        (westMostXCoord, yOfCurrentPixel, eastMostXCoord - westMostXCoord, 1);
            }
        }
    }

    @Override
    public void onPress(BufferedImage canvas, BufferedImage[] layers, MouseEvent e, ImageLayer currentlySelectedLayer) {

    }

    @Override
    public int getToolWidth() {
        return 0;
    }

    @Override
    public void setToolWidth(int width) {

    }

    @Override
    public void setFillState(boolean fillState) {

    }
}
