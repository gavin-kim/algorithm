# 2-3-4 Tree (Completely balanced search tree)

### 2-3-4 Nodes (Important: All leaf nodes have the same depth)

![2-3-4TreeNodes](/images/2-3-4TreeNodes.png)

### Insert
![24TreeInsertFlowchart](/images/24TreeInsertFlowchart.png)

### Delete
![24TreeDeleteFlowchart](/images/24TreeDeleteFlowchart.png)
    
### Transfer: When the node has an immediate sibling(2~3 elements)

    nodeIndex           0 1
                       (C D)
    parentIndex      0/ 1| 2\
    siblings     (..A)  (-)  (F..)
                     |   |   |
                     B  (*)  E
    
     transfer from left      transfer from right
           (A D)                   (C F)
          /  |  \                 /  |  \
      (..)  (C)  (F..)       (..A)  (D)  (..)
            / \  |                  / \
           B (*) E                (*)  E
    
    Transfer elements from a sibling that has 2~3 elements.
    If the node is a leaf a child node must be null.
      
### Merge: When the node has no siblings(2~3 elements). 

      (*) is a child node after merging.
     
      a single element   (B)        (B)
                         / \        / \
                       (-) (C)    (A) (-)
                        |  / \    / \  |
     
                        (-)         (-)     <--- parent is underflow
                           \       /
                     (-) (B C)   (A B) (-)
                         / | \   / | \
                       (*)          (*)
     
              0 1               0 1             0
      Steps  (C D)             (C D)           (D)
             / | \             / |             / |
          (A) (-) (F)  -->  (A) (F)  -->  (A C) (F)    left merge
           0   1   2         0   1            |
              (*)               (*)          (*)
     
                                               (C)
                                               / |
                                     -->    (A)  (D F) right merge
                                                 |
                                                (*)
     
      If the node is a leaf the child node must be null.
      Return merged child node. After merging the parent can be underflow.
      
      
