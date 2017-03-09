# 2-3-4 Tree (Completely balanced search tree)

### 2-3-4 Nodes (NOTE: All leaf nodes appearing on the same level)

![2-3-4TreeNodes](/images/2-3-4TreeNodes.png)

### Insert
![24TreeInsertFlowchart](/images/24TreeInsertFlowchart.png)

### Delete
![24TreeDeleteFlowchart](/images/24TreeDeleteFlowchart.png)
    
### Transfer
 
         (C F)          (C F)          (- F)         (B F)              
        /  |  \        /  |  \        /  | \        /  |  \
      (A B) D   G    (A B) -   G    (A B) C  G    (A -) C   G   <--- always leaf nodes
      
### Fusion

       (B D F)        (B D F)           (D F)
       / | | \        / | | \          /  |  \
      A  C E  G      A  - E  G      (A B) E   G  
      
      
