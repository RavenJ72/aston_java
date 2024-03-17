package CustomUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class CustomArrayList<E> implements CustomList<E> {

    private E[] data;
    private int size;

    public CustomArrayList() {
        this(10);
    }

    public CustomArrayList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == data.length) {
            resize(size * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = element;
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(size, element);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        E removedElement = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public void remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        if (size > 1) {
            mergeSort(0, size - 1, c);
        }
    }

    private void mergeSort(int left, int right, Comparator<? super E> c) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid, c);
            mergeSort(mid + 1, right, c);
            merge(left, mid, right, c);
        }
    }

    private void merge(int left, int mid, int right, Comparator<? super E> c) {
        int i = left, j = mid + 1, k = 0;
        E[] temp = (E[]) new Object[right - left + 1];

        while (i <= mid && j <= right) {
            if (c.compare(data[i], data[j]) <= 0) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = data[i++];
        }

        while (j <= right) {
            temp[k++] = data[j++];
        }

        // Copy the sorted elements back to the original array
        for (int m = 0; m < temp.length; m++) {
            data[left + m] = temp[m];
        }
    }


    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    @Override
    public int size() {
        return size;
    }







}
