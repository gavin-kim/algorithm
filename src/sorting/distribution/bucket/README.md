# Bucket Sort

<img src="/images/BucketSort.png">


 * Bucket sort
 
   Worst:   O(n^2)
   Best:    O(n + k)
   Average: O(n + k)
  
   k: number of buckets (stable sort: insertion or merge)
  
   min                       max
    {[   ][   ]=> bucket size } => range
  
   range: max - min
   range / bucket size: the number of buckets (last index of bucket[])
