public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.canRemoveAll(new int[]{0, 1, 1, 0}, new int[]{5, 3, 3, 5}, new int[]{2, 2}), true, "Example 1");
        check(sol.canRemoveAll(new int[]{0, 1, 0}, new int[]{9, 9, 9}, new int[]{3, 2}), false, "Example 2");
        check(sol.canRemoveAll(new int[]{2, 0, 1, 2}, new int[]{7, 5, 4, 7}, new int[]{3, 1, 2}), false, "Example 3");
    }

    static void check(boolean actual, boolean expected, String name) {
        System.out.println(name + ": " + actual + (actual == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
