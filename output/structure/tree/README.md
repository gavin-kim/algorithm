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
        void range(Node node, List<Key> list, Key lo, Key hi)
        {
            if (node == null) return;
            int cmplo = lo.compareTo(x.key);
            int cmphi = hi.compareTo(x.key);
            if (cmplo < 0) keys(x.left, list, lo, hi);          // search left more
            if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); // add a key
            if (cmphi > 0) keys(x.right, list, lo, hi);         // search right more
        }
![Range](/images/BST.range.png)
