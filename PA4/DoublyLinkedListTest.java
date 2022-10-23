import org.junit.Test;

import java.util.AbstractList;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    AbstractList<Integer> l1;
    AbstractList<String> l2;

    @org.junit.Before
    public void setUp() throws Exception {
        l1 = new DoublyLinkedList<Integer>();
        l2 = new DoublyLinkedList<String>();
    }

    @Test(expected = NullPointerException.class)
    public void TestNPEInAdd(){
        l1.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestNPEInAddWithIndex(){
        l1.add(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIOBInAddWithIndex(){
        l1.add(-1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIOOBInGet(){
        l1.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIOOBInRemove(){
        l1.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestIOOBInSet(){
        l1.set(-1, 2);
    }

    @Test(expected = NullPointerException.class)
    public void TestNPEInSet(){
        l1.set(0, null);
    }

    @org.junit.Test
    public void add() {
    }
}