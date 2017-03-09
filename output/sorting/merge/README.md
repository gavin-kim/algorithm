# Merge sort

`1/2 NlogN ~ NlogN compares to sort an array of length N`
	
**Top-down:** 

<img src="/images/MergeTopDown.png" width="500px">

**Proposition G.** Top-down mergesort uses at most 6N lg N array accesses to sort an
array of length N.

**Proof:** Each merge uses at most 6N array accesses (2N for the copy, 2N for the
merge back, and at most 2N for compares).

<hr>

**Bottom-up**

<img src="/images/MergeBottomUp.png" width="500px">
