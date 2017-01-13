package structure.list;

import java.util.Iterator;

/**
 * LinkedList returns a raw element
 * LinkedPositionalList returns a position that can traverse inside List
 */
public class LinkedPositionalList<E> implements PositionalList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public LinkedPositionalList() {
        head = new Node<>(null, null, null); // element, prev, next
        tail = new Node<>(null, head, null);
        head.setNext(tail);
    }

    /* private utilities */
    /** Validates the position and returns it as a node. */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Invalid Position Type");

        Node<E> node = (Node<E>) p;

        if (node.getNext() == null)
            throw new IllegalArgumentException("Position is no longer in the list");

        return node;
    }

    /** Returns the given node as a Position (or null, if it is a sentinels) */
    private Position<E> position(Node<E> node) {
        if (node == head || node == tail)
            return null;
        return node;
    }

    /**
     * Adds element to the linked list between the given nodes
     * prev - new node - next
     * */
    private Position<E> addBetween(E e, Node<E> prev, Node<E> next) {
        Node<E> node = new Node<>(e, prev, next); // create and link a new node
        prev.setNext(node);
        next.setPrev(node);
        size++;
        return node;
    }

    /* public methods */
    /**
     * Running Time: O(1)
     * Returns the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Running Time: O(1)
     * Tests whether the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Running Time: O(1)
     * Returns the first Position in the list (or null, if empty).
     */
    @Override
    public Position<E> first() {
        return (position(head.getNext()));
    }

    /**
     * Running Time: O(1)
     * Returns the last Position in the list (or null, if empty).
     */
    @Override
    public Position<E> last() {
        return (position(tail.getPrev()));
    }

    /**
     * Running Time: O(1)
     * Returns the Position immediately before Position (or null, if p is first).
     */
    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /**
     * Running Time: O(1)
     * Returns the Position immediately after Position (or null, if p is last).
     */
    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }



    /**
     * Running Time: O(1)
     * Inserts element at the front of the list and returns its new Position
     */
    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, head, head.getNext());
    }

    /**
     * Running Time: O(1)
     * Inserts element at the back of the list and returns its new Position
     */
    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, tail.getPrev(), tail);
    }

    /**
     * Running Time: O(1)
     * Inserts element immediately before Position and returns its new Position
     */
    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /**
     * Running Time: O(1)
     * Inserts element immediately after Position and returns its new Position
     */
    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /**
     * Running Time: O(1)
     * Replaces the element stored at Position and returns the replaced element.
     */
    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E element = node.getElement();
        node.setElement(e);
        return element;
    }

    /**
     * Running Time: O(1)
     * Removes the element stored at Position and returns it (invalidating Position)
     */
    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> prev = node.getPrev();
        Node<E> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;

        E element = node.getElement();
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);
        return element;
    }

    public static void insertionSort(PositionalList<Comparable> list) {

        // sorted[0 1 walk 3 4... marker] ~ pivot ~ unsorted[... n]
        // marker: to check first to last instead of index
        // pivot: compare target
        // walk: to check searching position

        Position<Comparable> marker = list.first();

        while (marker != list.last()) {
            Position<Comparable> pivot = list.after(marker);
            Comparable value = pivot.getElement();

            if (value.compareTo(pivot.getElement()) > 0)
                marker = pivot;
            else {

                Position<Comparable> walk = marker;

                // find leftmost: LinkedList doesn't need to move, just remember walk point
                while (list.first() != walk && marker.getElement().compareTo(value) > 0)
                    walk = list.before(walk);

                list.remove(pivot);
                list.addBefore(walk, value);
            }

        }
    }

    public Iterable<Position<E>> positions() {
        return PositionIterator::new; // iterable interface (lambda)
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();  // next position
        private Position<E> lastReturn = null; // last position returned

        @Override
        public boolean hasNext() {
            return cursor != null; // position is null when it reaches head or tail
        }

        @Override
        public Position<E> next() {
            if (cursor == null)
                throw new IllegalStateException("Nothing left");

            lastReturn = cursor;
            cursor = after(cursor);
            return lastReturn;
        }

        @Override
        public void remove() {
            if (lastReturn == null)
                throw new IllegalStateException("Noting to remove");

            // node - lastReturn - cursor -> node - cursor
            Position<E> toRemove = lastReturn;            // position to remove
            lastReturn = before(lastReturn);              // move to prev node
            LinkedPositionalList.this.remove(toRemove); // remove from outer list

        }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> posIterator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }


    private static class Node<E> implements Position<E> {

        private E element;    // reference to the element stored at this node
        private Node<E> prev; // reference to the previous node in the list
        private Node<E> next; // reference to the next node in the list

        public Node(E e, Node<E> prev, Node<E> next) {
            this.element = e;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Returns the element stored at this position
         *
         * @return the stored element
         * @throws IllegalStateException if position no longer valid
         */
        @Override
        public E getElement() throws IllegalStateException {
            if (next == null) // head - sentinels - tail (sentinels always have tail except the tail node)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
}
