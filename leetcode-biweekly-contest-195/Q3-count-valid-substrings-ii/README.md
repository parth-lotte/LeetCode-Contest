# Q3. Count Valid Substrings II

**Difficulty:** Medium | **Points:** 5 | **Contest:** [Biweekly Contest 195](https://leetcode.com/contest/biweekly-contest-195/)

## Problem

You are given a string `s` made only of `'a'`, `'b'` and `'c'`. A string is **valid** if it does not contain `"abc"` as a **subsequence** (the letters must appear in that order, but they do not have to be adjacent). Return the number of valid substrings of `s`.

- `1 <= s.length <= 10^5`

| Input | Output |
|-------|--------|
| `"abac"` | `9` |
| `"abc"` | `5` |
| `"ac"` | `3` |

This is the harder variant of [Q1](../Q1-count-valid-substrings-i): "abc" must now be checked as a subsequence instead of a contiguous substring, so a fixed-size window check is no longer enough.

## Intuition

If `s[l..i]` contains `"abc"` as a subsequence, then every longer substring `s[l'..i]` with `l' < l` also contains it. So for a fixed end index `i`, invalid starts are on the left and valid starts are on the right, with one clean boundary between them.

Define `abc` as the **largest** start `l` such that `s[l..i]` contains `"abc"` (or `-1` if none). Then the valid starts are `abc + 1, ..., i`, which is

```
i - (abc + 1) + 1 = i - abc
```

valid substrings ending at `i`.

## Approach

Maintain three values, each meaning "the largest start index from which this pattern can be found as a subsequence, using characters up to the current index". All start at `-1`.

| Variable | Meaning |
|----------|---------|
| `a` | Largest start from which `"a"` can be found (index of the latest `'a'`) |
| `ab` | Largest start from which `"ab"` can be found |
| `abc` | Largest start from which `"abc"` can be found |

For each index `i` with character `ch`:

- `'a'`: `a = i`
- `'b'`: this `'b'` can complete an `"ab"` starting at the latest `'a'`, so `ab = max(ab, a)`
- `'c'`: this `'c'` can complete an `"abc"` using the best `"ab"` found so far, so `abc = max(abc, ab)`

Then add `i - abc` to the answer.

If `a` is still `-1` when a `'b'` arrives, `ab` stays `-1`, because no `'a'` came before that `'b'`.

## Dry run: `s = "aabcc"`

| i | char | update | a | ab | abc | add (i - abc) |
|---|------|--------|---|----|-----|---------------|
| 0 | a | a = 0 | 0 | -1 | -1 | 1 |
| 1 | a | a = 1 | 1 | -1 | -1 | 2 |
| 2 | b | ab = max(-1, 1) = 1 | 1 | 1 | -1 | 3 |
| 3 | c | abc = max(-1, 1) = 1 | 1 | 1 | 1 | 2 |
| 4 | c | abc = max(1, 1) = 1 | 1 | 1 | 1 | 3 |

Total = 1 + 2 + 3 + 2 + 3 = **11**.

Check by counting: there are 15 substrings in total. The invalid ones must start at index 0 or 1 (to include an `'a'`) and end at index 3 or 4 (to include a `'c'`): `aabc`, `abc`, `aabcc`, `abcc`. That is 4 invalid, and 15 - 4 = 11.

## Complexity

- **Time:** O(n), one pass.
- **Space:** O(1).
- The result is stored in a `long`, since the answer can reach about 5 x 10^9.

## Edge cases

- Initial values are `-1` (not `0`), so that "nothing found yet" gives `i - (-1) = i + 1`, meaning all starts are valid.
- Strings with no `'a'` before a `'b'`, or no `'b'` before a `'c'`, never produce an `abc` boundary, so every substring is valid.

## Run

```bash
javac *.java && java Main
```
