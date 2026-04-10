import java.awt.Paint;
import java.awt.Shape;

import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;
import org.piccolo2d.util.PBounds;

/**
 * A node that is a combination of a shape and an integer within it.
 */
public class IntBoxNode extends PPath.Float {
    private static final long serialVersionUID = 1L;

    private PText nodeLabel;

    private int number;

    public IntBoxNode(Shape shape, int newNumber) {
        super(shape);

        this.nodeLabel = new PText(Integer.toString(newNumber));
        this.nodeLabel.setPickable(false);
        this.addChild(nodeLabel);
        this.centerText();

        this.number = newNumber;
    }

    public Paint getTextPaint() {
        return this.nodeLabel.getTextPaint();
    }

    public void setTextPaint(Paint textPaint) {
        this.nodeLabel.setTextPaint(textPaint);
    }

    public int getNumber() {
        return this.number;
    }

    public void setText(int newNumber) {
        this.nodeLabel.setText(Integer.toString(newNumber));
        this.centerText();
        this.number = newNumber;
    }

    private void centerText() {
        PBounds ourBounds = this.getBounds();
        this.nodeLabel.centerBoundsOnPoint(ourBounds.getCenterX(), ourBounds.getCenterY());
    }
    
   
}
