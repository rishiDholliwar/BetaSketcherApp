package com.teambeta.sketcherapp.drawingTools;

import com.teambeta.sketcherapp.model.GeneralObserver;
import com.teambeta.sketcherapp.model.ImageLayer;
import com.teambeta.sketcherapp.model.MouseCursor;
import com.teambeta.sketcherapp.ui.DrawArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.Paper;
import java.util.LinkedList;

/**
 * The Rectangle Selection tool class is used to select an image by a rectangle and drag them to another location
 * on the canvas
 */
public class RectangleSelectionTool extends DrawingTool {
    private int currentY;
    private int currentX;
    private int initX;
    private int initY;
    private int mouseOriginX;
    private int mouseOriginY;
    private int drawWidthX;
    private int drawHeightY;
    private int xAxisMagnitudeDelta;
    private int yAxisMagnitudeDelta;
    private Color color;
    private BufferedImage previewLayer = null;
    private int squareWidth;
    private final int DEFAULT_WIDTH_VALUE = 10;
    private boolean fillShape;
    private float dash[] = {10.0f};
    private BufferedImage originalSelectedCanvas;
    private static final Color transparentColor = new Color(0x00FFFFFF, true);
    private BufferedImage selectedCanvas; // the selection that the user has made
    private boolean isOverSelection; // is the mouse over the selectedCanvas
    private boolean isDrawn;// has the user selected somthing
    private int prevX, prevY;
    private boolean isDragSelection = false; // is the user choosing to drag the image
    private LinkedList<ImageLayer> drawingLayersCopy;
    private BufferedImage canvasCopy;
    private static final String INSTRUCTION_SELECTION_CANCEL_LABEL = "Right click to cancel";
    private static final String CUT_TEXT_RADIO_BUTTON = "Cut";
    private static final String COPY_TEXT_RADIO_BUTTON = "Copy";
    private static final int FONT_SIZE_TEXT_PANEL = 24;
    private static final String FONT_NAME_PANEL = "David";
    private JRadioButton cutOption;
    private JRadioButton copyOption;
    private JLabel rightClickInfo;
    private boolean doNothing = false;
    private boolean isCutOption;
    private JPanel selectionOptionPanel;
    private MouseCursor mouseCursor;

    /**
     * The constructor sets the properties of the tool to their default values
     */
    public RectangleSelectionTool(MouseCursor mouseCursor) {
        registerObservers();
        color = Color.black;
        initX = 0;
        initY = 0;
        currentX = 0;
        currentY = 0;
        mouseOriginX = 0;
        mouseOriginY = 0;
        drawWidthX = 0;
        drawHeightY = 0;
        xAxisMagnitudeDelta = 0;
        yAxisMagnitudeDelta = 0;
        squareWidth = DEFAULT_WIDTH_VALUE;
        fillShape = false;
        isCutOption = true;
        this.mouseCursor = mouseCursor;
    }

    /**
     * Checks to see where the user mouse is
     */
    @Override
    public void onMove(BufferedImage canvas, MouseEvent e, LinkedList<ImageLayer> drawingLayers) {
        if (selectedCanvas != null) {
            int offset = 40;
            if (e.getY() >= initY - offset && e.getY() <= (selectedCanvas.getHeight() + initY - offset)
                    && e.getX() >= initX - offset && e.getX() <= (selectedCanvas.getWidth() + initX - offset)) {
                if (isDrawn) {
                    isOverSelection = true;
                    mouseCursor.dragCursor();
                }
            } else {
                doNothing = false;
                if (isDrawn) {
                    mouseCursor.targetCursor();
                } else {
                    mouseCursor.setDefaultCursor();
                }
                isOverSelection = false;
            }
        }
    }

    @Override
    public void onPress(BufferedImage canvas, MouseEvent e, LinkedList<ImageLayer> drawingLayers) {
        canvasCopy = canvas;
        drawingLayersCopy = drawingLayers;
        if (SwingUtilities.isRightMouseButton(e)) {
            restartSelection();
            doNothing = true;
            //my code
        } else {
            if (!isDrawn) {
                MouseCursor.setDefaultCursor();
                canvas.getGraphics().setColor(color);
                currentX = e.getX();
                currentY = e.getY();
                initX = currentX;
                initY = currentY;
                mouseOriginX = currentX;
                mouseOriginY = currentY;
                originalSelectedCanvas = copyImage(canvas);
            } else {
                if (isCutOption)
                    clearSelection(canvas, drawingLayers);
                prevX = initX - e.getX();
                prevY = initY - e.getY();
                initializeVariables(canvas, e);
                isDragSelection = isOverSelection;
            }
        }
    }

