# Distribution Sorts
<h4>
  Comparison sorts can't run better than O(n log n) but Distribution sorts run in linear time O(n)<br>
  The unique characteristic of a distribution sorting algorithm is  that it does not make use of comparisons to do the sorting.
</h4>
<ul>
<li><strong>Counting</strong>: buckets hold only a single value</li>
<li><strong>Bucket</strong>:   buckets hold a range of values (range: max - min)</li>
<li><strong>Radix</strong>:    buckets hold values based on digits within their values<br> 
              (number of bits = the number of bits for max value in the array)<br>
              (radix = the number of bits to be used for counting sort each time)</li>
</ul>

