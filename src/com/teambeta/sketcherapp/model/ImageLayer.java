package com.teambeta.sketcherapp.model;

import com.sun.org.apache.bcel.internal.generic.DUP;

import java.awt.image.BufferedImage;

/**
 * The Layer class represents a drawing layer for our painting app.
 */
public class ImageLayer {
    private boolean isVisible;
    private BufferedImage bufferedImage;
    private boolean isSelected;
    private String name;
    private static int layerNumber = 1;
    private final static String HIDDEN = "  [Hidden]";
    private final static String DUPLICATE = "(Duplicate)";

    /**
     * Constructor.
     *
     * @param bufferedImage for the image layer.
     * @param nameOfOriginalLayerIfDuplicate the name of the original layer if this is a duplicate. Pass null for new layer.
     */
    public ImageLayer(BufferedImage bufferedImage, String nameOfOriginalLayerIfDuplicate) {
        this.bufferedImage = bufferedImage;
        isVisible = true;
        isSelected = false;
        if (nameOfOriginalLayerIfDuplicate == null) {
            this.name = "  Layer " + layerNumber;
        } else {
            if (!nameOfOriginalLayerIfDuplicate.contains(" " + DUPLICATE)) {
                this.name = nameOfOriginalLayerIfDuplicate + " " + DUPLICATE;
            } else {
                this.name = nameOfOriginalLayerIfDuplicate;
            }
        }
        layerNumber++;
    }

    /**
     * Resets the layerNumber to 1.
     */
    public static void resetLayerNumber() {
        layerNumber = 1;
    }

    /**
     * Get the buffered image.
     *
     * @return bufferedImage
     */
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Checks if layer is visible.
     *
     * @return true if layer if visible, false otherwise.
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Set visibility of the layer.
     *
     * @param visible sets visibility of layer.
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Specifies if layer is selected.
     *
     * @param selected sets layer to selected.
     */
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    /**
     * Checks if layer is selected.
     *
     * @return true if layer is selected, false otherwise.
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Get the name of the image layer.
     *
     * @return name of layer.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the image layer.
     *
     * @param name for the layer.
     */
    public void setName(String name) {
        this.name = "  " + name;
    }

    @Override
    public String toString() {
        if (!isVisible) {
            return HIDDEN + " " + name;
        } else {
            return name;
        }
    }
}