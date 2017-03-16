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
    Double-Red Violation: After insertion, 2 red nodes are liked directly.

### Case 1: Node x is black or null (rotate).

* rotateRight(w)
    
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-1.png)

* rotateLeft(v) and rotateRight(w)

![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-2.png)

* rotateLeft(w)

![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-3.png)

* rotateRight(v) and rotateLeft(w)

![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-4.png)

### Case 2: Node x is red (recolor)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation2-1.png)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation2-2.png)