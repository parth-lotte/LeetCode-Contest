# Q1. Count Valid Substrings I

**Difficulty:** Medium | **Points:** 4 | **Contest:** [Biweekly Contest 195](https://leetcode.com/contest/biweekly-contest-195/)

## Problem

You are given a string `s` made only of the characters `'a'`, `'b'` and `'c'`. A string is **valid** if it does not contain `"abc"` as a contiguous substring. Return the number of valid substrings of `s`. Substrings with different start or end indices are counted separately.

- `1 <= s.length <= 10^5`

| Input | Output |
|-------|--------|
| `"abac"` | `10` |
| `"abc"` | `5` |
| `"ac"` | `3` |

## Intuition

A substring is invalid only if it fully contains an occurrence of `"abc"`. If `"abc"` sits at positions `j-2..j`, then every substring that starts at `j-2` or earlier and ends at `j` or later is invalid.

So if we fix the **end index** `j`, the valid starts form one continuous range `[i, j]`, and `i` never moves left as `j` increases.

## Approach (sliding window)

1. Keep `i`, the smallest start index such that `s[i..j]` contains no `"abc"`. Initially `i = 0`.
2. For each end index `j`:
   - If `s[j-2..j] == "abc"`, set `i = j - 1`. Starting at `j-1` gives `"bc"`, which is too short to contain `"abc"`, so `j-1` is the first safe start.
   - Add `j - i + 1` to the answer. These are the valid substrings ending at `j`.
3. Return the total.

`i` only moves right because a start that is invalid for end `j` stays invalid for every later end.

## Dry run: `s = "abac"`

| j | char | "abc" ends at j? | i | add (j - i + 1) | total |
|---|------|------------------|---|-----------------|-------|
| 0 | a | no | 0 | 1 | 1 |
| 1 | b | no | 0 | 2 | 3 |
| 2 | a | no | 0 | 3 | 6 |
| 3 | c | no (`"bac"`) | 0 | 4 | 10 |

## Dry run: `s = "abc"`

| j | char | "abc" ends at j? | i | add (j - i + 1) | total |
|---|------|------------------|---|-----------------|-------|
| 0 | a | no | 0 | 1 | 1 |
| 1 | b | no | 0 | 2 | 3 |
| 2 | c | **yes** | 1 | 2 | 5 |

At `j = 2`, the start `0` is skipped because `"abc"` itself is invalid. Only `"bc"` and `"c"` are counted.

## Complexity

- **Time:** O(n), one pass.
- **Space:** O(1).
- The result is stored in a `long`, because the answer can reach about n(n+1)/2, roughly 5 x 10^9.

## Edge cases

- Strings shorter than 3 characters can never contain `"abc"`, so the answer is n(n+1)/2.
- Overlapping occurrences are not possible (`"abc"` has no self-overlap), so resetting `i` to `j - 1` is always enough.

## Run

```bash
javac *.java && java Main
```
