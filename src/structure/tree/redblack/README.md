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

![DoubleRedInsertion](/images/RedBlackTreeInsertion.png)
![DoubleRedInsertion](/images/RedBlackTreeInsertion2.png)

### Case 1: Uncle node x is BLACK or Null (rotate).

* rotateRight(w)
    
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-1.png)

* rotateLeft(v) and rotateRight(w)

![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation1-2.png)


### Case 2: Uncle node x is red (recolor). Need to check again
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation2-1.png)
![DoubleRedViolation](/images/RedBlackTreeDoubleRedViolation2-2.png)


## Deletion

### Case 1: Node is RED

    1. Remove the node and link its child to parent.

        P         P
        |         |
        R  -->  C(B)
       / \
    C(B)  null (rightmost)    

### Case 2: Node is BLACK and its child is RED.

    1. Change child's color to BLACK to maintain Black-Depth.
    2. Remove the node and link its child to parent.

        P         P       
        |         |      
        B  -->  C(B)      
       / \           
    C(R)  null (rightmost)

### Case 3: Double-Black Violation (Merge and Transfer)
![DoubleBlackViolation](/images/RedBlackTreeDoubleBlackViolation.png)

### Steps to fix After Deletion 
![DoubleBlackViolation](/images/RedBlackTreeDoubleBlackViolation2.png)

