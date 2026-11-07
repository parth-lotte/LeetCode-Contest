class Solution {
    public long countValidSubstrings(String s) {
        int i = 0, j = 0;      // i = smallest valid start for the current end j
        long count = 0;
        int n = s.length();

        while (j < n) {
            // "abc" occupies j-2..j, so any start <= j-2 would include it
            if (j >= 2 && s.charAt(j - 2) == 'a' && s.charAt(j - 1) == 'b' && s.charAt(j) == 'c') {
                i = j - 1;
            }

            count += (j - i + 1);   // valid starts for end j: i, i+1, ..., j
            j++;
        }
        return count;
    }
}
