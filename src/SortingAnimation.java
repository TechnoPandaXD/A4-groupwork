import java.awt.Color;
import java.awt.Font;

import org.piccolo2d.activities.PActivity;
import org.piccolo2d.nodes.PText;

public class SortingAnimation extends AnimationScreen {
    private static final long serialVersionUID = 1L;

    // Size of the whole window
    private static final int totalWidth = 600;
    private static final int totalHeight = 300;
    
    // Size of each box (the numbers)
    private static final int totalWidth = 650;
    private static final int totalHeight = 400;
    private static final int BOX_SIZE = 30;
    
    // Space between each box
    private static final int SPACING = 10;
    
    // Space from the left side
    private static final int MARGIN = 50;

    // Height used when moving boxes (so they "lift up")
    private static final int ROW_Y = 150;
    
    // Text at the top (used to show status)
    private PText header;

    // Array that holds all the number boxes
    private IntBoxNode[] myNumberBoxes;

    private static final int ARRAY_SIZE = 12; // Assignment requires 12 elements (12 numbers)

    @Override
    public void addInitialNodes() {
        // Set screen size
        setBounds(0, 0, totalWidth, totalHeight);

        // add background as a coloured box
        addColouredBox(0, 0, totalWidth, totalHeight, Color.BLACK);

        // TODO: change header text to actual algorithm name (title text at the top)
        header = addText(0, 0, "Shell Sort Animation");
        header.setTextPaint(Color.WHITE);

        // Create the array for the boxes
        myNumberBoxes = new IntBoxNode[ARRAY_SIZE];
        // Fill it with random numbers
        createRandomBoxes();
    }

    public void playAnimation() {
        // wait for initialization before animating (before start)
        waitForInitialization();
        
        // Start the sorting algorithm
        ShellSort.sort(myNumberBoxes, this);
        PText allDone = addText(220, 300, "All Done!");
        allDone.setFont(new Font("Consolas", Font.PLAIN, 36));
        allDone.setTextPaint(Color.WHITE);


        // but sometimes they don't show up unless you animate them.
        // You can use a delay of zero, though.

        allDone.animateToPositionScaleRotation(220, 300, 1, 0, 0);
        
        
        // You can use a delay of zero, though to make sure they actually show up
        allDone.animateToPositionScaleRotation(100, 200, 1, 0, 0);
    }


    
    public static void main(String[] args) {
        // start animation program
        SortingAnimation screen = new SortingAnimation();
        screen.playAnimation();
    }
    // Waits for animations to finish before continuing
    private static void waitForActivity(PActivity... activities) {
        long finalEndTime = 0;

        // Find the latest ending animation
        for (PActivity activity : activities) {
            long thisEndTime = activity.getStartTime() + activity.getDuration();
            if (thisEndTime > finalEndTime) {
                finalEndTime = thisEndTime;
            }
        }

        // Pause until animations are done
        while (System.currentTimeMillis() < finalEndTime) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
    
    
    // loop through array and fill indexes with random numbers.
    // will create random boxes with numbers
    private void createRandomBoxes() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            // random number from 1 - 40
            int num = (int) (Math.random() * 40 + 1);

            //create box at the correct position
            myNumberBoxes[i] = addIntBox(getBoxX(i), getBoxY(), BOX_SIZE, BOX_SIZE, num);
            
            // set colours
            myNumberBoxes[i].setPaint(Color.DARK_GRAY);
            myNumberBoxes[i].setTextPaint(Color.WHITE);
        }
    }

    // Calculates X position based on index
    private double getBoxX(int index) {
       return MARGIN + index * (BOX_SIZE + SPACING);
    }

    // Gets Y position (middle of screen)
    private double getBoxY() {
        return totalHeight / 2;
    }

    // Highlights two boxes when comparing
    public void highlightCompare(int i, int j) {
        waitForActivity(
            myNumberBoxes[i].animateToColor(Color.RED, 400),
            myNumberBoxes[j].animateToColor(Color.RED, 400)
        );
    }

    // Resets color after comparing
    public void resetCompare(int i, int j) {
        waitForActivity(
            myNumberBoxes[i].animateToColor(Color.DARK_GRAY, 300),
            myNumberBoxes[j].animateToColor(Color.DARK_GRAY, 300)
        );
    }


    public void shiftBox(int from, int to, int value) {
        IntBoxNode temp = addIntBox(getBoxX(from), getBoxY(), BOX_SIZE, BOX_SIZE, value);
        temp.setPaint(Color.PINK);
        temp.setTextPaint(Color.WHITE);

        // lift up
        waitForActivity(
            temp.animateToPositionScaleRotation(getBoxX(from), ROW_Y - 40, 1, 0, totalHeight)
        );

        // move sideways
        waitForActivity(
            temp.animateToPositionScaleRotation(getBoxX(to), ROW_Y - 40, 1, 0, totalWidth)
        );

        // drop down
        waitForActivity(
            temp.animateToPositionScaleRotation(getBoxX(to), getBoxY(), 1, 0, totalHeight)
        );

        // remove temp box after move
        temp.removeFromParent();
        myNumberBoxes[to].setText(value);
    }
    // Moves a box directly to a new index
    public void moveBoxToIndex(IntBoxNode box, int index) {
        waitForActivity(
            box.animateToPositionScaleRotation(getBoxX(index), getBoxY(), 1, 0, totalWidth)
        );
    }

    // Updates the text at the top
    public void updateStatus(String text) {
        header.setText(text);
    }

}
