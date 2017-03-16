# Red Black Tree

    A red-black tree is a binary search tree with one extra bit per node.
    
    1. The root is black.
    2. Every leaf (nil) is black
    3. If a node is red, then both its children are black.
    4. All leaf nodes have the same black depth.
    
    NOTE: red nodes always have a black parent 
    
## 2-4 Tree nodes and Red Black Tree nodes   
![RedBlackTreeNode](/images/RedBlackTreeNode.png)

## Insertion
    
    A new node is always inserted as a leaf node.
    If a new node is root, color is black, otherwise red.

## Double-Red Violation Case 1:
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-1.png)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-2.png)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-3.png)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-4.png)

## Double-Red Violation Case 2:
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation2-1.png)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation2-2.png)