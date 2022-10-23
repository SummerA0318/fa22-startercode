/*
 * NAME: TODO
 * PID: TODO
 */

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.AbstractList;

/**
 * TODO
 * @author TODO
 * @since TODO
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.getPrev().setNext(this.getNext());
            this.getNext().setPrev(this.getPrev());
            this.setPrev(null);
            this.setNext(null);
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.nelems = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        Node toAdd = new Node(element, null, this.tail);
        this.tail.setNext(toAdd);
        this.tail = toAdd;
        this.nelems += 1;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * TODO: Javadoc comments
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > this.nelems) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        Node current = this.head;
        while (index != 0) {
            current = current.getNext();
            index -= 1;
        }
        Node toAdd = new Node(element, current, current.getPrev());
        current.getPrev().setNext(toAdd);
        current.setPrev(toAdd);
        this.nelems += 1;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        for (int i=0; i<this.nelems; i++) {
            Node current = this.head;
            this.head = this.head.getNext();
            current.remove();
        }
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * TODO: Javadoc comments
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        Node current = this.head;
        for (int i=0; i<this.nelems; i++) {
            if (current.getElement().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * TODO: Javadoc comments
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.nelems) {
            throw new IndexOutOfBoundsException();
        }
        Node current = this.head;
        while (index != 0) {
            current = current.getNext();
            index -= 1;
        }
        return current.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * TODO: Javadoc comments
     */
    private Node getNth(int index) {
        Node current = this.head;
        while (index != 0) {
            current = current.getNext();
            index -= 1;
        }
        return current;
    }

    /**
     * Determine if the list empty
     *
     * TODO: javadoc comments
     */
    @Override
    public boolean isEmpty() {
        return (this.nelems == 0);
    }

    /**
     * Remove the element from position index in the list
     *
     * TODO: javadoc comments
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.nelems) {
            throw new IndexOutOfBoundsException();
        }
        Node current = this.head;
        while (index != 0) {
            current = current.getNext();
            index -= 1;
        }
        T data = current.getElement();
        current.remove();
        this.nelems -= 1;
        return data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * TODO: javadoc comments
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index >= this.nelems) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        Node current = this.head;
        while (index != 0) {
            current = current.getNext();
            index -= 1;
        }
        T data = current.getElement();
        current.setElement(element);
        return data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * TODO: javadoc comments
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * TODO: javadoc comments
     */
    @Override
    public String toString() {
        String output = "";
        String newOutput = "";
        Node current = this.head;
        for (int i=0; i<this.nelems; i++) {
            newOutput = output + current.getElement() + " -> ";
            output = newOutput;
            current = current.getNext();
        }
        return "(head) -> " + output + "(tail)";
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * TODO: javadoc comments
     */
    public void removeMultipleOf(int base) {
        // TODO: complete implementation       
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * TODO: javadoc comments
     */
    public void swapSegment(DoublyLinkedList other, int splitIndex) {
        // TODO: complete implementation
    }

}