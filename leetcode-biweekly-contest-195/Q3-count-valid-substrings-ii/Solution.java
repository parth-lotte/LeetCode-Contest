class Solution {
    public long countValidSubstrings(String s) {

        long count = 0;

        // Largest start index from which each pattern can be found as a
        // subsequence using characters up to the current index (-1 = none yet).
        int a = -1, ab = -1, abc = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == 'c') {
                abc = Math.max(abc, ab);
            } else if (ch == 'b') {
                ab = Math.max(ab, a);
            } else {
                a = i;
            }

            // Valid starts for end index i are abc+1 ... i
            count += (i - abc);
        }
        return count;
    }
}
