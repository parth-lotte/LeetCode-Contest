# LeetCode Biweekly Contest 195 - Java Solutions

Solutions, approach write-ups, dry runs and complexity analysis for the problems I solved in [LeetCode Biweekly Contest 195](https://leetcode.com/contest/biweekly-contest-195/).

## Problems solved

| # | Problem | Difficulty | Points | Core idea | Time | Space |
|---|---------|-----------|--------|-----------|------|-------|
| Q1 | [Count Valid Substrings I](./Q1-count-valid-substrings-i) | Medium | 4 | Sliding window; move the start past each `"abc"` | O(n) | O(1) |
| Q2 | [Remove Elements by Type and Color](./Q2-remove-elements-by-type-and-color) | Medium | 4 | Stack of runs; pop a run when it reaches its group size | O(n) | O(n) |
| Q3 | [Count Valid Substrings II](./Q3-count-valid-substrings-ii) | Medium | 5 | Track the latest start for `"a"`, `"ab"`, `"abc"` subsequences | O(n) | O(1) |

Each folder contains:

- `Solution.java`: the accepted solution
- `Main.java`: a small runner that checks the examples from the problem
- `README.md`: problem summary, intuition, approach, dry run, complexity and edge cases

## Key takeaways

- **Fix the end index, count the valid starts.** Q1 and Q3 both use this. When a longer substring always contains a shorter one, the valid starts for each end form a continuous range, so the count is a single subtraction.
- **Substring vs subsequence changes the technique.** Q1 only needs a 3-character check at each position. Q3 needs a small chain of states (`a -> ab -> abc`), each storing the best possible start.
- **Stacks handle collapsing sequences.** In Q2, removing a group makes its neighbours adjacent, which a stack of runs models directly.

## How to run

Requires JDK 8 or later.

```bash
git clone https://github.com/<your-username>/leetcode-biweekly-contest-195.git
cd leetcode-biweekly-contest-195/Q1-count-valid-substrings-i
javac *.java && java Main
```

Repeat in the other folders to run their examples.

## Repository structure

```
leetcode-biweekly-contest-195/
├── README.md
├── Q1-count-valid-substrings-i/
│   ├── Solution.java
│   ├── Main.java
│   └── README.md
├── Q2-remove-elements-by-type-and-color/
│   ├── Solution.java
│   ├── Main.java
│   └── README.md
└── Q3-count-valid-substrings-ii/
    ├── Solution.java
    ├── Main.java
    └── README.md
```

## Note

The problems belong to LeetCode. This repository contains only my own solutions and explanations, with each problem described in my own words. Please see the contest page for the original statements.

Feedback and suggestions for cleaner or faster approaches are welcome. Feel free to open an issue or a pull request.
