# Binary Search Tree

## Delete

    Case1: The node has a child or no child.
    Case2: The node has 2 children.
![Delete the minimum](/images/BST.delete.png) 
![Delete a key](/images/BST.delete2.png)


## Floor and Select
![Floor](/images/BST.floor.png)
![Select](/images/BST.select.png)

## Range
        while (node != null) {
            if (low <= node.key <= high) list.add(node) 
            if (node.key > low) stack(node.left);   
            if (node.key < high) stack(node.right);
            node = stack.pop();
        }
![Range](/images/BST.range.png)
