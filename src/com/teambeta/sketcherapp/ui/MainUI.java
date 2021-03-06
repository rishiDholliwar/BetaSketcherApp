package com.teambeta.sketcherapp.ui;

import com.teambeta.sketcherapp.Database.DB_KBShortcuts;
import com.teambeta.sketcherapp.drawingTools.*;
import com.teambeta.sketcherapp.model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * Main UI class to wrap all GUI elements together.
 */
public class MainUI {
    private static LineTool lineTool;
    private static BrushTool brushTool;
    private static RectangleTool rectangleTool;
    private static EraserTool eraserTool;
    private static EllipseTool ellipseTool;
    private static TextTool textTool;
    private static PaintBucketTool paintBucketTool;
    private static EyeDropperTool eyeDropperTool;
    private static FanTool fanTool;
    private static CelticKnotTool celticKnotTool;
    private static DNATool dnaTool;
    private static AirBrushTool airBrushTool;
    private static TriangleTool triangleTool;
    private static SpiralTool spiralTool;
    private static DrawingTool selectedDrawingTool;
    private static EyeDropperStats eyeDropperStats;
    private RectangleSelectionTool rectangleSelectionTool;

    private static final String CLEAR_TOOL_BUTTON_TEXT = "Clear";
    private static final String SELECTION_TOOL_BUTTON_TEXT = "Selection";
    private static final String BRUSH_TOOL_BUTTON_TEXT = "Brush";
    private static final String RECTANGLE_TOOL_BUTTON_TEXT = "Rectangle";
    private static final String LINE_TOOL_BUTTON_TEXT = "Line";
    private static final String ERASER_TOOL_BUTTON_TEXT = "Eraser";
    private static final String ELLIPSE_TOOL_BUTTON_TEXT = "Ellipse";
    private static final String TEXT_TOOL_BUTTON_TEXT = "Text";
    private static final String BUCKET_BUTTON_TEXT = "Paint Bucket";
    private static final String EYEDROPPER_TOOL_BUTTON_TEXT = "Eye Dropper";
    private static final String FAN_TOOL_BUTTON_TEXT = "Fan-out";
    private static final String CELTIC_KNOT_TOOL_BUTTON_TEXT = "Celtic Knot";
    private static final String DNA_TOOL_BUTTON_TEXT = "DNA";
    private static final String AIR_BRUSH_TOOL_BUTTON_TEXT = "Airbrush";
    private static final String TRIANGLE_TOOL_BUTTON_TEXT = "Triangle";
    private static final String SPIRAL_TOOL_BUTTON_TEXT = "Spiral";
    private static final String BRIGHTNESS_CONTRAST_BUTTON_TEXT = "Brightness/Contrast";
    private static final String HUE_SATURATION_BUTTON_TEXT = "Hue/Saturation";

    private JFrame mainFrame;

    private static final String APPLICATION_NAME = "BetaPaint";
    private static final int APPLICATION_WIDTH = 1920;
    private static final int APPLICATION_HEIGHT = 1080;
    private static final int EDITOR_PANEL_WIDTH = 400;
    private static final int EDITOR_PANEL_HEIGHT = 300;
    private static final int PANEL_SECTION_SPACING = 20;
    private static final int WEST_PANEL_WIDTH = 120;
    private static final int COLOR_PANEL_HEIGHT = 250;
    private static final int IMAGE_EDITING_PANEL_HEIGHT = 180;
    private static final int IMAGE_EDITING_PANEL_WIDTH = 340;
    private static final String DARK_GREY_CANVAS = "#222222";
    private static final int SCROLL_INCREMENT = 15;
    private int canvasWidth;
    private int canvasHeight;

    private JButton clearButton;
    private JButton selectionToolButton;
    private JButton brushToolButton;
    private JButton lineToolButton;
    private JButton rectangleToolButton;
    private JButton eraserToolButton;
    private JButton ellipseToolButton;
    private JButton eyeDropperToolButton;
    private JButton textToolButton;
    private JButton fanToolButton;
    private JButton celticKnotToolButton;
    private JButton paintBucketToolButton;
    private JButton dnaToolButton;
    private JButton airBrushToolButton;
    private JButton triangleToolButton;
    private JButton spiralToolButton;
    private JButton brightnessContrastButton;
    private JButton hueSaturationButton;

    private static DrawArea drawArea;
    private static ColorChooser colorChooser;
    private WidthChanger widthChanger;
    private TextToolSettings textToolSettings;
    private ShortcutDialog keyboardShortCutPanel;
    private Shortcuts shortcuts;
    private ImportExport importExport;
    private JPanel canvasTools;
    private DB_KBShortcuts db_kbShortcuts;
    private JButton highlightedButton;
    private BrightnessContrastMenu brightnessContrastMenu;
    private HueSaturationMenu hueSaturationMenu;
    private LayersPanel layersPanel;
    private MouseCursor mouseCursor;
    private JPanel northPanel;
    private PrintCanvas printCanvas;
    private GreyscaleMenu greyscaleMenu;
    private NoiseGeneratorMenu noiseGeneratorMenu;
    private CheckerboardMenu checkerboardMenu;
    private InvertMenu invertMenu;

    private static final String APPLICATION_LOGO_IMAGE_DIRECTORY = "BPIcon.png";
    private static final String AIR_BRUSH_ICON_DEFAULT = "airbrush.png";
    private static final String AIR_BRUSH_ICON_HIGHLIGHTED = "airbrush_highlighted.png";
    private static final String AIR_BRUSH_ICON_HOVER = "airbrush_hover.png";
    private static final String BRIGHTNESS_ICON_DEFAULT = "brightness.png";
    private static final String BRIGHTNESS_ICON_HIGHLIGHTED = "brightness_highlighted.png";
    private static final String BRIGHTNESS_ICON_HOVER = "brightness_hover.png";
    private static final String BRUSH_ICON_DEFAULT = "brush.png";
    private static final String BRUSH_ICON_HIGHLIGHTED = "brush_highlighted.png";
    private static final String BRUSH_ICON_HOVER = "brush_hover.png";
    private static final String BUCKET_ICON_DEFAULT = "bucket.png";
    private static final String BUCKET_ICON_HIGHLIGHTED = "bucket_highlighted.png";
    private static final String BUCKET_ICON_HOVER = "bucket_hover.png";
    private static final String CELTIC_ICON_DEFAULT = "celtic.png";
    private static final String CELTIC_ICON_HIGHLIGHTED = "celtic_highlighted.png";
    private static final String CELTIC_ICON_HOVER = "celtic_hover.png";
    private static final String CIRCLE_ICON_DEFAULT = "circle.png";
    private static final String CIRCLE_ICON_HIGHLIGHTED = "circle_highlighted.png";
    private static final String CIRCLE_ICON_HOVER = "circle_hover.png";
    private static final String CLEAR_ICON_DEFAULT = "clear_canvas.png";
    private static final String CLEAR_ICON_HIGHLIGHTED = "clear_canvas_highlighted.png";
    private static final String CLEAR_ICON_HOVER = "clear_canvas_hover.png";
    private static final String DNA_ICON_DEFAULT = "dna.png";
    private static final String DNA_ICON_HIGHLIGHTED = "dna_highlighted.png";
    private static final String DNA_ICON_HOVER = "dna_hover.png";
    private static final String ERASER_ICON_DEFAULT = "eraser.png";
    private static final String ERASER_ICON_HIGHLIGHTED = "eraser_highlighted.png";
    private static final String ERASER_ICON_HOVER = "eraser_hover.png";
    private static final String EYEDROP_ICON_DEFAULT = "eyedrop.png";
    private static final String EYEDROP_ICON_HIGHLIGHTED = "eyedrop_highlighted.png";
    private static final String EYEDROP_ICON_HOVER = "eyedrop_hover.png";
    private static final String FAN_ICON_DEFAULT = "fan.png";
    private static final String FAN_ICON_HIGHLIGHTED = "fan_highlighted.png";
    private static final String FAN_ICON_HOVER = "fan_hover.png";
    private static final String HUE_SATURATION_ICON_DEFAULT = "hue_saturation.png";
    private static final String HUE_SATURATION_ICON_HIGHLIGHTED = "hue_saturation_highlighted.png";
    private static final String HUE_SATURATION_ICON_HOVER = "hue_saturation_highlighted.png";
    private static final String LINE_ICON_DEFAULT = "line.png";
    private static final String LINE_ICON_HIGHLIGHTED = "line_highlighted.png";
    private static final String LINE_ICON_HOVER = "line_hover.png";
    private static final String SELECTION_ICON_DEFAULT = "selection.png";
    private static final String SELECTION_ICON_HIGHLIGHTED = "selection_highlighted.png";
    private static final String SELECTION_ICON_HOVER = "selection_hover.png";
    private static final String SPIRAL_ICON_DEFAULT = "spiral.png";
    private static final String SPIRAL_ICON_HIGHLIGHTED = "spiral_highlighted.png";
    private static final String SPIRAL_ICON_HOVER = "spiral_hover.png";
    private static final String SQUARE_ICON_DEFAULT = "square.png";
    private static final String SQUARE_ICON_HIGHLIGHTED = "square_highlighted.png";
    private static final String SQUARE_ICON_HOVER = "square_hover.png";
    private static final String TEXT_ICON_DEFAULT = "text.png";
    private static final String TEXT_ICON_HIGHLIGHTED = "text_highlighted.png";
    private static final String TEXT_ICON_HOVER = "text_hover.png";
    private static final String TRIANGLE_ICON_DEFAULT = "triangle.png";
    private static final String TRIANGLE_ICON_HIGHLIGHTED = "triangle_highlighted.png";
    private static final String TRIANGLE_ICON_HOVER = "triangle_hover.png";

