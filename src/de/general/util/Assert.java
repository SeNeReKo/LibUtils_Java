package de.general.util;

/**
 *
 * @author knauth
 */
public class Assert
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public static void assertEquals(String str, double obj1, double obj2, double delta)
	{
		if (obj1 == obj2) {
			return;
		} else
		if (Math.abs(obj1 - obj2) <= delta) {
			return;
		} else {
			fail(str + " expected=" + obj1 + " actual=" + obj2 + " delta=" + delta);
		}
	}

	public static void assertEquals(String str, float obj1, float obj2, float delta)
	{
		if (obj1 == obj2) {
			return;
		} else
		if (Math.abs(obj1 - obj2) <= delta) {
			return;
		} else {
			fail(str + " expected=" + obj1 + " actual=" + obj2 + " delta=" + delta);
		}
	}

	public static void assertEquals(String msg, int expected, int actual)
	{
		if (expected != actual) {
			fail(msg + " expected=" + expected + " actual=" + actual);
		}
	}

	public static void assertEquals(String msg, Object obj1, Object obj2)
	{
		if (obj1 == null && obj2 == null) {
			return;
		}

		if ((obj1 != null) && (obj2 != null) && obj1.equals(obj2)) {
			return;
		}

		fail(msg + " expected=" + obj1 + " actual=" + obj2);
	}

	public static void assertEquals(boolean obj1, boolean obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(byte obj1, byte obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(char obj1, char obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(double obj1, double obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(float obj1, float obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(short obj1, short obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(int obj1, int obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(long obj1, long obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(Object obj1, Object obj2)
	{
		assertEquals("", obj1, obj2);
	}

	public static void assertEquals(String msg, boolean obj1, boolean obj2)
	{
		assertEquals(msg, obj1, obj2);
	}

	public static void assertEquals(String msg, byte obj1, byte obj2)
	{
		assertEquals(msg, obj1, obj2);
	}

	public static void assertEquals(String msg, char obj1, char obj2)
	{
		assertEquals(msg, obj1, obj2);
	}

	public static void assertFalse(boolean condition)
	{
		assertFalse(null, condition);
	}

	public static void assertFalse(String message, boolean condition)
	{
		assertTrue(message, !condition);
	}

	public static void assertNotNull(Object obj)
	{
		assertNotNull(null, obj);
	}

	public static void assertNotNull(String msg, Object obj)
	{
		assertTrue(msg, obj != null);
	}

	public static void assertNotSame(Object obj1, Object obj2)
	{
		assertNotSame(null, obj1, obj2);
	}

	public static void assertNotSame(String msg, Object obj1, Object obj2)
	{
		if (obj1 != obj2) {
			return;
		}

		if (msg == null) {
			msg = "";
		}

		fail(msg + " expected and actual match");
	}

	public static void assertNull(Object obj)
	{
		assertNull(null, obj);
	}

	public static void assertNull(String msg, Object obj)
	{
		assertTrue(msg, obj == null);
	}

	public static void assertSame(Object obj1, Object obj2)
	{
		assertSame(null, obj1, obj2);
	}

	public static void assertSame(String msg, Object obj1, Object obj2)
	{
		if (obj1 == obj2) {
			return;
		}

		if (msg == null) {
			msg = "";
		}

		fail(msg + " expected and actual do not match");
	}

	public static void assertTrue(boolean condition)
	{
		assertTrue(null, condition);
	}

	public static void assertTrue(String message, boolean condition)
	{
		if (!condition) {
			fail(message);
		}
	}

	public static void fail()
	{
		fail(null);
	}

	public static void fail(String message)
	{
		throw new AssertionFailedError(message);
	}

}
