package designpattern.behavioral.iterator;

import java.util.NoSuchElementException;

/**
 * Iterator has two type of a snapshot iterator and lazy iterator.
 * Iterator is used for inner class to directly access parent's elements.
 *
 * A class that implements Iterable<T> class must support Iterator<E>.
 *
 * Snapshot: creates the sequence of elements once the iterator is created.
 * Lazy: Iterator has position that points the element.
 *
 *
 */

public interface Iterator<E> {
    /**
     * Return true if the iterator has more elements.
     *
     * @return true if the iterator has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iterator.
     *
     * @return the next element in the iterator.
     * @throws NoSuchElementException if the iterator has no more elements.
     */
    E next() throws NoSuchElementException;

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation)
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
