# Q2. Remove Elements by Type and Color

**Difficulty:** Medium | **Points:** 4 | **Contest:** [Biweekly Contest 195](https://leetcode.com/contest/biweekly-contest-195/)

## Problem

You are given arrays `types` and `colors` (both of length `n`) and an array `groupSize`. In one operation you may remove exactly `groupSize[t]` **consecutive** elements, provided all of them have type `t` and the same color. After a removal, the remaining elements close the gap and keep their order. Return `true` if every element can be removed, otherwise `false`.

- `1 <= n <= 10^5`
- `groupSize[t]` is the required group size for type `t`

| Input | Output |
|-------|--------|
| `types=[0,1,1,0], colors=[5,3,3,5], groupSize=[2,2]` | `true` |
| `types=[0,1,0], colors=[9,9,9], groupSize=[3,2]` | `false` |
| `types=[2,0,1,2], colors=[7,5,4,7], groupSize=[3,1,2]` | `false` |

## Intuition

A group can only be built from consecutive elements with the same `(type, color)`. After a group is removed, its two neighbours become adjacent and may merge into a longer run. This is the same idea as the classic "remove all adjacent duplicates" problem, where the required group size depends on the type.

Removing a group as soon as it reaches its required size is always safe: those elements cannot be used in any other way, and the order in which valid groups are removed does not change the final result.

## Approach (stack of runs)

1. Keep a stack where each entry is `{type, color, runLength}`.
2. For each element `(type, color)`:
   - If the top of the stack has the same type and color, increase its `runLength`.
   - Otherwise push a new entry with `runLength = 1`.
3. If the top entry's `runLength` equals `groupSize[type]`, pop it. This removes the group, and the next element is compared against whatever is now on top.
4. After processing everything, return `true` if the stack is empty.

Because we pop the moment a run reaches `groupSize[type]`, a run on the stack never exceeds the required size.

## Dry run: Example 1

`types=[0,1,1,0]`, `colors=[5,3,3,5]`, `groupSize=[2,2]`

| Index | Element (type, color) | Action | Stack after |
|-------|----------------------|--------|-------------|
| 0 | (0, 5) | push, run 1 | `[(0,5,1)]` |
| 1 | (1, 3) | push, run 1 | `[(0,5,1), (1,3,1)]` |
| 2 | (1, 3) | run becomes 2 = `groupSize[1]`, pop | `[(0,5,1)]` |
| 3 | (0, 5) | run becomes 2 = `groupSize[0]`, pop | `[]` |

The stack is empty, so the answer is `true`.

## Dry run: Example 3

`types=[2,0,1,2]`, `colors=[7,5,4,7]`, `groupSize=[3,1,2]`

| Index | Element (type, color) | Action | Stack after |
|-------|----------------------|--------|-------------|
| 0 | (2, 7) | push, run 1 (needs 2) | `[(2,7,1)]` |
| 1 | (0, 5) | push, run 1 (needs 3) | `[(2,7,1), (0,5,1)]` |
| 2 | (1, 4) | push, run 1 = `groupSize[1]`, pop | `[(2,7,1), (0,5,1)]` |
| 3 | (2, 7) | top is `(0,5)`, so push | `[(2,7,1), (0,5,1), (2,7,1)]` |

The stack is not empty, so the answer is `false`.

## Complexity

- **Time:** O(n). Each element is pushed at most once and popped at most once.
- **Space:** O(n) for the stack.

## Edge cases

- `groupSize[t] == 1`: the element is removed immediately.
- A run longer than the required size (for example 4 elements with size 2) is removed in two groups, which the stack handles naturally.
- Leftover partial runs stay on the stack, so the answer is `false`.

## Run

```bash
javac *.java && java Main
```