    @Override
    public void onDrag(BufferedImage canvas, MouseEvent e, LinkedList<ImageLayer> drawingLayers) {
        if (!drawingLayers.isEmpty()) {
            if (!doNothing) {
                if (!isDrawn) {
                    if (previewLayer == null) {
                        previewLayer = DrawArea.getPreviewBufferedImage();
                    }
                    //clear preview layer
                    DrawArea.clearBufferImageToTransparent(previewLayer);
                    //init graphics objects
                    Graphics2D canvasGraphics = (Graphics2D) canvas.getGraphics();
                    canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    canvasGraphics.setColor(color);
                    Graphics2D previewLayerGraphics = (Graphics2D) previewLayer.getGraphics();
                    canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    previewLayerGraphics.setColor(color);
                    previewLayerGraphics.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
                    calcSquareCoordinateData(e);
                    previewLayerGraphics.drawRect(initX, initY, drawWidthX, drawHeightY);
                    //info: https://docs.oracle.com/javase/tutorial/2d/advanced/compositing.html
                    //draw the preview layer on top of the drawing layer(s)
                    AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
                    canvasGraphics.setComposite(alphaComposite);
                    DrawArea.drawLayersOntoCanvas(drawingLayers, canvas);
                    canvasGraphics.drawImage(previewLayer, 0, 0, null);
                } else {
                    if (selectedCanvas != null) {
                        dragImage(canvas, e, drawingLayers);
                    }
                }
            }
        }
    }

    @Override
    public void onRelease(BufferedImage canvas, MouseEvent e, LinkedList<ImageLayer> drawingLayers) {
        if (!doNothing) {
            ImageLayer selectedLayer = getSelectedLayer(drawingLayers);
            if (selectedLayer != null) {
                if (!isDrawn) {
                    isDrawn = true;
                    checkBounds(canvas);
                    selectedCanvas = copyImage(selectedLayer.getBufferedImage().getSubimage(initX, initY,
                            drawWidthX, drawHeightY));
                    DrawArea.clearBufferImageToTransparent(previewLayer);
                } else {
                    MouseCursor.setDefaultCursor();
                    isDrawn = false;
                    if (isDragSelection) {
                        pasteDragSelection(canvas, drawingLayers, selectedLayer, e);
                    } else {
                        pasteSelection(canvas, drawingLayers, selectedLayer, e);
                    }
                    selectedCanvas = null;
                }
            } else {
                restartSelection();
            }
        } else {
            doNothing = false;
        }
    }

    /**
     * if the user selects the canvas that goes out of bounds this function will correct the results
     */
    private void checkBounds(BufferedImage canvas) {
        if (initX + (drawWidthX) > canvas.getWidth()) {
            drawWidthX = (canvas.getWidth() - initX);
        }
        if (initX < 0) {
            drawWidthX = drawWidthX - Math.abs(initX);
            initX = 0;
        }
        if ((initY + (drawHeightY)) > canvas.getHeight()) {
            drawHeightY = Math.abs(canvas.getHeight() - initY);
        }
        if (initY < 0) {
            drawHeightY = drawHeightY - Math.abs(initY);
            initY = 0;
        }
        //canvas.getSubimage function will only take numbers >0 need minimum of 2 as we are going to subtract 1
        //in order to remove the preview border
        if (drawWidthX <= 1) {
            drawWidthX = 2;
        }
        if (drawHeightY <= 1) {
            drawHeightY = 2;
        }
    }

    /**
     * getting sub-image is pass by reference so this will transform it to pass by value
     */
    public static BufferedImage copyImage(BufferedImage copyBuffer) {
        BufferedImage bufferedImage = new BufferedImage(copyBuffer.getWidth(), copyBuffer.getHeight(), copyBuffer.getType());
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.drawImage(copyBuffer, 0, 0, null);
        g.dispose();
        return bufferedImage;
    }

    /**
     * clears the space where the user selected the canvas
     */
    private void clearSelection(BufferedImage canvas, LinkedList<ImageLayer> drawingLayers) {
        ImageLayer selectedLayer = getSelectedLayer(drawingLayers);
        Graphics2D selectedLayerGraphics = (Graphics2D) selectedLayer.getBufferedImage().getGraphics();
        selectedLayerGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        selectedLayerGraphics.setComposite(AlphaComposite.Clear);
        selectedLayerGraphics.setColor(transparentColor);
        selectedLayerGraphics.setStroke(new BasicStroke(0));
        int offset = 0;
        selectedLayerGraphics.fillRect(initX, initY, drawWidthX - offset, drawHeightY - offset);
        selectedLayerGraphics.drawRect(initX, initY, drawWidthX - offset, drawHeightY - offset);
        DrawArea.drawLayersOntoCanvas(drawingLayers, canvas);
    }

