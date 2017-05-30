# Dynamic Programming
* Solve the problems by combining the solutions to sub-problems 
* Solve each sub-problem just once and then save its result in a table
* Avoid work of recomputing the answer every time

## A sequence of 4 steps for developing a dynamic-programming algorithm:
1. Characterize the structure of an optimal solution.
2. Recursively define the value of an optimal solution.
3. Compute the value of an optimal solution, typically in a bottom-up fashion.
4. Construct an optimal solution from computed information.

## Top-Down vs Bottom-Up 

### Top-Down with memoization.
* Save computed results to avoid recomputing previous inputs.

### Bottom-Up 
* Sort the sub-problems by size and solve them in size order, smallest first.
* Solve each sub-problems only once and Save their solutions.
* Often has much better constant factors

### Dynamic VS Greedy
* Greedy algorithms first make a "greedy" choice (the choice that looks best at 
  that time) and then solve a resulting sub-problem, without bothering to solve 
  all possible related smaller sub-problems.