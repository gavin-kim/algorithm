# 2-3-4 Tree (Completely balanced search tree)

* NOTE: All leaf nodes appearing on the same level

![2-3-4TreeNodes](/images/2-3-4TreeNodes.png)

### Insert
      
      1. Is overflow (4+ elements)? 
      (Y) Pick up the 3rd element in the node and put it in its parent.
      (N) Just insert it.

### Delete

      1. Is a leaf node? 
      (Y) Delete and Go to the next
      (N) Swap the node with rightmost and remove it. 
      (All leaf node is always on the same level in 2-3-4 Tree: rightmost is a leaf node) 
      
      [leaf node]
      2. Is underflow? 
      (Y) Go to the next
      (N) Done.
     
      [leaf node && underflow]
      3. Immediate sibling has 2+ elements? 
      (Y) Transfer
      (N) Fusion (take a element from its parent)
      
      [After Fusion]
      4. A parent of the node is underflow? 
      (Y) No.3 with the parent
      (N) Done.
 ### Transfer

         (C F)          (C F)          (- F)         (B F)              
        /  |  \        /  |  \        /  | \        /  |  \
      (A B) D   G    (A B) -   G    (A B) C  G    (A -) C   G 
 ### Fusion

       (B D F)        (B D F)           (D F)
       / | | \        / | | \          /  |  \
      A  C E  G      A  - E  G      (A B) E   G
      
      
