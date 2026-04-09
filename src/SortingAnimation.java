import java.awt.Color;
import java.awt.Font;

import org.piccolo2d.activities.PActivity;
import org.piccolo2d.nodes.PText;

public class SortingAnimation extends AnimationScreen {
    private static final long serialVersionUID = 1L;

    private static final int totalWidth = 500;
    private static final int totalHeight = 300;
    private static final int BOX_SIZE = 30;
    private static final int SPACING = 10;
    private static final int MARGIN = 50;
    private static final int ROW_Y = 150;
    
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
        myNumberBoxes = new IntBoxNode[ARRAY_SIZE];
        createRandomBoxes();
    }

    public void playAnimation() {
        // wait for initialization before animating
        waitForInitialization();

        ShellSort.sort(myNumberBoxes, this);
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
    
    // loop through array and fill indexes with random numbers.
    private void createRandomBoxes() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            int num = (int) (Math.random() * 40 + 1);
            myNumberBoxes[i] = addIntBox(getBoxX(i), getBoxY(), BOX_SIZE, BOX_SIZE, num);
            myNumberBoxes[i].setPaint(Color.BLUE);
            myNumberBoxes[i].setTextPaint(Color.RED);
        }
    }

    private double getBoxX(int index) {
       return MARGIN + index * (BOX_SIZE + SPACING);
    }

    private double getBoxY() {
        return totalHeight / 2;
    }

    public void highlightCompare(int i, int j) {
        // make sure his calls waitforactivity() return ROW_Y;
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
