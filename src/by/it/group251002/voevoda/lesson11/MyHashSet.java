package by.it.group251002.voevoda.lesson11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {

    private SLL<E>[] set;
    private int size;

    public MyHashSet() {
        int defaultCapacity = 32;
        set = (SLL<E>[]) new SLL[defaultCapacity];
    }

    public MyHashSet(int capacity) {
        set = (SLL<E>[]) new SLL[capacity];
    }

    /////////////////////////////////////////////////////////////////////////
    //////               Обязательные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int index = 0;
        if (size != 0) {
            for (boolean foundFirstEl = false; !foundFirstEl && index < set.length; ++index) {
                if (set[index] != null) {
                    sb.append(set[index].getHead().getValue().toString());
                    foundFirstEl = true;
                }
            }
        }
        index = index == 0 ? 0 : index - 1;
        boolean skipedFirstEl = false;
        for (; index < set.length; ++index) {
            if (set[index] == null) {
                continue;
            }
            Node<E> ptr = skipedFirstEl ? set[index].getHead() : set[index].getHead().getNext();
            skipedFirstEl = true;
            for (; ptr != null; ptr = ptr.getNext()) {
                sb.append(", ");
                sb.append(ptr.getValue().toString());
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(set, null);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        int index = e.hashCode() % set.length;

        if (set[index] != null && set[index].contains(e)) {
            return false;
        }

        if (set[index] == null) {
            set[index] = new SLL<>();
        }
        set[index].append(e);
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = o.hashCode() % set.length;
        if (set[index] == null) {
            return false;
        }

       if (set[index].remove((E) o)) {
           --size;
           return true;
       }

        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = o.hashCode() % set.length;
        if (set[index] == null) {
            return false;
        }
        E e = (E) o;
        return set[index].contains(e);
    }

    /////////////////////////////////////////////////////////////////////////
    //////              Необязательные к реализации методы            ///////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public Iterator<E> iterator() {
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
}
