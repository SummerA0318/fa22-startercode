/*
    Name: Yubing Lin
    PID:  A16994291
 */

import java.util.EmptyStackException;

/**
 * Some basic methods to use String Stack
 * @author Yubing Lin
 * @since  Oct 7, 2022
 */
public class StringStack {

    /* instance variables, feel free to add more if you need */
    private String[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;
    private int originalCapacity = 0;
    public static final double DEFAULT_SHRINK = 0.25;
    public static final double DEFAULT_LOAD = 0.75;
    public static final int CAPACITY_THRESHOLD = 5;
    public static final double LOAD_THRESHOLD = 0.67;
    public static final double SHRINK_THRESHOLD = 0.33;
    public static final int DOUBLE_FACTOR = 2;
    public static final int HALF_FACTOR = 2;

    /**
     * Constructor for the class which three variables all have specified values.
     * Initialize the variables and throw exceptions
     * @param capacity is the original number of elements the stack can contain
     * @param loadF is the specified fraction that once the percent of elements in stack reaches
    the stack need to double its capacity
     * @param shrinkF is the specified fraction that once the percent of elements in stack reaches
    the stack need to half its capacity
     * @throws IllegalArgumentException when the given capacity or factors are
     * out of their threshold
     */
    public StringStack(int capacity, double loadF, double shrinkF) {
        this.nElems = 0;
        this.originalCapacity = capacity;
        if (capacity >= CAPACITY_THRESHOLD & LOAD_THRESHOLD <= loadF & loadF <= 1 &
                0 < shrinkF & shrinkF <= SHRINK_THRESHOLD) {
            this.data = new String[capacity];
            this.loadFactor = loadF;
            this.shrinkFactor = shrinkF;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor for the class which two variables have specified values.
     * Initialize the variables and throw same exceptions as the previous one
     */
    public StringStack(int capacity, double loadF) {
        this(capacity, loadF, DEFAULT_SHRINK);
    }

    /**
     * Constructor for the class which two variables have specified values.
     * Initialize the variables and throw same exceptions as the previous one
     */
    public StringStack(int capacity) {
        this(capacity, DEFAULT_LOAD, DEFAULT_SHRINK);
    }

    /**
     * Determine if the stack is empty or not.
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (this.nElems == 0);
    }

    /**
     * Clear the stack. Delete everything in it and reset the capacity to the original one.
     */
    public void clear() {
        this.nElems = 0;
        this.data = new String[this.originalCapacity];
    }

    /**
     * Determine the number of elements in the stack
     * @return an int which is the number of elements currently stored
     */
    public int size() {
        return this.nElems;
    }

    /**
     * Determine the current capacity of the stack
     * @return an int which is the number of elements the stack can store
     */
    public int capacity() {
        return this.data.length;
    }

    /**
     * Return the element with largest index
     * @return the last element in the stack
     * @throws EmptyStackException when the stack is empty
     */
    public String peek() {
        if (nElems == 0) {
            throw new EmptyStackException();
        } else {
            return this.data[this.nElems - 1];
        }
    }

    /**
     * Add an element into the stack
     * @param element the int to be added into the stack
     */
    public void push(String element) {
        double doubleElems = this.nElems;
        double doubleLength = this.data.length;
        // Determine if the faction of elements will be more than threshold after pushing a element
        // If yes, double the capacity by create a new array with double capacity and assign it to
        // the stack and push the element. If not, push the element.
        if ((doubleElems + 1) / doubleLength >= this.loadFactor) {
            String[] doubleData = new String[this.data.length * DOUBLE_FACTOR];
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

    /**
     * Return the element with largest index and deleted it from the stack
     * @return the last element in the stack
     * @throws EmptyStackException if the stack is empty
     */
    public String pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        double doubleElems = this.nElems;
        double doubleLength = this.data.length;
        String head = this.data[this.nElems - 1];
        // Determine if the faction of elements will be lower than threshold after popping an
        // element. If yes, half the capacity by create a new array with halved capacity and
        // assign it to the stack and pop the element. If not, pop the element
        if ((doubleElems - 1) / doubleLength <= this.shrinkFactor) {
            if (this.data.length / HALF_FACTOR < this.originalCapacity) {
                String[] smallData = new String[this.originalCapacity];
                for (int i=0; i<smallData.length-1; i++) {
                    smallData[i] = this.data[i];
                }
                this.data = smallData;
            } else {
                String[] halfData = new String[this.data.length / HALF_FACTOR];
                for (int i=0; i<halfData.length-1; i++) {
                    halfData[i] = this.data[i];
                }
                this.data = halfData;
            }
        }
        else {
            String[] newData = new String[this.data.length];
            for (int i=0; i<newData.length-1; i++) {
                newData[i] = this.data[i];
            }
            this.data = newData;
        }
        nElems -= 1;
        return head;
    }

    /**
     * Add more than one element into the stack at the same time
     * @param elements an int array which stores all the elements to be added
     * @throws IllegalArgumentException if the given array of elements to add is null
     */
    public void multiPush(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        } else {
            // Do pushing for every element in the given elements
            for (int i=0; i<elements.length; i++) {
                this.push(elements[i]);
            }
        }
    }

    /**
     * Return and delete more than one element in the stack at the same time
     * @param amount the amount of elements to return and delete.
     * @return amount number of elements in the order of from larger index in the stack to smaller
     * ones or all the numbers in the stack
     * @throws IllegalArgumentException if the amount to return and delete is less than 0
     */
    public String[] multiPop(int amount) {
        // Determine if the popping amount is larger than the number of elements.
        // If the number is larger than the current number of elements than return all the elements
        // Otherwise return and delete the same number of elements as the given amount.
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if (amount > this.nElems) {
            String[] result = new String[this.nElems];
            int elems = this.nElems;
            for (int j=0; j<elems; j++) {
                result[j] = this.pop();
            }
            return result;
        } else {
            String[] result = new String[amount];
            for (int i=0; i<amount; i++) {
                result[i] = this.pop();
            }
            return result;
        }
    }
}
