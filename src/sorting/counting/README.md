# Counting Sort

`O(n + k): counting [0..n-1] + sorting [0..n-1] + k adding[0..k-1] and n size of auxiliary array`

It is more efficient to use a non-comparison sorting algorithm. This will make it possible to sort lists even in linear time. These challenges will cover Counting Sort, a fast way to sort lists where the elements have a small number of possible values, such as integers within a certain range.

<img src="/images/CountingSort.png">

CountingSort(Array, range)

<ul>
  <li>a : Array</li>
  <li>b : Sorted Array</li>
  <li>c : Array that counts occurence</li>
  <li>c': Array that indicates the last index + 1</li>     
</ul>
