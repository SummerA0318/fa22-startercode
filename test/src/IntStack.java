/*
    Name: Yubing Lin
    PID:  A16994291
 */

import java.util.EmptyStackException;
import java.util.Arrays;
/**
 * TODO
 * @author Yubing Lin
 * @since  Oct 7, 2022
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    public static final double DEFAULTSHRINK = 0.25;
    public static final double DEFAULTLOAD = 0.75;
    public int originalCapacity = 0;

    public IntStack(int capacity, double loadF, double shrinkF) {
        this.nElems = 0;
        this.originalCapacity = capacity;
        if (capacity >= 5 & 0.67 <= loadF & loadF <= 1 & 0 < shrinkF & shrinkF <= 0.33) {
            this.data = new int[capacity];
            this.loadFactor = loadF;
            this.shrinkFactor = shrinkF;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public IntStack(int capacity, double loadF) {
        this(capacity, loadF, DEFAULTSHRINK);
    }

    public IntStack(int capacity) {
        this(capacity, DEFAULTLOAD, DEFAULTSHRINK);
    }

    public boolean isEmpty() {
        if (this.nElems == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        this.nElems = 0;
        this.data = new int[this.originalCapacity];
    }

    public int size() {
        return this.nElems;
    }

    public int capacity() {
        return this.data.length;
    }

    public int peek() {
        if (nElems == 0) {
            throw new EmptyStackException();
        } else {
            return this.data[this.nElems - 1];
        }
    }

    public void push(int element) {
        double doubleElems = this.nElems;
        double doubleLength = this.data.length;
        if ((doubleElems + 1) / doubleLength >= this.loadFactor) {
            int[] doubleData = new int[this.data.length * 2];
            for (int i=0; i<this.data.length; i++) {
                doubleData[i] = this.data[i];
            }
            doubleData[this.nElems] = element;
            this.data = doubleData;
        }
        else {
            this.data[this.nElems] = element;
        }
        nElems += 1;
    }

    public int pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        double doubleElems = this.nElems;
        double doubleLength = this.data.length;
        int head = this.data[this.nElems - 1];
        if ((doubleElems - 1) / doubleLength <= this.shrinkFactor) {
            if (this.data.length / 2 < this.originalCapacity) {
                int[] smallData = new int[this.originalCapacity];
                for (int i=0; i<smallData.length-1; i++) {
                    smallData[i] = this.data[i];
                }
                this.data = smallData;
            } else {
                int[] halfData = new int[this.data.length / 2];
                for (int i=0; i<halfData.length-1; i++) {
                    halfData[i] = this.data[i];
                }
                this.data = halfData;
            }
        }
        else {
            int[] newData = new int[this.data.length];
            for (int i=0; i<newData.length-1; i++) {
                newData[i] = this.data[i];
            }
            this.data = newData;
        }
        nElems -= 1;
        return head;
    }

    public void multiPush(int[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        } else {
            for (int i=0; i<elements.length; i++) {
                this.push(elements[i]);
            }
        }
    }

    public int[] multiPop(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if (amount > this.nElems) {
            int[] result = new int[this.nElems];
            int elems = this.nElems;
            for (int j=0; j<elems; j++) {
                result[j] = this.pop();
            }
            return result;
        } else {
            int[] result = new int[amount];
            for (int i=0; i<amount; i++) {
                result[i] = this.pop();
            }
            return result;
        }
    }
}
