import java.awt.Paint;
import java.awt.geom.Rectangle2D;

import org.piccolo2d.PNode;
import org.piccolo2d.extras.PFrame;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

/**
 * A base simplified animation screen. This class should be used as a
 * super-class for a sorting animation class. -- provides helper methods
 * for creating and displaying visual elements
 */
public abstract class AnimationScreen extends PFrame {
    private static final long serialVersionUID = 1L;

    // Tracks whether the screen has finished initializing
    private boolean isInitialized;

    public AnimationScreen() {
        super();

        // Initially, the screen is not ready
        isInitialized = false;
    }

    public final void initialize() {
        // Remove the default pan and zoom event handlers (mouse controls)
        getCanvas().setPanEventHandler(null);
        getCanvas().setZoomEventHandler(null);

        // Let the subclass add its initial visual elements
        addInitialNodes();

        // Mark the screen as ready for animation
        isInitialized = true;
    }

    public abstract void addInitialNodes();

    /**
     * This method must be implemented by subclasses
     * to define what appears on the screen at the start.
     */
    public void waitForInitialization() {
        // Wait until initialization is complete before continuing
        while (!isInitialized) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Ignore interruption
            }
        }
    }

      /**
     * Positions a node and adds it to the animation screen.
     */
    private void addAt(PNode node, double x, double y) {
        // position item(node) at specific location
        node.translate(x, y);

        // add to canvas (screen)
        getCanvas().getLayer().addChild(node);
        getCanvas().repaint();
    }

    /**
     * Creates and displays a text box.
     */
    public TextBoxNode addTextBox(double x, double y, double width, double height, String text) {
        TextBoxNode newNode = new TextBoxNode(new Rectangle2D.Double(0, 0, width, height), text);

        addAt(newNode, x, y);

        return newNode;
    }

     /**
     * Creates and displays a box containing an integer.
     */
    public IntBoxNode addIntBox(double x, double y, double width, double height, int number) {
        IntBoxNode newNode = new IntBoxNode(new Rectangle2D.Double(0, 0, width, height), number);

        addAt(newNode, x, y);

        return newNode;
    }

    /**
     * Creates and displays simple text.
     */
    public PText addText(double x, double y, String text) {
        PText newNode = new PText(text);

        addAt(newNode, x, y);

        return newNode;
    }

    /**
     * Creates and displays a colored rectangle (background or box).
     */
    public PNode addColouredBox(double x, double y, double width, double height, Paint colour) {
        PNode newNode = new PNode();
        // note - node will not be visible until its bounds and paint are set.
        // Set size of the box
        newNode.setBounds(0, 0, width, height);
        // set colour
        newNode.setPaint(colour);

        addAt(newNode, x, y);

        return newNode;
    }

     /**
     * Creates and displays an ellipse shape.
     */
    public PPath addElipse(double x, double y, double width, double height) {
        PPath newNode = PPath.createEllipse(0, 0, width, height);

        addAt(newNode, x, y);

        return newNode;
    }
    
     /**
     * Creates and displays a rectangle shape.
     */
    public PPath addRectangle(double x, double y, double width, double height) {
        PPath newNode = PPath.createRectangle(0, 0, width, height);

        addAt(newNode, x, y);

        return newNode;
    }
/**
    * Caution: lines are difficult to animate, so they are only recommend
    * if they don't move.
     * Creates and displays a line.
     * Note: Lines are harder to animate, so they are best used as static elements.
     */
    public PPath addLine(double x1, double y1, double x2, double y2) {
        PPath newNode = PPath.createLine(x1, y1, x2, y2);

        // add line to canvas (screen)
        getCanvas().getLayer().addChild(newNode);

        return newNode;
    }
}