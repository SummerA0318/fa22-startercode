import org.junit.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class IntStackTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor1() {
        IntStack one = new IntStack(0, 0.6, 0.3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor2() {
        IntStack one = new IntStack(10, -0.7, 6.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor3() {
        IntStack one = new IntStack(20, 1.2, -0.2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor4() {
        IntStack one = new IntStack(5, -0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor5() {
        IntStack one = new IntStack(20, 12.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor6() {
        IntStack one = new IntStack(3, 0.8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor7() {
        IntStack one = new IntStack(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor8() {
        IntStack one = new IntStack(-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructor9() {
        IntStack one = new IntStack(3);
    }

    @org.junit.Test
    public void isEmptyTest() {
        IntStack one = new IntStack(10, 0.8);
        assertEquals(true, one.isEmpty());
        one.push(1);
        assertEquals(false, one.isEmpty());
        one.pop();
        assertEquals(true, one.isEmpty());
    }

    @org.junit.Test
    public void clearTest() {
        IntStack one = new IntStack(5, 0.7, 0.3);
        one.push(1);
        one.clear();
        assertEquals(0, one.size());
        int[] arr = {1,2,3,4};
        one.multiPush(arr);
        one.clear();
        assertEquals(true, one.isEmpty());
        one.clear();
        assertEquals(5, one.capacity());
    }

    @org.junit.Test
    public void sizeTest() {
        IntStack one = new IntStack(10);
        assertEquals(0, one.size());
        int[] arr = {1,2,3,4};
        one.multiPush(arr);
        assertEquals(4, one.size());
        one.pop();
        assertEquals(3, one.size());
    }

    @org.junit.Test
    public void capacityTest() {
        IntStack one = new IntStack(5, 0.8, 0.2);
        assertEquals(5, one.capacity());
        int[] arr = {1,2,3,4};
        one.multiPush(arr);
        assertEquals(10, one.capacity());
        one.multiPop(2);
        assertEquals(5, one.capacity());
    }

    @org.junit.Test
    public void peekTest() {
        IntStack one = new IntStack(5);
        one.push(1);
        assertEquals(1, one.peek());
        one.push(2);
        one.push(3);
        one.push(4);
        assertEquals(4, one.peek());
    }

    @Test(expected = EmptyStackException.class)
    public void testExceptionPeek() {
        IntStack one = new IntStack(20);
        one.peek();
    }

    @org.junit.Test
    public void pushTest() {
        IntStack one = new IntStack(5, 0.7, 0.3);
        one.push(1);
        assertEquals(1, one.size());
        one.push(2);
        one.push(3);
        one.push(4);
        assertEquals(10, one.capacity());
        assertEquals(4, one.size());
        assertEquals(4, one.peek());
    }

    @org.junit.Test
    public void popTest() {
        IntStack one = new IntStack(5, 0.9);
        int[] add = {1,2,3,4,5,6,7,8};
        one.multiPush(add);
        assertEquals(8, one.pop());
        assertEquals(7, one.size());
        one.pop();
        assertEquals(6, one.pop());
        one.pop();
        one.pop();
        assertEquals(3, one.pop());
        assertEquals(5, one.capacity());
    }

    @Test(expected = EmptyStackException.class)
    public void testExceptionPop() {
        IntStack one = new IntStack(20, 0.8);
        one.pop();
    }

    @org.junit.Test
    public void multiPushTest() {
        IntStack one = new IntStack(5, 0.7, 0.3);
        int[] add1 = {1,2,3,4};
        one.multiPush(add1);
        assertEquals(4, one.size());
        assertEquals(10, one.capacity());
        int[] add2 = {4,3,2,1};
        one.multiPush(add2);
        assertEquals(8, one.size());
        assertEquals(1, one.peek());
        assertEquals(20, one.capacity());
        one.multiPush(add2);
        assertEquals(1, one.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMultiPush() {
        IntStack one = new IntStack(20);
        one.multiPush(null);
    }

    @org.junit.Test
    public void multiPopTest() {
        IntStack one = new IntStack(5, 0.7, 0.3);
        int[] add1 = {1,2,3,4};
        one.multiPush(add1);
        assertEquals(3, one.multiPop(10)[1]);
        assertEquals(true, one.isEmpty());
        assertEquals(5, one.capacity());
        int[] add2 = {4,3,2,1};
        one.multiPush(add2);
        assertEquals(1, one.multiPop(3)[0]);
        assertEquals(4, one.peek());
        assertEquals(5, one.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMultiPop() {
        IntStack one = new IntStack(20);
        one.push(1);
        one.multiPop(-1);
    }
}