    /**
     * drags the selected canvas to its new location
     */
    private void dragImage(BufferedImage canvas, MouseEvent e, LinkedList<ImageLayer> drawingLayers) {
        //init graphics objects
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        Graphics2D canvasGraphics = (Graphics2D) canvas.getGraphics();
        canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        canvasGraphics.setColor(transparentColor);
        Graphics2D selectedCanvasGraphics = (Graphics2D) selectedCanvas.getGraphics();
        canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        selectedCanvasGraphics.setColor(transparentColor);
        selectedCanvasGraphics.setComposite(alphaComposite);
        selectedCanvasGraphics.drawRect(prevX + e.getX(), prevY + e.getY(), drawWidthX, drawHeightY);
        //info: https://docs.oracle.com/javase/tutorial/2d/advanced/compositing.html
        //draw the preview layer on top of the drawing layer(s)
        canvasGraphics.setComposite(alphaComposite);
        DrawArea.drawLayersOntoCanvas(drawingLayers, canvas);
        canvasGraphics.drawImage(selectedCanvas, prevX + e.getX(), prevY + e.getY(), null);
    }

    /**
     * places the selected canvas where the user has dragged it to its new location
     */
    private void pasteDragSelection(BufferedImage canvas, LinkedList<ImageLayer> drawingLayers,
                                    ImageLayer selectedLayer, MouseEvent e) {
        //cut and paste the image into clicked location
        if (selectedLayer != null) {
            Graphics2D selectedLayerGraphics = (Graphics2D) selectedLayer.getBufferedImage().getGraphics();
            selectedLayerGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            selectedLayerGraphics.setColor(color);
            selectedLayerGraphics.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            selectedLayerGraphics.drawImage(selectedCanvas, prevX + e.getX(), prevY + e.getY(), null);
            DrawArea.drawLayersOntoCanvas(drawingLayers, canvas);
        }
        isDrawn = false;
    }

    /**
     * cut and paste the image into clicked location
     */
    private void pasteSelection(BufferedImage canvas, LinkedList<ImageLayer> drawingLayers, ImageLayer selectedLayer, MouseEvent e) {
        if (selectedLayer != null) {
            Graphics2D selectedLayerGraphics = (Graphics2D) selectedLayer.getBufferedImage().getGraphics();
            selectedLayerGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            selectedLayerGraphics.setColor(color);
            selectedLayerGraphics.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            selectedLayerGraphics.drawImage(selectedCanvas, e.getX(), e.getY(), null);
            DrawArea.drawLayersOntoCanvas(drawingLayers, canvas);
        }
        DrawArea.clearBufferImageToTransparent(selectedCanvas);
        isDrawn = false;
    }

    @Override
    public void onClick(BufferedImage canvas, MouseEvent e, LinkedList<ImageLayer> drawingLayers) {
        canvasCopy = canvas;
        drawingLayersCopy = drawingLayers;
        if (SwingUtilities.isRightMouseButton(e)) {
            restartSelection();
            doNothing = true;
            //my code
        } else {
            if (isDrawn) {
                isDrawn = false;
                doNothing = false;
                mouseCursor.setDefaultCursor();
                initializeVariables(canvas, e);
                pasteSelection(canvas, drawingLayers, null, e);
            }
        }
    }

    private ImageLayer getSelectedLayer(LinkedList<ImageLayer> drawingLayers) {
        //get the selected layer, this assumes there is only one selected layer.
        for (int i = 0; i < drawingLayers.size(); i++) {
            ImageLayer drawingLayer = drawingLayers.get(i);
            if (drawingLayer.isSelected()) {
                return drawingLayer;
            }
        }
        return null;
    }

    @Override
    public int getToolWidth() {
        return squareWidth;
    }

    @Override
    public void setToolWidth(int width) {
        squareWidth = width;
    }

