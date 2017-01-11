package designpattern.behavioral.iterator;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SomeList<E> implements List<E> {

    Object[] array;
    int size;

    private class Itr implements Iterator<E> {

        private int cursor;          // index of next element to return
        private int lastReturn = -1; // index of last element returned

        /**
         * Return true if the iterator has more elements.
         *
         * @return true if the iterator has more elements
         */
        @Override
        public boolean hasNext() {
            return cursor != size; // array sizer
        }

        /**
         * Returns the next element in the iterator.
         *
         * @return the next element in the iterator.
         * @throws NoSuchElementException if the iterator has no more elements.
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() throws NoSuchElementException {
            if (cursor == size)
                throw new NoSuchElementException("No next element");

            return (E)array[lastReturn = cursor++];
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation)
         */
        @Override
        public void remove() {
            if (lastReturn < 0)
                throw new IllegalStateException("Noting to remove");

            // call outer remove method
            SomeList.this.remove(lastReturn); // the last one returned
            cursor = lastReturn--;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }


}