    public static boolean isDatabaseGood = false;

    /**
     * Get the resource URL of the resource
     *
     * @param filename filename
     * @return resource URL
     */
    private URL getRESFile(String filename) {
        return getClass().getClassLoader().getResource(filename);
    }

    private ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                if (drawArea.getCurrentlySelectedLayer().isVisible()) {
                    rectangleSelectionTool.restartSelection();
                    drawArea.clear();
                }
            } else if (e.getSource() == brushToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = brushTool;
                setHighlightedToDefault();
                highlightedButton = brushToolButton;
                brushToolButton.setIcon(new ImageIcon(getRESFile(BRUSH_ICON_HIGHLIGHTED)));
                updateSizeSlider();
                drawArea.setColor(brushTool.getColor());
            } else if (e.getSource() == fanToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = fanTool;
                setHighlightedToDefault();
                highlightedButton = fanToolButton;
                fanToolButton.setIcon(new ImageIcon(getRESFile(FAN_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == lineToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = lineTool;
                setHighlightedToDefault();
                highlightedButton = lineToolButton;
                lineToolButton.setIcon(new ImageIcon(getRESFile(LINE_ICON_HIGHLIGHTED)));
                updateSizeSlider();
                drawArea.setColor(lineTool.getColor());
            } else if (e.getSource() == rectangleToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = rectangleTool;
                setHighlightedToDefault();
                highlightedButton = rectangleToolButton;
                rectangleToolButton.setIcon(new ImageIcon(getRESFile(SQUARE_ICON_HIGHLIGHTED)));
                updateSizeSlider();
                updateFillState(); // Tool supports filling
                drawArea.setColor(rectangleTool.getColor());
            } else if (e.getSource() == widthChanger.getJTextFieldComponent()) {
                widthChanger.setSliderComponent(widthChanger.getJTextFieldValue());
                widthChanger.setCurrentWidthValue(widthChanger.getJTextFieldValue());
                drawArea.setColor(brushTool.getColor());
            } else if (e.getSource() == ellipseToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = ellipseTool;
                setHighlightedToDefault();
                highlightedButton = ellipseToolButton;
                ellipseToolButton.setIcon(new ImageIcon(getRESFile(CIRCLE_ICON_HIGHLIGHTED)));
                updateSizeSlider();
                updateFillState(); // Tool supports filling
                drawArea.setColor(ellipseTool.getColor());
            } else if (e.getSource() == eraserToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = eraserTool;
                setHighlightedToDefault();
                highlightedButton = eraserToolButton;
                eraserToolButton.setIcon(new ImageIcon(getRESFile(ERASER_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == paintBucketToolButton) {
                widthChanger.hideTextPanel();
                selectedDrawingTool = paintBucketTool;
                setHighlightedToDefault();
                highlightedButton = paintBucketToolButton;
                paintBucketToolButton.setIcon(new ImageIcon(getRESFile(BUCKET_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == eyeDropperToolButton) {
                widthChanger.hideTextPanel();
                selectedDrawingTool = eyeDropperTool;
                setHighlightedToDefault();
                highlightedButton = eyeDropperToolButton;
                eyeDropperToolButton.setIcon(new ImageIcon(getRESFile(EYEDROP_ICON_HIGHLIGHTED)));
            } else if (e.getSource() == celticKnotToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = celticKnotTool;
                setHighlightedToDefault();
                highlightedButton = celticKnotToolButton;
                celticKnotToolButton.setIcon(new ImageIcon(getRESFile(CELTIC_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == dnaToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = dnaTool;
                setHighlightedToDefault();
                highlightedButton = dnaToolButton;
                dnaToolButton.setIcon(new ImageIcon(getRESFile(DNA_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == airBrushToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = airBrushTool;
                setHighlightedToDefault();
                highlightedButton = airBrushToolButton;
                airBrushToolButton.setIcon(new ImageIcon(getRESFile(AIR_BRUSH_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == widthChanger.getCheckBoxGlobalSizeComponent()) {
                if (widthChanger.isGlobalSize()) {
                    widthChanger.setGlobalSize(false);
                } else {
                    widthChanger.setGlobalSize(true);
                }
            } else if (e.getSource() == textToolSettings.getFontSelector()) {
                textTool.setFont(textToolSettings.getFontFromSelector());
            } else if (e.getSource() == textToolSettings.getCaesarSelector()) {
                textTool.setCaesarConvert(textToolSettings.isCaesarSelected());
                if (textToolSettings.isCaesarSelected()) {
                    textToolSettings.enableCaesarShiftField(true);
                } else {
                    textToolSettings.enableCaesarShiftField(false);
                }
            } else if (e.getSource() == textToolSettings.getMorseCodeSelector()) {
                textTool.setMorseConvert(textToolSettings.isMorseSelected());
                if (!textToolSettings.isCaesarSelected()) {
                    textToolSettings.enableCaesarShiftField(false);
                } else {
                    textToolSettings.enableCaesarShiftField(true);
                }
            } else if (e.getSource() == widthChanger.getFillBox()) {
                widthChanger.setFill(!widthChanger.isFill());
                selectedDrawingTool.setFillState(widthChanger.isFill());
            } else if (e.getSource() == triangleToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = triangleTool;
                setHighlightedToDefault();
                highlightedButton = triangleToolButton;
                triangleToolButton.setIcon(new ImageIcon(getRESFile(TRIANGLE_ICON_HIGHLIGHTED)));
                updateSizeSlider();
                updateFillState();
            } else if (e.getSource() == spiralToolButton) {
                widthChanger.showTextPanel();
                selectedDrawingTool = spiralTool;
                setHighlightedToDefault();
                highlightedButton = spiralToolButton;
                spiralToolButton.setIcon(new ImageIcon(getRESFile(SPIRAL_ICON_HIGHLIGHTED)));
                updateSizeSlider();
            } else if (e.getSource() == brightnessContrastButton) {
                brightnessContrastMenu.showWindow();
            } else if (e.getSource() == hueSaturationButton) {
                hueSaturationMenu.showWindow();
            } else if (e.getSource() == selectionToolButton) {
                widthChanger.hideTextPanel();
                selectedDrawingTool = rectangleSelectionTool;
                setHighlightedToDefault();
                highlightedButton = selectionToolButton;
                selectionToolButton.setIcon(new ImageIcon(getRESFile(SELECTION_ICON_HIGHLIGHTED)));
                textToolSettings.setVisibility(false);
                northPanel.remove(textToolSettings);
                northPanel.validate();
                rectangleSelectionTool.showPanel();
                northPanel.add(rectangleSelectionTool.getSelectionOptionPanel(), BorderLayout.EAST);
                northPanel.validate();
                updateSizeSlider();
                updateFillState();
            }

            if (e.getSource() == textToolButton) {
                widthChanger.showTextPanel();
                updateSizeSlider();
                rectangleSelectionTool.hidePanel();
                northPanel.remove(rectangleSelectionTool.getSelectionOptionPanel());
                northPanel.add(textToolSettings, BorderLayout.EAST);
                northPanel.validate();
                selectedDrawingTool = textTool;
                setHighlightedToDefault();
                highlightedButton = textToolButton;
                textToolButton.setIcon(new ImageIcon(getRESFile(TEXT_ICON_HIGHLIGHTED)));
                textToolSettings.setVisibility(true);

            } else if ((e.getSource() != textToolButton && e.getSource() != clearButton)
                    && (e.getSource() instanceof JButton)) {
                textToolSettings.setVisibility(false);
            }

            if (selectedDrawingTool != rectangleSelectionTool && e.getSource() != clearButton && e.getSource() instanceof JButton) {
                rectangleSelectionTool.restartSelection();
                rectangleSelectionTool.hidePanel();
                mouseCursor.setDefaultCursor();
                drawArea.setFocusable(true);
                drawArea.validate();
            }

        }
    };

    /**
     * Constructor.
     */
    public MainUI(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        drawArea = new DrawArea(this);
        mouseCursor = new MouseCursor(drawArea);
        initDrawingTools();
        prepareGUI();
    }

    /**
     * Create the drawing tool objects and set the pen tool as the default selection.
     */
    private void initDrawingTools() {
        textToolSettings = new TextToolSettings();
        lineTool = new LineTool();
        brushTool = new BrushTool();
        rectangleTool = new RectangleTool();
        eraserTool = new EraserTool(); // Requires drawArea due to requiring the canvas colour.
        ellipseTool = new EllipseTool();
        eyeDropperTool = new EyeDropperTool(); // Requires widthChanger UI element for direct text update.
        textTool = new TextTool();
        fanTool = new FanTool();
        celticKnotTool = new CelticKnotTool();
        paintBucketTool = new PaintBucketTool();
        dnaTool = new DNATool();
        airBrushTool = new AirBrushTool();
        triangleTool = new TriangleTool();
        spiralTool = new SpiralTool();
        rectangleSelectionTool = new RectangleSelectionTool(mouseCursor);
        selectedDrawingTool = brushTool;
    }

    /**
     * Class to listen for changes in the widthChanger slider.
     */
    public class listenForSlider implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == widthChanger.getSliderComponent()) {
                selectedDrawingTool.setToolWidth(widthChanger.getCurrentWidthValue());
                widthChanger.setCurrentWidthValue(selectedDrawingTool.getToolWidth());
                widthChanger.setJLabel();
            }
        }
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * When a new brushTool is selected this method
     * will update the size panel to the current brush tool
     * values
     * if global is ticked then all the tool sizes change to current size
     * if global not ticked the component size can be different for each tool
     */
    public void updateSizeSlider() {
        if (!widthChanger.isGlobalSize()) {
            widthChanger.setCurrentWidthValue(selectedDrawingTool.getToolWidth());
            widthChanger.setSliderComponent(selectedDrawingTool.getToolWidth());
            widthChanger.setJLabel();
        } else {
            selectedDrawingTool.setToolWidth(widthChanger.getCurrentWidthValue());
            widthChanger.setCurrentWidthValue(selectedDrawingTool.getToolWidth());
            widthChanger.setJLabel();
        }
    }

    /**
     * When a new tool is selected, tools that support filling will set their
     * fill state to the fill checkbox.
     */
    public void updateFillState() {
        selectedDrawingTool.setFillState(widthChanger.isFill());
    }

    /**
     * This is called when the 'x' is pressed.
     */
    private void exit() {
        Object[] exitOptions = {"Cancel",
                "Export canvas",
                "Exit without exporting"};


        int confirmed = JOptionPane.showOptionDialog(null,
                "Are you sure you want to quit?", "Confirm Quit",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, exitOptions, null);

        ImportExport importExport = new ImportExport(drawArea, this);

        if (confirmed == JOptionPane.CANCEL_OPTION) {
            mainFrame.dispose();
            //System.exit(0);
        }
        if (confirmed == JOptionPane.NO_OPTION) {
            importExport.exportImage();
            //mainFrame.dispose();  Ideally would use this instead of the following line, but for some reason it
            //wont properly close the app. If someone knows why, feel free to fix it
            System.exit(0);

        }
    }

    /**
     * Build main GUI.
     */
    private void prepareGUI() {
        initializeMainFrame();

        Container mainContent = mainFrame.getContentPane();
        mainContent.setLayout(new BorderLayout());


        importExport = new ImportExport(drawArea, this);
        brightnessContrastMenu = new BrightnessContrastMenu(drawArea);
        hueSaturationMenu = new HueSaturationMenu(drawArea);
        greyscaleMenu = new GreyscaleMenu(drawArea);
        invertMenu = new InvertMenu(drawArea);
        noiseGeneratorMenu = new NoiseGeneratorMenu(drawArea);
        checkerboardMenu = new CheckerboardMenu(drawArea);

        initializeDrawArea(mainContent);
        initializeButtons();
        initializeCanvasTools();
        brushToolButton.setIcon(new ImageIcon(getRESFile(BRUSH_ICON_HIGHLIGHTED)));
        printCanvas = new PrintCanvas(drawArea, this);

        /* END MAIN UI BUTTONS */
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        //setting up the shortcuts and database
        shortcuts = new Shortcuts(canvasTools, this);
        db_kbShortcuts = new DB_KBShortcuts(shortcuts);
        keyboardShortCutPanel = new ShortcutDialog(this, shortcuts);

        MenuUI menuUI = new MenuUI(mainFrame, drawArea, importExport, greyscaleMenu, hueSaturationMenu, brightnessContrastMenu,
                noiseGeneratorMenu, checkerboardMenu, keyboardShortCutPanel, printCanvas, invertMenu);


        northPanel.add(menuUI, BorderLayout.NORTH);
        initializeToolSettings(northPanel);
        initializeWidthChanger();

        JScrollPane westPanel = initializeWestPanel();
        mainContent.add(westPanel, BorderLayout.WEST);

        JPanel colorPanel = initializeColorPanel();
        JPanel imageEditingPanel = initializeImageEditingPanel();
        JPanel editorPanel = initializeEditorPanel(colorPanel, imageEditingPanel);
        mainContent.add(editorPanel, BorderLayout.EAST);
        mainContent.add(northPanel, BorderLayout.NORTH);

        // Standard ActionListeners do not properly send updates to the text tool. PropertyChangeListeners work better.
        textToolSettings.getCaesarShiftField().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                textTool.setCaesarShiftValue(textToolSettings.getCaesarShiftValue());
            }
        });

        //drop table if shortcuts are changed
        //  db_kbShortcuts.dropTable();
        isDatabaseGood = db_kbShortcuts.testConnection();
        if (isDatabaseGood) {

            if (db_kbShortcuts.isTableExists()) {
                generateDBDefaultKeyBindings();
                db_kbShortcuts.generateDBKeyBindings();
            } else {
                db_kbShortcuts.createTable();
                generateDefaultKeyBindings();
            }

        } else {
            generateDefaultKeyBindings();
        }

        if (rectangleSelectionTool != null) {
            rectangleSelectionTool.renderPanel();
            northPanel.add(rectangleSelectionTool.getSelectionOptionPanel(), BorderLayout.EAST);
            rectangleSelectionTool.hidePanel();
        }
    }

    /**
     * Set up main frame of the application.
     */
    private void initializeMainFrame() {
        mainFrame = new JFrame(APPLICATION_NAME);
        mainFrame.setIconImage(new ImageIcon(getRESFile(APPLICATION_LOGO_IMAGE_DIRECTORY)).getImage());
        mainFrame.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        mainFrame.getContentPane().setBackground(Color.DARK_GRAY);

        mainFrame.setLocationByPlatform(true);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        if (ImportExport.isExported()) {
                            System.exit(0);
                        } else {
                            exit();
                        }
                    }
                });
    }

    /**
     * Set up draw area component.
     *
     * @param mainContent container.
     */
    private void initializeDrawArea(Container mainContent) {
        JPanel drawAreaPanel = new JPanel();
        drawAreaPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = canvasHeight;
        gridBagConstraints.ipadx = canvasWidth;
        drawAreaPanel.setBackground(Color.decode(DARK_GREY_CANVAS));
        drawAreaPanel.add(drawArea, gridBagConstraints);
        JScrollPane drawAreaScrollPane = new JScrollPane(drawAreaPanel);
        drawAreaScrollPane.getVerticalScrollBar().setBlockIncrement(SCROLL_INCREMENT);
        drawAreaScrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        drawAreaScrollPane.setBorder(createEmptyBorder());
        drawAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        drawAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainContent.add(drawAreaScrollPane, BorderLayout.CENTER);
    }

    /**
     * Initialize tool buttons.
     */
    private void initializeButtons() {
        clearButton = new ToolButton(new ImageIcon(getRESFile(CLEAR_ICON_DEFAULT)));
        selectionToolButton = new ToolButton(new ImageIcon(getRESFile(SELECTION_ICON_DEFAULT)));
        brushToolButton = new ToolButton(new ImageIcon(getRESFile(BRIGHTNESS_ICON_DEFAULT)));
        lineToolButton = new ToolButton(new ImageIcon(getRESFile(LINE_ICON_DEFAULT)));
        rectangleToolButton = new ToolButton(new ImageIcon(getRESFile(SQUARE_ICON_DEFAULT)));
        eraserToolButton = new ToolButton(new ImageIcon(getRESFile(ERASER_ICON_DEFAULT)));
        ellipseToolButton = new ToolButton(new ImageIcon(getRESFile(CIRCLE_ICON_DEFAULT)));
        textToolButton = new ToolButton(new ImageIcon(getRESFile(TEXT_ICON_DEFAULT)));
        eyeDropperToolButton = new ToolButton(new ImageIcon(getRESFile(EYEDROP_ICON_DEFAULT)));
        fanToolButton = new ToolButton(new ImageIcon(getRESFile(FAN_ICON_DEFAULT)));
        celticKnotToolButton = new ToolButton(new ImageIcon(getRESFile(CELTIC_ICON_DEFAULT)));
        paintBucketToolButton = new ToolButton(new ImageIcon(getRESFile(BUCKET_ICON_DEFAULT)));
        dnaToolButton = new ToolButton(new ImageIcon(getRESFile(DNA_ICON_DEFAULT)));
        airBrushToolButton = new ToolButton(new ImageIcon(getRESFile(AIR_BRUSH_ICON_DEFAULT)));
        triangleToolButton = new ToolButton(new ImageIcon(getRESFile(TRIANGLE_ICON_DEFAULT)));
        spiralToolButton = new ToolButton(new ImageIcon(getRESFile(SPIRAL_ICON_DEFAULT)));
        brightnessContrastButton = new ToolButton(new ImageIcon(getRESFile(BRIGHTNESS_ICON_DEFAULT)));
        hueSaturationButton = new ToolButton(new ImageIcon(getRESFile(HUE_SATURATION_ICON_DEFAULT)));
        highlightedButton = brushToolButton;
    }

    /**
     * Setup tool icons and action listeners for canvas tools.
     */
    private void initializeCanvasTools() {
        // Add a button to this array to register to actionListener and canvasTools
        // The order of this list determines the order of the buttons in the generated UI. Index -> 0 = Position ->
        // First
        JButton[] buttonContainer = {
                clearButton, selectionToolButton, brushToolButton, airBrushToolButton, eraserToolButton, lineToolButton,
                spiralToolButton, fanToolButton, rectangleToolButton, ellipseToolButton, triangleToolButton,
                paintBucketToolButton, celticKnotToolButton, dnaToolButton, textToolButton, eyeDropperToolButton,
                brightnessContrastButton, hueSaturationButton
        };
        String[] buttonTextContainer = {
                CLEAR_TOOL_BUTTON_TEXT, SELECTION_TOOL_BUTTON_TEXT, BRUSH_TOOL_BUTTON_TEXT, AIR_BRUSH_TOOL_BUTTON_TEXT,
                ERASER_TOOL_BUTTON_TEXT, LINE_TOOL_BUTTON_TEXT, SPIRAL_TOOL_BUTTON_TEXT, FAN_TOOL_BUTTON_TEXT,
                RECTANGLE_TOOL_BUTTON_TEXT, ELLIPSE_TOOL_BUTTON_TEXT, TRIANGLE_TOOL_BUTTON_TEXT, BUCKET_BUTTON_TEXT,
                CELTIC_KNOT_TOOL_BUTTON_TEXT, DNA_TOOL_BUTTON_TEXT, TEXT_TOOL_BUTTON_TEXT, EYEDROPPER_TOOL_BUTTON_TEXT,
                BRIGHTNESS_CONTRAST_BUTTON_TEXT, HUE_SATURATION_BUTTON_TEXT
        };
        String[] buttonHoverContainer = {
                CLEAR_ICON_HOVER, SELECTION_ICON_HOVER, BRUSH_ICON_HOVER, AIR_BRUSH_ICON_HOVER, ERASER_ICON_HOVER,
                LINE_ICON_HOVER, SPIRAL_ICON_HOVER, FAN_ICON_HOVER, SQUARE_ICON_HOVER, CIRCLE_ICON_HOVER,
                TRIANGLE_ICON_HOVER, BUCKET_ICON_HOVER, CELTIC_ICON_HOVER, DNA_ICON_HOVER, TEXT_ICON_HOVER,
                EYEDROP_ICON_HOVER, BRIGHTNESS_ICON_HOVER, HUE_SATURATION_ICON_HOVER
        };
        String[] buttonDefaultContainer = {
                CLEAR_ICON_DEFAULT, SELECTION_ICON_DEFAULT, BRUSH_ICON_DEFAULT, AIR_BRUSH_ICON_DEFAULT,
                ERASER_ICON_DEFAULT, LINE_ICON_DEFAULT, SPIRAL_ICON_DEFAULT, FAN_ICON_DEFAULT, SQUARE_ICON_DEFAULT,
                CIRCLE_ICON_DEFAULT, TRIANGLE_ICON_DEFAULT, BUCKET_ICON_DEFAULT, CELTIC_ICON_DEFAULT, DNA_ICON_DEFAULT,
                TEXT_ICON_DEFAULT, EYEDROP_ICON_DEFAULT, BRIGHTNESS_ICON_DEFAULT, HUE_SATURATION_ICON_DEFAULT
        };
        String[] buttonHighlightedContainer = {
                CLEAR_ICON_HIGHLIGHTED, SELECTION_ICON_HIGHLIGHTED, BRUSH_ICON_HIGHLIGHTED, AIR_BRUSH_ICON_HIGHLIGHTED,
                ERASER_ICON_HIGHLIGHTED, LINE_ICON_HIGHLIGHTED, SPIRAL_ICON_HIGHLIGHTED, FAN_ICON_HIGHLIGHTED,
                SQUARE_ICON_HIGHLIGHTED, CIRCLE_ICON_HIGHLIGHTED, TRIANGLE_ICON_HIGHLIGHTED, BUCKET_ICON_HIGHLIGHTED,
                CELTIC_ICON_HIGHLIGHTED, DNA_ICON_HIGHLIGHTED, TEXT_ICON_HIGHLIGHTED, EYEDROP_ICON_HIGHLIGHTED,
                BRIGHTNESS_ICON_HIGHLIGHTED, HUE_SATURATION_ICON_HIGHLIGHTED
        };

        canvasTools = new JPanel();
        canvasTools.setLayout(new BoxLayout(canvasTools, BoxLayout.Y_AXIS));
        canvasTools.setBackground(Color.DARK_GRAY);
        canvasTools.add(Box.createRigidArea(new Dimension(0, PANEL_SECTION_SPACING)));

        int buttonContainerIndex = 0;
        // Register buttons to actionListener and canvasTools
        for (JButton button : buttonContainer) {
            button.addActionListener(actionListener);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setToolTipText(buttonTextContainer[buttonContainerIndex]);
            int CURRENT_CONTAINER_INDEX = buttonContainerIndex;
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setIcon(new ImageIcon(getRESFile(buttonHoverContainer[CURRENT_CONTAINER_INDEX])));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (button != highlightedButton) {
                        button.setIcon(new ImageIcon(getRESFile(buttonDefaultContainer[CURRENT_CONTAINER_INDEX])));
                    } else {
                        button.setIcon(new ImageIcon(getRESFile(buttonHighlightedContainer[CURRENT_CONTAINER_INDEX])));
                    }
                }
            });
            buttonContainerIndex++;
            if (button != eyeDropperToolButton && button != brightnessContrastButton && button != hueSaturationButton) {
                canvasTools.add(button);
                canvasTools.add(Box.createRigidArea(new Dimension(0, PANEL_SECTION_SPACING)));
            }
        }
        canvasTools.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    /**
     * Set up tool settings panel.
     *
     * @param northPanel for the application.
     */
    private void initializeToolSettings(JPanel northPanel) {
        JPanel toolSettings = new JPanel();
        toolSettings.setLayout(new BoxLayout(toolSettings, BoxLayout.X_AXIS));
        toolSettings.setBackground(Color.DARK_GRAY);
        toolSettings.add(Box.createRigidArea(new Dimension(WEST_PANEL_WIDTH, 0)));
        widthChanger = new WidthChanger();
        toolSettings.add(widthChanger.getGUI());
        northPanel.add(toolSettings, BorderLayout.CENTER);

        if (textToolSettings != null) {
            northPanel.add(textToolSettings, BorderLayout.EAST);
            textTool.setFont(textToolSettings.getFontFromSelector());
        }
    }

    /**
     * Set up width changer for tools.
     */
    private void initializeWidthChanger() {
        listenForSlider listenForSlider = new listenForSlider();
        widthChanger.getSliderComponent().addChangeListener(listenForSlider);
        widthChanger.getJTextFieldComponent().addActionListener(actionListener);
        textToolSettings.addActionListener(actionListener);
        widthChanger.getCheckBoxGlobalSizeComponent().addActionListener(actionListener);
        widthChanger.getFillBox().addActionListener(actionListener);
    }

    /**
     * Set up color panel.
     *
     * @return color panel.
     */
    private JPanel initializeColorPanel() {
        JPanel colorPanel = new JPanel();
        colorChooser = new ColorChooser();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
        colorPanel.setBackground(Color.DARK_GRAY);
        colorPanel.setMaximumSize(new Dimension(EDITOR_PANEL_WIDTH, COLOR_PANEL_HEIGHT));
        colorPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        JPanel colorChooserPanel = new JPanel();
        colorChooserPanel.setLayout(new GridBagLayout());
        GridBagConstraints colorChooserConstraints = new GridBagConstraints();
        colorChooserPanel.setBackground(Color.DARK_GRAY);
        colorChooserConstraints.insets = new Insets(PANEL_SECTION_SPACING, 0, 0, 0);
        colorChooserPanel.add(eyeDropperToolButton, colorChooserConstraints);
        colorChooserConstraints.gridx = 1;
        colorChooserConstraints.gridy = 0;
        colorChooserConstraints.insets = new Insets(PANEL_SECTION_SPACING, PANEL_SECTION_SPACING, 0, 0);
        colorChooserPanel.add(colorChooser, colorChooserConstraints);
        eyeDropperStats = new EyeDropperStats();
        colorChooserConstraints.gridx = 2;
        colorChooserConstraints.gridy = 0;
        colorChooserConstraints.insets = new Insets(PANEL_SECTION_SPACING, PANEL_SECTION_SPACING, 0, 0);
        colorChooserPanel.add(eyeDropperStats, colorChooserConstraints);
        colorPanel.add(colorChooserPanel);

        return colorPanel;
    }

    /**
     * Set up image editing panel consisting of the brightness/contrast and hue/saturation buttons.
     *
     * @return image editing panel of the application.
     */
    private JPanel initializeImageEditingPanel() {
        JPanel imageEditingPanel = new JPanel();
        imageEditingPanel.setLayout(new GridBagLayout());
        imageEditingPanel.setBackground(Color.DARK_GRAY);
        imageEditingPanel.setMaximumSize(new Dimension(IMAGE_EDITING_PANEL_WIDTH,
                IMAGE_EDITING_PANEL_HEIGHT));
        imageEditingPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        GridBagConstraints imageButtonConstraints = new GridBagConstraints();
        imageButtonConstraints.insets = new Insets(PANEL_SECTION_SPACING, PANEL_SECTION_SPACING, 0, 0);
        imageEditingPanel.add(brightnessContrastButton, imageButtonConstraints);
        imageButtonConstraints.gridx = 1;
        imageButtonConstraints.gridy = 0;
        imageButtonConstraints.insets = new Insets(PANEL_SECTION_SPACING, PANEL_SECTION_SPACING, 0, 0);
        imageEditingPanel.add(hueSaturationButton, imageButtonConstraints);

        return imageEditingPanel;
    }

    /**
     * Set up editor panel.
     *
     * @return editor panel of the application.
     */
    private JPanel initializeEditorPanel(JPanel colorPanel, JPanel imageEditingPanel) {
        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));
        editorPanel.setBackground(Color.DARK_GRAY);
        editorPanel.setPreferredSize(new Dimension(EDITOR_PANEL_WIDTH, EDITOR_PANEL_HEIGHT));
        editorPanel.add(colorPanel);
        editorPanel.add(Box.createRigidArea(new Dimension(0, PANEL_SECTION_SPACING)));
        editorPanel.add(imageEditingPanel);
        editorPanel.add(Box.createRigidArea(new Dimension(0, PANEL_SECTION_SPACING)));
        layersPanel = new LayersPanel(drawArea, rectangleSelectionTool);
        editorPanel.add(layersPanel);
        return editorPanel;
    }

    /**
     * Set up west panel with a scroll bar (if needed).
     *
     * @return scrollable west panel of the application.
     */
    private JScrollPane initializeWestPanel() {
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setMaximumSize(new Dimension(WEST_PANEL_WIDTH, Integer.MAX_VALUE));
        westPanel.setBackground(Color.DARK_GRAY);
        westPanel.add(canvasTools);

        JScrollPane scrollPaneWestPanel = new JScrollPane(westPanel);
        scrollPaneWestPanel.setPreferredSize(new Dimension(WEST_PANEL_WIDTH, 0));
        scrollPaneWestPanel.setBorder(createEmptyBorder());
        scrollPaneWestPanel.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        scrollPaneWestPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneWestPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        return scrollPaneWestPanel;
    }

    /**
     * Sets highlighted tool back to its default icon.
     */
    private void setHighlightedToDefault() {
        if (highlightedButton != null) {
            if (highlightedButton == selectionToolButton) {
                selectionToolButton.setIcon(new ImageIcon(getRESFile(SELECTION_ICON_DEFAULT)));
            } else if (highlightedButton == brushToolButton) {
                brushToolButton.setIcon(new ImageIcon(getRESFile(BRUSH_ICON_DEFAULT)));
            } else if (highlightedButton == airBrushToolButton) {
                airBrushToolButton.setIcon(new ImageIcon(getRESFile(AIR_BRUSH_ICON_DEFAULT)));
            } else if (highlightedButton == eraserToolButton) {
                eraserToolButton.setIcon(new ImageIcon(getRESFile(ERASER_ICON_DEFAULT)));
            } else if (highlightedButton == lineToolButton) {
                lineToolButton.setIcon(new ImageIcon(getRESFile(LINE_ICON_DEFAULT)));
            } else if (highlightedButton == fanToolButton) {
                fanToolButton.setIcon(new ImageIcon(getRESFile(FAN_ICON_DEFAULT)));
            } else if (highlightedButton == rectangleToolButton) {
                rectangleToolButton.setIcon(new ImageIcon(getRESFile(SQUARE_ICON_DEFAULT)));
            } else if (highlightedButton == ellipseToolButton) {
                ellipseToolButton.setIcon(new ImageIcon(getRESFile(CIRCLE_ICON_DEFAULT)));
            } else if (highlightedButton == triangleToolButton) {
                triangleToolButton.setIcon(new ImageIcon(getRESFile(TRIANGLE_ICON_DEFAULT)));
            } else if (highlightedButton == paintBucketToolButton) {
                paintBucketToolButton.setIcon(new ImageIcon(getRESFile(BUCKET_ICON_DEFAULT)));
            } else if (highlightedButton == celticKnotToolButton) {
                celticKnotToolButton.setIcon(new ImageIcon(getRESFile(CELTIC_ICON_DEFAULT)));
            } else if (highlightedButton == dnaToolButton) {
                dnaToolButton.setIcon(new ImageIcon(getRESFile(DNA_ICON_DEFAULT)));
            } else if (highlightedButton == textToolButton) {
                textToolButton.setIcon(new ImageIcon(getRESFile(TEXT_ICON_DEFAULT)));
            } else if (highlightedButton == eyeDropperToolButton) {
                eyeDropperToolButton.setIcon(new ImageIcon(getRESFile(EYEDROP_ICON_DEFAULT)));
            } else if (highlightedButton == spiralToolButton) {
                spiralToolButton.setIcon(new ImageIcon(getRESFile(SPIRAL_ICON_DEFAULT)));
            }
        }
    }

    /**
     * Return the currently selected drawing tool.
     *
     * @return selected drawing tool.
     */
    public static DrawingTool getSelectedDrawingTool() {
        return selectedDrawingTool;
    }

    /**
     * Display GUI.
     */
    public void displayUI() {
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setLocationRelativeTo(null);  // positions GUI in center when opened
        mainFrame.setVisible(true);
    }

    /**
     * Returns eyedropper stats object.
     *
     * @return eyedropper stats object
     */
    public static ColorChooser getColorChooser() {
        return colorChooser;
    }

    /**
     * Get the layers panel.
     *
     * @return layers panel.
     */
    public LayersPanel getLayersPanel() {
        return layersPanel;
    }

    /**
     * Temprorary fix to allow the eraser tool to work no matter the order of creation.
     *
     * @return The UI drawArea
     */
    public static DrawArea getDrawArea() {
        return drawArea;
    }

    /**
     * generates the default key binding for shortcut keys
     */
    public void generateDefaultKeyBindings() {
        shortcuts.addKeyBinding(KeyEvent.VK_C, true, false, false,
                Shortcuts.CLEAR_TOOL_SHORTCUT, (evt) -> {
                    rectangleSelectionTool.restartSelection();
                    setHighlightedToDefault();
                    highlightedButton = clearButton;
                    drawArea.clear();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_O, false, true, false,
                Shortcuts.EXPORT_SHORTCUT, (evt) -> {
                    importExport.exportImage();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_I, false, true, false,
                Shortcuts.IMPORT_SHORTCUT, (evt) -> {
                    importExport.importImage();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_B, true, false, false,
                Shortcuts.BRUSH_TOOL_SHORTCUT, (evt) -> {
                    widthChanger.showPanel();
                    selectedDrawingTool = brushTool;
                    setHighlightedToDefault();
                    highlightedButton = brushToolButton;
                    brushToolButton.setIcon(new ImageIcon(getRESFile(BRUSH_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    drawArea.setColor(brushTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_L, true, false, false,
                Shortcuts.LINE_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = lineTool;
                    setHighlightedToDefault();
                    highlightedButton = lineToolButton;
                    lineToolButton.setIcon(new ImageIcon(getRESFile(LINE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    drawArea.setColor(lineTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_R, true, false, false,
                Shortcuts.RECTANGLE_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = rectangleTool;
                    setHighlightedToDefault();
                    highlightedButton = rectangleToolButton;
                    rectangleToolButton.setIcon(new ImageIcon(getRESFile(SQUARE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateFillState(); // Tool supports filling
                    drawArea.setColor(rectangleTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_A, true, false, false,
                Shortcuts.AIRBRUSH_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = airBrushTool;
                    setHighlightedToDefault();
                    highlightedButton = airBrushToolButton;
                    airBrushToolButton.setIcon(new ImageIcon(getRESFile(AIR_BRUSH_ICON_DEFAULT)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_K, true, false, false,
                Shortcuts.CELTICKNOT_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = celticKnotTool;
                    setHighlightedToDefault();
                    highlightedButton = celticKnotToolButton;
                    celticKnotToolButton.setIcon(new ImageIcon(getRESFile(CELTIC_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        /*
        //TODO FIX
        shortcuts.addKeyBinding(KeyEvent.VK_C, false, false, true, Shortcuts.COLOR_CHOOSER_TOOL_SHORTCUT, (evt) -> {
            // colorPanel.setVisible(true);
            Color color = null;
            color = JColorChooser.showDialog(null, "Select a Color", color);
            updateSizeSlider();
        });*/
        shortcuts.addKeyBinding(KeyEvent.VK_D, true, false, false,
                Shortcuts.DNA_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = dnaTool;
                    setHighlightedToDefault();
                    highlightedButton = dnaToolButton;
                    dnaToolButton.setIcon(new ImageIcon(getRESFile(DNA_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_Q, true, false, false,
                Shortcuts.ELLIPSE_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = ellipseTool;
                    setHighlightedToDefault();
                    highlightedButton = ellipseToolButton;
                    ellipseToolButton.setIcon(new ImageIcon(getRESFile(CIRCLE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateFillState(); // Tool supports filling
                    drawArea.setColor(ellipseTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_E, true, false, false,
                Shortcuts.ERASER_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = eraserTool;
                    setHighlightedToDefault();
                    highlightedButton = eraserToolButton;
                    eraserToolButton.setIcon(new ImageIcon(getRESFile(ERASER_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_Y, true, false, false,
                Shortcuts.EYE_DROP_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = eyeDropperTool;
                    setHighlightedToDefault();
                    highlightedButton = eyeDropperToolButton;
                    eyeDropperToolButton.setIcon(new ImageIcon(getRESFile(EYEDROP_ICON_HIGHLIGHTED)));
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_F, true, false, false,
                Shortcuts.FAN_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = fanTool;
                    setHighlightedToDefault();
                    highlightedButton = fanToolButton;
                    fanToolButton.setIcon(new ImageIcon(getRESFile(FAN_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_P, true, false, false,
                Shortcuts.PAINTBUCKET_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = paintBucketTool;
                    setHighlightedToDefault();
                    highlightedButton = paintBucketToolButton;
                    paintBucketToolButton.setIcon(new ImageIcon(getRESFile(BUCKET_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_S, true, false, false,
                Shortcuts.SELECTION_TOOL_SHORTCUT, (evt) -> {
                    widthChanger.hideTextPanel();
                    selectedDrawingTool = rectangleSelectionTool;
                    setHighlightedToDefault();
                    highlightedButton = selectionToolButton;
                    selectionToolButton.setIcon(new ImageIcon(getRESFile(SELECTION_ICON_HIGHLIGHTED)));
                    textToolSettings.setVisibility(false);
                    northPanel.remove(textToolSettings);
                    northPanel.validate();
                    rectangleSelectionTool.showPanel();
                    northPanel.add(rectangleSelectionTool.getSelectionOptionPanel(), BorderLayout.EAST);
                    northPanel.validate();
                    updateSizeSlider();
                    updateFillState();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_T, true, false, false,
                Shortcuts.TEXT_TOOL_SHORTCUT, (evt) -> {
                    rectangleSelectionTool.hidePanel();
                    northPanel.remove(rectangleSelectionTool.getSelectionOptionPanel());
                    northPanel.add(textToolSettings, BorderLayout.EAST);
                    widthChanger.showTextPanel();
                    updateSizeSlider();
                    northPanel.validate();
                    selectedDrawingTool = textTool;
                    setHighlightedToDefault();
                    highlightedButton = textToolButton;
                    textToolButton.setIcon(new ImageIcon(getRESFile(TEXT_ICON_HIGHLIGHTED)));
                    textToolSettings.setVisibility(true);

                });
        shortcuts.addKeyBinding(KeyEvent.VK_Z, true, false, false,
                Shortcuts.TRIANGLE_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = triangleTool;
                    setHighlightedToDefault();
                    highlightedButton = triangleToolButton;
                    triangleToolButton.setIcon(new ImageIcon(getRESFile(TRIANGLE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateFillState();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_P, false, true, false, Shortcuts.PRINT_SHORTCUT,
                (evt) -> {
                    printCanvas.getPrintDimensionsDialog();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_S, false, true, false, Shortcuts.SHORTCUT_SHORTCUT,
                (evt) -> {
                    keyboardShortCutPanel.renderPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_C, false, true, false, Shortcuts.CLOSE_SHORTCUT,
                (evt) -> {
                    System.exit(0);
                });
        shortcuts.addKeyBinding(KeyEvent.VK_N, false, true, false, Shortcuts.NEW_SHORTCUT,
                (evt) -> {
                    Dimension dimension = NewWindow.displayPrompt();
                    if (dimension != null) {
                        final int CANVAS_WIDTH = (int) dimension.getWidth();
                        final int CANVAS_HEIGHT = (int) dimension.getHeight();
                        MainUI mainUI = new MainUI(CANVAS_WIDTH, CANVAS_HEIGHT);
                        mainUI.displayUI();
                        mainFrame.dispose();
                    }
                });
        shortcuts.addKeyBinding(KeyEvent.VK_G, false, true, false, Shortcuts.GREYSCALE_SHORTCUT,
                (evt) -> {
                    greyscaleMenu.showWindow();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_B, false, true, false, Shortcuts.BRIGHTNESS_SHORTCUT,
                (evt) -> {
                    brightnessContrastMenu.showWindow();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_A, false, true, false, Shortcuts.HUE_SATURATION_SHORTCUT,
                (evt) -> {
                    hueSaturationMenu.showWindow();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_K, false, true, false, Shortcuts.CHECKERBOARD_SHORTCUT,
                (evt) -> {
                    checkerboardMenu.showWindow();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_E, false, true, false, Shortcuts.NOISE_SHORTCUT,
                (evt) -> {
                    noiseGeneratorMenu.showWindow();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_I, true, false, false, Shortcuts.SPIRAL_TOOL_SHORTCUT,
                (evt) -> {
                    selectedDrawingTool = spiralTool;
                    setHighlightedToDefault();
                    highlightedButton = spiralToolButton;
                    spiralToolButton.setIcon(new ImageIcon(getRESFile(SPIRAL_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(KeyEvent.VK_V, false, true, false, Shortcuts.INVERT_SHORTCUT,
                (evt) -> {
                    invertMenu.showWindow();
                });
    }

    /**
     * gets the key binding for the shortcuts from the database
     */
    public void generateDBDefaultKeyBindings() {
        shortcuts.addKeyBinding(Shortcuts.getClearToolKeyCode(), Shortcuts.isAlt_clearTool(),
                Shortcuts.isShift_clearTool(), Shortcuts.isAlt_clearTool(), Shortcuts.CLEAR_TOOL_SHORTCUT, (evt) -> {
                    rectangleSelectionTool.restartSelection();
                    setHighlightedToDefault();
                    highlightedButton = clearButton;
                    drawArea.clear();
                });
        shortcuts.addKeyBinding(shortcuts.getExportKeyCode(), shortcuts.isCtrl_export(), shortcuts.isShift_export(),
                shortcuts.isAlt_export(), Shortcuts.EXPORT_SHORTCUT, (evt) -> {
                    importExport.exportImage();
                });
        shortcuts.addKeyBinding(shortcuts.getImportKeyCode(), shortcuts.isCtrl_import(), shortcuts.isShift_import(),
                shortcuts.isAlt_import(), Shortcuts.IMPORT_SHORTCUT, (evt) -> {
                    importExport.importImage();
                });
        shortcuts.addKeyBinding(shortcuts.getBrushToolKeyCode(), shortcuts.isCtrl_brushTool(),
                shortcuts.isShift_brushTool(), shortcuts.isAlt_brushTool(), shortcuts.BRUSH_TOOL_SHORTCUT, (evt) -> {
                    widthChanger.showPanel();
                    selectedDrawingTool = brushTool;
                    setHighlightedToDefault();
                    highlightedButton = brushToolButton;
                    brushToolButton.setIcon(new ImageIcon(getRESFile(BRUSH_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    drawArea.setColor(brushTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getLineToolKeyCode(), shortcuts.isCtrl_lineTool(),
                shortcuts.isShift_lineTool(), shortcuts.isAlt_lineTool(), Shortcuts.LINE_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = lineTool;
                    setHighlightedToDefault();
                    highlightedButton = lineToolButton;
                    lineToolButton.setIcon(new ImageIcon(getRESFile(LINE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    drawArea.setColor(lineTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getAirBrushToolKeyCode(), shortcuts.isCtrl_airBrushTool(),
                shortcuts.isShift_airBrushTool(), shortcuts.isAlt_airBrushTool(), Shortcuts.AIRBRUSH_TOOL_SHORTCUT,
                (evt) -> {
                    selectedDrawingTool = airBrushTool;
                    setHighlightedToDefault();
                    highlightedButton = airBrushToolButton;
                    airBrushToolButton.setIcon(new ImageIcon(getRESFile(AIR_BRUSH_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getCelticKnotToolKeyCode(), shortcuts.isCtrl_celticKnotTool(),
                shortcuts.isShift_celticKnotTool(), shortcuts.isAlt_celticKnotTool(),
                Shortcuts.CELTICKNOT_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = celticKnotTool;
                    setHighlightedToDefault();
                    highlightedButton = celticKnotToolButton;
                    celticKnotToolButton.setIcon(new ImageIcon(getRESFile(CELTIC_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
/*
        //TODO fix
        shortcuts.addKeyBinding(shortcuts.getColorChooserToolKeyCode(), shortcuts.isCtrl_colorChooserTool(),
        shortcuts.isShift_colorChooserTool(), shortcuts.isAlt_colorChooserTool(), Shortcuts.COLOR_CHOOSER_TOOL_SHORTCUT, (evt) -> {
            Color color = null;
            color = JColorChooser.showDialog(null, "Select a Color", color);
            updateSizeSlider();
        });*/
        shortcuts.addKeyBinding(shortcuts.getDnaToolKeyCode(), shortcuts.isCtrl_dnaTool(), shortcuts.isShift_dnaTool(),
                shortcuts.isAlt_dnaTool(), Shortcuts.DNA_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = dnaTool;
                    setHighlightedToDefault();
                    highlightedButton = dnaToolButton;
                    dnaToolButton.setIcon(new ImageIcon(getRESFile(DNA_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getEllipseToolKeyCode(), shortcuts.isCtrl_ellipseTool(),
                shortcuts.isShift_ellipseTool(), shortcuts.isAlt_ellipseTool(), Shortcuts.ELLIPSE_TOOL_SHORTCUT,
                (evt) -> {
                    selectedDrawingTool = ellipseTool;
                    setHighlightedToDefault();
                    highlightedButton = ellipseToolButton;
                    ellipseToolButton.setIcon(new ImageIcon(getRESFile(CIRCLE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateFillState(); // Tool supports filling
                    drawArea.setColor(ellipseTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getEraserToolKeyCode(), shortcuts.isCtrl_eraserTool(),
                shortcuts.isShift_eraserTool(), shortcuts.isAlt_eraserTool(), Shortcuts.ERASER_TOOL_SHORTCUT,
                (evt) -> {
                    selectedDrawingTool = eraserTool;
                    setHighlightedToDefault();
                    highlightedButton = eraserToolButton;
                    eraserToolButton.setIcon(new ImageIcon(getRESFile(ERASER_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getEyeDropToolKeyCode(), shortcuts.isCtrl_eyeDropTool(),
                shortcuts.isShift_eyeDropTool(), shortcuts.isAlt_eyeDropTool(), Shortcuts.EYE_DROP_TOOL_SHORTCUT,
                (evt) -> {
                    selectedDrawingTool = eyeDropperTool;
                    setHighlightedToDefault();
                    highlightedButton = eyeDropperToolButton;
                    eyeDropperToolButton.setIcon(new ImageIcon(getRESFile(EYEDROP_ICON_HIGHLIGHTED)));
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getFanToolKeyCode(), shortcuts.isCtrl_fanTool(), shortcuts.isShift_fanTool(),
                shortcuts.isAlt_fanTool(), Shortcuts.FAN_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = fanTool;
                    setHighlightedToDefault();
                    highlightedButton = fanToolButton;
                    fanToolButton.setIcon(new ImageIcon(getRESFile(FAN_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getPaintBucketToolKeyCode(), shortcuts.isCtrl_paintBucketTool(),
                shortcuts.isShift_paintBucketTool(), shortcuts.isAlt_paintBucketTool(),
                Shortcuts.PAINTBUCKET_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = paintBucketTool;
                    setHighlightedToDefault();
                    highlightedButton = paintBucketToolButton;
                    paintBucketToolButton.setIcon(new ImageIcon(getRESFile(BUCKET_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getSelectionToolKeyCode(), shortcuts.isCtrl_selectionTool(),
                shortcuts.isShift_selectionTool(), shortcuts.isAlt_selectionTool(), Shortcuts.SELECTION_TOOL_SHORTCUT,
                (evt) -> {
                    widthChanger.hideTextPanel();
                    selectedDrawingTool = rectangleSelectionTool;
                    setHighlightedToDefault();
                    highlightedButton = selectionToolButton;
                    selectionToolButton.setIcon(new ImageIcon(getRESFile(SELECTION_ICON_HIGHLIGHTED)));
                    textToolSettings.setVisibility(false);
                    northPanel.remove(textToolSettings);
                    northPanel.validate();
                    rectangleSelectionTool.showPanel();
                    northPanel.add(rectangleSelectionTool.getSelectionOptionPanel(), BorderLayout.EAST);
                    northPanel.validate();
                    updateSizeSlider();
                    updateFillState();
                });
        shortcuts.addKeyBinding(shortcuts.getRectToolKeyCode(), shortcuts.isCtrl_rectTool(),
                shortcuts.isShift_rectTool(), shortcuts.isAlt_rectTool(), Shortcuts.RECTANGLE_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = rectangleTool;
                    setHighlightedToDefault();
                    highlightedButton = rectangleToolButton;
                    rectangleToolButton.setIcon(new ImageIcon(getRESFile(SQUARE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateFillState(); // Tool supports filling
                    drawArea.setColor(rectangleTool.getColor());
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getTextToolKeyCode(), shortcuts.isCtrl_textTool(),
                shortcuts.isShift_textTool(), shortcuts.isAlt_textTool(), Shortcuts.TEXT_TOOL_SHORTCUT, (evt) -> {
                    rectangleSelectionTool.hidePanel();
                    northPanel.remove(rectangleSelectionTool.getSelectionOptionPanel());
                    northPanel.add(textToolSettings, BorderLayout.EAST);
                    widthChanger.showTextPanel();
                    updateSizeSlider();
                    northPanel.validate();
                    selectedDrawingTool = textTool;
                    setHighlightedToDefault();
                    highlightedButton = textToolButton;
                    textToolButton.setIcon(new ImageIcon(getRESFile(TEXT_ICON_HIGHLIGHTED)));
                    textToolSettings.setVisibility(true);

                });
        shortcuts.addKeyBinding(shortcuts.getTriangleToolKeyCode(), shortcuts.isCtrl_triangleTool(),
                shortcuts.isShift_triangleTool(), shortcuts.isAlt_triangleTool(), Shortcuts.TRIANGLE_TOOL_SHORTCUT,
                (evt) -> {
                    selectedDrawingTool = triangleTool;
                    setHighlightedToDefault();
                    highlightedButton = triangleToolButton;
                    triangleToolButton.setIcon(new ImageIcon(getRESFile(TRIANGLE_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateFillState();
                    updateNorthEastPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getPrintToolKeyCode(), shortcuts.isCtrl_printTool(),
                shortcuts.isShift_printTool(), shortcuts.isAlt_printTool(), Shortcuts.PRINT_SHORTCUT, (evt) -> {
                    printCanvas.getPrintDimensionsDialog();
                });
        shortcuts.addKeyBinding(shortcuts.getShortcutToolKeyCode(), shortcuts.isCtrl_shortcutTool(),
                shortcuts.isShift_shortcutTool(), shortcuts.isAlt_shortcutTool(), Shortcuts.SHORTCUT_SHORTCUT,
                (evt) -> {
                    keyboardShortCutPanel.renderPanel();
                });
        shortcuts.addKeyBinding(shortcuts.getCloseToolKeyCode(), shortcuts.isCtrl_closeTool(),
                shortcuts.isShift_closeTool(), shortcuts.isAlt_closeTool(), Shortcuts.CLOSE_SHORTCUT, (evt) -> {
                    System.exit(0);
                });
        shortcuts.addKeyBinding(shortcuts.getNewToolKeyCode(), shortcuts.isCtrl_newTool(), shortcuts.isShift_newTool(),
                shortcuts.isAlt_newTool(), Shortcuts.NEW_SHORTCUT, (evt) -> {
                    Dimension dimension = NewWindow.displayPrompt();
                    if (dimension != null) {
                        final int CANVAS_WIDTH = (int) dimension.getWidth();
                        final int CANVAS_HEIGHT = (int) dimension.getHeight();
                        MainUI mainUI = new MainUI(CANVAS_WIDTH, CANVAS_HEIGHT);
                        mainUI.displayUI();
                        mainFrame.dispose();
                    }
                });
        shortcuts.addKeyBinding(shortcuts.getGreyscaleToolKeyCode(), shortcuts.isCtrl_greyscaleTool(),
                shortcuts.isShift_greyscaleTool(), shortcuts.isAlt_greyscaleTool(), Shortcuts.GREYSCALE_SHORTCUT,
                (evt) -> {
                    greyscaleMenu.showWindow();
                });
        shortcuts.addKeyBinding(shortcuts.getBrightToolKeyCode(), shortcuts.isCtrl_brightTool(),
                shortcuts.isShift_brightTool(), shortcuts.isAlt_brightTool(), Shortcuts.BRIGHTNESS_SHORTCUT, (evt) -> {
                    brightnessContrastMenu.showWindow();
                });
        shortcuts.addKeyBinding(shortcuts.getSaturationToolKeyCode(), shortcuts.isCtrl_saturationTool(),
                shortcuts.isShift_saturationTool(), shortcuts.isAlt_saturationTool(), Shortcuts.HUE_SATURATION_SHORTCUT,
                (evt) -> {
                    hueSaturationMenu.showWindow();
                });
        shortcuts.addKeyBinding(shortcuts.getNoiseToolKeyCode(), shortcuts.isCtrl_noiseTool(),
                shortcuts.isShift_noiseTool(), shortcuts.isAlt_noiseTool(), Shortcuts.NOISE_SHORTCUT, (evt) -> {
                    noiseGeneratorMenu.showWindow();
                });
        shortcuts.addKeyBinding(shortcuts.getCheckToolKeyCode(), shortcuts.isCtrl_checkTool(),
                shortcuts.isShift_checkTool(), shortcuts.isAlt_checkTool(), Shortcuts.CHECKERBOARD_SHORTCUT, (evt) -> {
                    checkerboardMenu.showWindow();
                });
        shortcuts.addKeyBinding(shortcuts.getSpiralToolKeyCode(), shortcuts.isCtrl_spiralTool(),
                shortcuts.isShift_spiralTool(), shortcuts.isAlt_spiralTool(), Shortcuts.SPIRAL_TOOL_SHORTCUT, (evt) -> {
                    selectedDrawingTool = spiralTool;
                    setHighlightedToDefault();
                    highlightedButton = spiralToolButton;
                    spiralToolButton.setIcon(new ImageIcon(getRESFile(SPIRAL_ICON_HIGHLIGHTED)));
                    updateSizeSlider();
                    updateNorthEastPanel();
                });
        /*shortcuts.addKeyBinding(shortcuts.getZoomToolKeyCode(), shortcuts.isCtrl_zoomTool(),
        shortcuts.isShift_zoomTool(), shortcuts.isAlt_zoomTool(), Shortcuts.ZOOM_TOOL_SHORTCUT, (evt) -> {
        });*/

        shortcuts.addKeyBinding(shortcuts.getInvertToolKeyCode(), shortcuts.isCtrl_invertTool(),
                shortcuts.isShift_invertTool(), shortcuts.isAlt_invertTool(), Shortcuts.INVERT_SHORTCUT, (evt) -> {
                    invertMenu.showWindow();
                });
    }

    /**
     * makes sure the north east panel does is hidden
     * when it should not be displayed
     */
    public void updateNorthEastPanel() {
        mouseCursor.setDefaultCursor();
        textToolSettings.setVisibility(false);
        rectangleSelectionTool.restartSelection();
        rectangleSelectionTool.hidePanel();
        updateSizeSlider();
        widthChanger.showTextPanel();
        northPanel.validate();
    }

    /**
     * Used in Shortcuts class to focus the canvas tools
     */
    public void focusCanvasTools() {
        canvasTools.setFocusable(true);
    }

    /**
     * Used in Shortcuts class to focus the canvas tools
     * this also makes the text field to be read only
     */
    public void focusWidthPanelToFalse() {
        widthChanger.getJTextFieldComponent().setFocusable(false);

    }

    /**
     * returns the database for the key board shortcuts
     */
    public DB_KBShortcuts getDb_kbShortcuts() {
        return db_kbShortcuts;
    }
}
