
public class ShellSort {

	// Shell sort on the boxes. Big gaps first, then smaller, then gap 1.
	public static void sort(IntBoxNode[] boxes, SortingAnimation screen) {
		int n = boxes.length;

		// Each pass uses a smaller gap until gap is 1.
		for (int gap = n / 2; gap > 0; gap /= 2) {
			screen.updateStatus("Shell sort — gap = " + gap);

			// Insert each value into its place for this gap size.
			for (int i = gap; i < n; i++) {
				// Hold the value we are inserting.
				int temp = boxes[i].getNumber();
				int j = i;

				// Shift bigger values along until we find the right spot.
				while (j >= gap && greater(boxes[j - gap], temp)) {
					screen.highlightCompare(j - gap, j);
					// Copy value one gap step to the right.
					shift(boxes, j - gap, j, screen);
					screen.resetCompare(j - gap, j);
					j -= gap;
				}
				// Drop the held value in place.
				boxes[j].setText(temp);
			}
		}
	}

	// Returns true if the box shows a bigger number than the key.
	private static boolean greater(IntBoxNode a, int value) {
		return a.getNumber() > value;
	}

	// Use shiftBox(from, to, value) so the slot at "to" is not updated until the ghost animation ends.
	private static void shift(IntBoxNode[] boxes, int from, int to, SortingAnimation screen) {
		int value = boxes[from].getNumber();
		screen.shiftBox(from, to, value);
	}
}

