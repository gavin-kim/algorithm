# Sorting
Java’s systems programmers have chosen to use quicksort (with 3-way partitioning)
to implement the primitive-type methods, and mergesort for reference-type methods.<br>
The primary practical implications of these choices are, as just discussed, to trade speed
and memory usage (for primitive types) for stability (for reference types).
<hr>
A comparison sort algorithm cannot beat <strong><i>n * log(n)</i></strong> (worst-case) running time, since <strong><i>n * log(n)</i></strong> represents the minimum number of comparisons needed to know where to place each element.
<hr>

<img src="/images/SortingAlgorithms.png" width="400px" height="400px">
<img src="/images/RecursionTree.png" width="400px" height="400px">
<img src="/images/RecursiveDivideAndConquer.jpg" width="400px" height="400px">
<hr>