    /**
     * getColor returns the current color the square tool is set to.
     *
     * @return the current Color of the LineTool
     */
    @Override
    public Color getColor() {
        return ColorChooser.getColor();
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

    public void setFillState(boolean fillState) {
        fillShape = fillState;
    }

    private void calcSquareCoordinateData(MouseEvent e) {
        // Get the coordinates of where the user released the mouse.
        currentX = e.getX();
        currentY = e.getY();
        // Draw the square with the longest side as long as the shortest side.
        xAxisMagnitudeDelta = Math.abs(currentX - mouseOriginX);
        yAxisMagnitudeDelta = Math.abs(currentY - mouseOriginY);
        // Detect shift-down by the MouseEvent, e.
        if (e.isShiftDown()) {
            if (xAxisMagnitudeDelta > yAxisMagnitudeDelta) {
                drawWidthX = yAxisMagnitudeDelta;
                drawHeightY = yAxisMagnitudeDelta;
            } else {
                drawWidthX = xAxisMagnitudeDelta;
                drawHeightY = xAxisMagnitudeDelta;
            }
        } else {
            drawWidthX = xAxisMagnitudeDelta;
            drawHeightY = yAxisMagnitudeDelta;
        }
        // Handle cases where the square lies in a quadrant (with origin 0,0 at click) other than IV.
        if (currentY < mouseOriginY) {
            initY = mouseOriginY - drawHeightY;
        }
        if (currentX < mouseOriginX) {
            initX = mouseOriginX - drawWidthX;
        }
    }

    /**
     * initializes variables for this class
     */
    private void initializeVariables(BufferedImage canvas, MouseEvent e) {
        canvas.getGraphics().setColor(color);
        currentX = e.getX();
        currentY = e.getY();
        initX = currentX;
        initY = currentY;
        mouseOriginX = currentX;
        mouseOriginY = currentY;
        isCutOption = cutOption.isSelected();
    }

    /**
     * when we switch to another tool, restart the selection
     */
    public void restartSelection() {
        MouseCursor.setDefaultCursor();
        if (drawingLayersCopy != null && canvasCopy != null)
            DrawArea.drawLayersOntoCanvas(drawingLayersCopy, canvasCopy);
        isOverSelection = false;
        isDrawn = false;
    }

    /**
     * gui for the north east panel
     */
    public void renderPanel() {
        selectionOptionPanel = new JPanel();
        selectionOptionPanel.setLayout(new GridBagLayout());
        rightClickInfo = new JLabel(INSTRUCTION_SELECTION_CANCEL_LABEL);
        rightClickInfo.setFont(new Font(FONT_NAME_PANEL, Font.PLAIN, FONT_SIZE_TEXT_PANEL));
        rightClickInfo.setForeground(Color.RED);
        rightClickInfo.setBackground(Color.DARK_GRAY);
        cutOption = new JRadioButton(CUT_TEXT_RADIO_BUTTON);
        cutOption.setBackground(Color.DARK_GRAY);
        cutOption.setSelected(true);
        cutOption.setForeground(Color.white);
        cutOption.setFont(new Font(FONT_NAME_PANEL, Font.PLAIN, FONT_SIZE_TEXT_PANEL));
        copyOption = new JRadioButton(COPY_TEXT_RADIO_BUTTON);
        copyOption.setBackground(color.DARK_GRAY);
        copyOption.setForeground(Color.white);
        copyOption.setFont(new Font(FONT_NAME_PANEL, Font.PLAIN, FONT_SIZE_TEXT_PANEL));
        ButtonGroup group = new ButtonGroup();
        group.add(cutOption);
        group.add(copyOption);
        JPanel firstLine = new JPanel();
        firstLine.setBackground(color.DARK_GRAY);
        firstLine.setLayout(new FlowLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        firstLine.add(cutOption, c);
        c.gridx = 1;
        c.gridy = 0;
        firstLine.add(copyOption, c);
        c.gridx = 2;
        c.gridy = 0;
        JPanel secondLine = new JPanel();
        secondLine.setBackground(Color.DARK_GRAY);
        secondLine.setLayout(new FlowLayout());
        c.gridx = 0;
        c.gridy = 1;
        secondLine.add(rightClickInfo, c);
        c.gridx = 0;
        c.gridy = 0;
        selectionOptionPanel.add(firstLine, c);
        c.gridx = 0;
        c.gridy = 1;
        selectionOptionPanel.add(secondLine, c);
        selectionOptionPanel.setBackground(Color.DARK_GRAY);
        cutOption.addActionListener(actionListener);
        copyOption.addActionListener(actionListener);
    }

    public void hidePanel() {
        selectionOptionPanel.setVisible(false);
    }

    public void showPanel() {
        selectionOptionPanel.setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cutOption) {
                isCutOption = true;
            }
            if (e.getSource() == copyOption) {
                isCutOption = false;
            }
        }
    };

    /**
     * returns a panel tom display message, and cut/paste options
     */
    public JPanel getSelectionOptionPanel() {
        return selectionOptionPanel;
    }
}