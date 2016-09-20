package Mediamac;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CDTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEstTrouve() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCD() {
		CD cd  = new CD("foo", "bar", "CD musical");
		assertEquals("foo", cd.getTitre());
		assertEquals("bar", cd.getAuteur());
		assertEquals("CD musical", cd.getFormat());
	}

	@Test
	public void testCD1() {
		CD cd  = new CD("foo", "bar", "CD music");
		assertEquals("foo", cd.getTitre());
		assertEquals("bar", cd.getAuteur());
		assertEquals("CD musical", cd.getFormat());
	}

}
