import static org.junit.Assert.*;

import org.junit.*;

	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack one = new IntStack(0, 0.8, 0.2);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack one = new IntStack(10, 0.4, 0.2);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack one = new IntStack(10, 0.8, 0.6);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack two = new IntStack(5, 0.0);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack two = new IntStack(3, 0.8);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack two = new IntStack(3, 0.1);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack one = new IntStack(0);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack one = new IntStack(3);
	}
	
	@Test (expected = IllegalArgumentException.class)  // or any exception you expect
	public void testExceptionExample() {
		IntStack one = new IntStack(4);
	}