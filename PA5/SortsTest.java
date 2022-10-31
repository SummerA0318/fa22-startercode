import java.util.ArrayList;

import static org.junit.Assert.*;

public class SortsTest {
    ArrayList<Integer> l1;
    ArrayList<Integer> sortedl1;
    ArrayList<String> l2;
    ArrayList<String> sortedl2;
    ArrayList<Double> l3;
    ArrayList<Double> sortedl3;
    Sorts<Integer> s;
    Sorts<String> sInt;
    Sorts<Double> sDou;

    @org.junit.Before
    public void setUp() throws Exception {
        l1 = new ArrayList<Integer>();
        sortedl1 = new ArrayList<Integer>();
        l2 = new ArrayList<String>();
        sortedl2 = new ArrayList<String>();
        l3 = new ArrayList<Double>();
        sortedl3 = new ArrayList<Double>();
        s = new Sorts<>();
        sInt = new Sorts<>();
        sDou = new Sorts<>();
    }

    public void setRandomL1() {
        l1.add(5);
        l1.add(2);
        l1.add(4);
        l1.add(6);
        l1.add(1);
        l1.add(3);
    }

    public void setSortedL1() {
        sortedl1.add(1);
        sortedl1.add(2);
        sortedl1.add(3);
        sortedl1.add(4);
        sortedl1.add(5);
        sortedl1.add(6);
    }

    public void setPartiallySortedL1() {
        sortedl1.add(5);
        sortedl1.add(1);
        sortedl1.add(2);
        sortedl1.add(4);
        sortedl1.add(6);
        sortedl1.add(3);
    }

    public void setSortedL2() {
        l2.add("a");
        l2.add("b");
        l2.add("c");
        l2.add("d");
        l2.add("e");
        l2.add("f");
    }

    public void setReverseL3(){
        l3.add(5.6);
        l3.add(5.2);
        l3.add(3.2);
        l3.add(3.2);
        l3.add(2.0);
        l3.add(0.318);
    }

    public void setSortedL3() {
        sortedl3.add(0.318);
        sortedl3.add(2.0);
        sortedl3.add(3.2);
        sortedl3.add(3.2);
        sortedl3.add(5.2);
        sortedl3.add(5.6);
    }

    public void setRandomL3() {
        l3.add(3.2);
        l3.add(5.2);
        l3.add(2.0);
        l3.add(3.2);
        l3.add(0.318);
        l3.add(5.6);
    }

    @org.junit.Test
    public void testInsertion() {
        this.setRandomL1();
        s.InsertionSort(l1, 0, 5);
        setSortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testInsertion2() {
        this.setRandomL1();
        s.InsertionSort(l1, 1, 4);
        setPartiallySortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testInsertion3() {
        this.setSortedL2();
        sortedl2 = l2;
        sInt.InsertionSort(l2, 1, 5);
        assertEquals(sortedl2, l2);
    }

    @org.junit.Test
    public void testInsertion4() {
        this.setReverseL3();
        this.setSortedL3();
        sDou.InsertionSort(l3, 0, 5);
        assertEquals(sortedl3, l3);
    }

    @org.junit.Test
    public void testMerge() {
        this.setRandomL1();
        s.MergeSort(l1, 0, 5);
        setSortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testMerge2() {
        this.setRandomL1();
        s.MergeSort(l1, 1, 4);
        setPartiallySortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testMerge3() {
        this.setSortedL2();
        sortedl2 = l2;
        sInt.MergeSort(l2, 1, 5);
        assertEquals(sortedl2, l2);
    }

    @org.junit.Test
    public void testMerge4() {
        this.setReverseL3();
        this.setSortedL3();
        sDou.MergeSort(l3, 0, 5);
        assertEquals(sortedl3, l3);
    }

    @org.junit.Test
    public void testQuick() {
        this.setRandomL1();
        s.QuickSort(l1, 0, 5);
        setSortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testQuick2() {
        this.setRandomL1();
        s.QuickSort(l1, 1, 4);
        setPartiallySortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testQuick3() {
        this.setSortedL2();
        sortedl2 = l2;
        sInt.QuickSort(l2, 1, 5);
        assertEquals(sortedl2, l2);
    }

    @org.junit.Test
    public void testQuick4() {
        this.setReverseL3();
        this.setSortedL3();
        sDou.QuickSort(l3, 0, 5);
        assertEquals(sortedl3, l3);
    }

    @org.junit.Test
    public void testModifiedQuick() {
        this.setRandomL1();
        s.Modified_QuickSort(l1, 0, 5, 2);
        setSortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testModifiedQuick2() {
        this.setRandomL1();
        s.Modified_QuickSort(l1, 1, 4, 2);
        setPartiallySortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testModifiedQuick3() {
        this.setSortedL2();
        sortedl2 = l2;
        sInt.Modified_QuickSort(l2, 1, 5, 2);
        assertEquals(sortedl2, l2);
    }

    @org.junit.Test
    public void testModifiedQuick4() {
        this.setReverseL3();
        this.setSortedL3();
        sDou.MergeSort(l3, 0, 5);
        assertEquals(sortedl3, l3);
    }

    @org.junit.Test
    public void testCocktail() {
        this.setRandomL1();
        s.cocktailSort(l1, 0, 5);
        setSortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testCocktail2() {
        this.setRandomL1();
        s.cocktailSort(l1, 1, 4);
        setPartiallySortedL1();
        assertEquals(sortedl1, l1);
    }

    @org.junit.Test
    public void testCocktail3() {
        this.setSortedL2();
        sortedl2 = l2;
        sInt.cocktailSort(l2, 1, 5);
        assertEquals(sortedl2, l2);
    }

    @org.junit.Test
    public void testCocktail4() {
        this.setReverseL3();
        this.setSortedL3();
        sDou.cocktailSort(l3, 0, 5);
        assertEquals(sortedl3, l3);
    }

    @org.junit.Test
    public void testBucket() {
        this.setReverseL3();
        this.setSortedL3();
        assertEquals(sortedl3, sDou.bucketSort(l3));
    }

    @org.junit.Test
    public void testBucket2() {
        this.setSortedL3();
        l3 = sortedl3;
        assertEquals(sortedl3, sDou.bucketSort(l3));
    }

    @org.junit.Test
    public void testBucket3() {
        this.setRandomL3();
        this.setSortedL3();
        assertEquals(sortedl3, sDou.bucketSort(l3));
    }
}