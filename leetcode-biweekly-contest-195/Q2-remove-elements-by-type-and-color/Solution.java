import java.util.*;

class Solution {
    public boolean canRemoveAll(int[] types, int[] colors, int[] groupSize) {

        // Each stack entry is {type, color, runLength}
        Stack<int[]> st = new Stack<>();

        int n = types.length;

        for (int i = 0; i < n; i++) {
            int type = types[i];
            int color = colors[i];

            if (!st.isEmpty() && st.peek()[0] == type && st.peek()[1] == color) {
                st.peek()[2]++;                       // extend the current run
            } else {
                st.push(new int[]{type, color, 1});   // start a new run
            }

            // The run is complete: remove the whole group
            if (st.peek()[2] == groupSize[type]) {
                st.pop();
            }
        }

        return st.isEmpty();
    }
}
