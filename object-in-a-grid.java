// TC: O(n^2) in the worst case, but on average O(n log n) due to hill climbing
// SC: O(1) for the space used by the algorithm itself, but O(n) for the space used by the grid if we consider it as part of the input
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


public class Solution {
    
    // Provided enum
    enum Response {
        HOTTER,   // Moving closer to target than your previous guess
        COLDER,   // Moving farther from target than your previous guess
        SAME,     // Same distance as your previous guess
        EXACT;    // Reached the target
    }
    
    /**
     * Black-box API: Given the row and col of a guess,
     * returns a Response relative to the previous guess.
     * 
     * In an interview or contest, this method is given.
     */
    public Response getResponse(int row, int col) {
        // Implementation is hidden (black box).
        // For testing purposes, you might simulate it.
        return Response.EXACT; // stub
    }
    
    /**
     * Finds the target object in a grid of size rows x cols.
     * The grid is 0-indexed.
     */
    public int[] findObject(int rows, int cols) {
        // Start with an initial guess, e.g., the center of the grid.
        int currRow = rows / 2;
        int currCol = cols / 2;
        
        // Make the first guess.
        Response res = getResponse(currRow, currCol);
        if (res == Response.EXACT) {
            return new int[]{currRow, currCol};
        }
        
        // Use hill-climbing: as long as we can move to a position that gives "HOTTER",
        // update the current guess.
        boolean improved = true;
        while (improved) {
            improved = false;
            // Define possible directions (including diagonals).
            int[][] directions = {
                {-1,  0}, // up
                { 1,  0}, // down
                { 0, -1}, // left
                { 0,  1}, // right
                {-1, -1}, // up-left
                {-1,  1}, // up-right
                { 1, -1}, // down-left
                { 1,  1}  // down-right
            };
            
            // Try each neighbor.
            for (int[] dir : directions) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];
                
                // Make sure we're within bounds.
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }
                
                // Query the response for the new guess.
                Response newRes = getResponse(newRow, newCol);
                if (newRes == Response.EXACT) {
                    // Found the target.
                    return new int[]{newRow, newCol};
                }
                
                // If the new guess is HOTTER, update our current guess.
                if (newRes == Response.HOTTER) {
                    currRow = newRow;
                    currCol = newCol;
                    improved = true;
                    // Once we found an improvement, break out of the loop to try from the new position.
                    break;
                }
                // Optionally, you might handle the SAME response if desired.
            }
        }
        
        // If no neighbor gives a HOTTER response, we assume the current guess is the best we can find.
        // (In a convex distance function, this local optimum would be the target.)
        return new int[]{currRow, currCol};
    }
    
    // For demonstration purposes. In a real-world setting, getResponse is provided externally.
    public static void main(String[] args) {
        // Note: This main method is only for demonstration.
        // In an actual test scenario, the getResponse method would be implemented for real.
        Solution solver = new Solution();
        
        // Suppose we have a grid of size 5 x 5.
        int rows = 5;
        int cols = 5;
        
        // The following call would attempt to locate the target.
        // Without an actual implementation of getResponse, this code uses the stub that always returns EXACT.
        int[] result = solver.findObject(rows, cols);
        System.out.println("Target found at: (" + result[0] + ", " + result[1] + ")");
    }
}
