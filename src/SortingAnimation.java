import java.awt.Color;
import java.awt.Font;

import org.piccolo2d.activities.PActivity;
import org.piccolo2d.nodes.PText;

public class SortingAnimation extends AnimationScreen {
    private static final long serialVersionUID = 1L;

    private static final int totalWidth = 500;
    private static final int totalHeight = 300;
    
    private PText header;
    private IntBoxNode[] myNumberBoxes;

    private static final int ARRAY_SIZE = 12; // Assignment requires 12 elements
    @Override
    public void addInitialNodes() {
        setBounds(0, 0, totalWidth, totalHeight);

        // add background as a coloured box
        addColouredBox(0, 0, totalWidth, totalHeight, Color.LIGHT_GRAY);

        // TODO: change header text to actual algorithm name
        header = addText(0, 0, "Sorting is cool!");
        header.setTextPaint(Color.DARK_GRAY);

        // You could use an array of int nodes to hold numbers
        // to visualize the array.
        myNumberBoxes = new IntBoxNode[ARRAY_SIZE]; // Changed already to 12 elements

        //TODO: This only does 2 box, we need 12
        int num1 = (int) (Math.random() * 40 + 1);
        myNumberBoxes[0] = addIntBox(-30, -30, 30, 30, num1);
        myNumberBoxes[0].setPaint(Color.BLUE);
        myNumberBoxes[0].setTextPaint(Color.RED);

        int num2 = (int) (Math.random() * 40 + 1);
        myNumberBoxes[1] = addIntBox(-50, -50, 30, 30, num2);
        myNumberBoxes[1].setPaint(Color.BLUE);
        myNumberBoxes[1].setTextPaint(Color.RED);
    }

    public void playAnimation() {
        // wait for initialization before animating
        waitForInitialization();

        //TODO:Remove demo animation and replace with call to ShellSort.sort(myNumberBoxes, This);
        
        // Some example animation steps:

        // Parameters are x, y, scale, rotation (in radians), and time (in ms)
        waitForActivity(myNumberBoxes[0].animateToPositionScaleRotation(10, 30, 0.5, 0, 1000));

        // The function waitForActivity makes sure the following animation doesn't start
        // until after this animation step is done
        waitForActivity(myNumberBoxes[0].animateToColor(Color.GREEN, 2000),
                myNumberBoxes[0].animateToPositionScaleRotation(100, 30, 1.5, Math.toRadians(110), 2000));

        // Multiple items can be animated at the same time. You just need to give them
        // the same duration if you want them to move at the same speed. Lower duration
        // steps would finish first, like the header move here.
        waitForActivity(header.animateToPositionScaleRotation(0, 0, 2.0, 0, 500),
                myNumberBoxes[0].animateToPositionScaleRotation(150, 110, 1, 0, 1000),
                myNumberBoxes[1].animateToColor(Color.GREEN, 1000),
                myNumberBoxes[1].animateToPositionScaleRotation(200, 110, 1, 0, 1000));

        // You can show comparing with colour change
        waitForActivity(myNumberBoxes[0].animateToColor(Color.PINK, 1000),
                myNumberBoxes[1].animateToColor(Color.PINK, 1000));

        // If you don't wait, then these will happen at the same time as the following
        // steps
        myNumberBoxes[0].animateToColor(Color.GREEN, 1000);
        myNumberBoxes[1].animateToColor(Color.GREEN, 1000);

        //TODO: remove 2 element demo
        // You could use the number in boxes for comparisons
        if (myNumberBoxes[0].getNumber() > myNumberBoxes[1].getNumber()) {
            // You can even swap values in the array
            IntBoxNode temp = myNumberBoxes[0];
            myNumberBoxes[0] = myNumberBoxes[1];
            myNumberBoxes[1] = temp;

            // But make sure you animate it too, or it won't be visible.
            // Hint: the positions should really be computed from the index.
            waitForActivity(myNumberBoxes[0].animateToPositionScaleRotation(150, 110, 1, 0, 2000),
                    myNumberBoxes[1].animateToPositionScaleRotation(200, 110, 1, 0, 2000));
        }

        // You can add items as you go...
        // TODO: change the finish text to what we want it to be
        PText allDone = addText(100, 200, "All Done!");
        allDone.setFont(new Font("Consolas", Font.PLAIN, 36));
        allDone.setTextPaint(Color.WHITE);
        // but sometimes they don't show up unless you animate them.
        // You can use a delay of zero, though.
        allDone.animateToPositionScaleRotation(100, 200, 1, 0, 0);
    }

    public static void main(String[] args) {
        SortingAnimation screen = new SortingAnimation();
        screen.playAnimation();
    }

    private static void waitForActivity(PActivity... activities) {
        long finalEndTime = 0;
        for (PActivity activity : activities) {
            long thisEndTime = activity.getStartTime() + activity.getDuration();
            if (thisEndTime > finalEndTime) {
                finalEndTime = thisEndTime;
            }
        }
        while (System.currentTimeMillis() < finalEndTime) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Whatever, I do what I want.
            }
        }
    }
    
    /*
     * NEEDS TO BE IMPLEMENTED (REMOVE TODO comments after we do it)
     */
    private void createRandomBoxes() {
        // TODO: create 12 random boxes and place them on screen
    }

    private double getBoxX(int index) {
        // TODO: compute x-position from index
        return 0;
    }

    private double getBoxY() {
        // TODO: return common y-position for boxes
        return 0;
    }

    public void highlightCompare(int i, int j) {
        // TODO: animate comparison highlight
    }

    public void resetCompare(int i, int j) {
        // TODO: restore normal box colors
    }

    public void shiftBox(int from, int to) {
        // TODO: animate a shifted box
    }

    public void moveBoxToIndex(IntBoxNode box, int index) {
        // TODO: animate a box to its index position
    }

    public void updateStatus(String text) {
        // TODO: update header or status text
    }

    
    
}