public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        check(sol.countValidSubstrings("abac"), 9, "Example 1");
        check(sol.countValidSubstrings("abc"), 5, "Example 2");
        check(sol.countValidSubstrings("ac"), 3, "Example 3");
    }

    static void check(long actual, long expected, String name) {
        System.out.println(name + ": " + actual + (actual == expected ? "  PASS" : "  FAIL (expected " + expected + ")"));
    }
}
