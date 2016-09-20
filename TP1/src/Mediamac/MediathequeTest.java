package Mediamac;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MediathequeTest {
	private Mediatheque lib;

	@Before
	public void setUp() throws Exception {
		lib = new Mediatheque();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd1() {
		lib.add(new Livre("titrelivre a", "auteur b"));
		lib.add(new DVD("titre dvd a", "auteur b", 0));
		lib.add(new DVD("titre dvd a", "auteur b", 1));
		lib.add(new DVD("titre dvd c", "auteur c", 8));
		lib.add(new CD("titre CD a", "auteur b", "MP3"));
		List<Media> result = lib.filtre("zone", "7");
		assertEquals(1, result.size());
	}

	@Test
	public void testFiltre() {
		lib.add(new Livre("titrelivre a", "auteur b"));
		lib.add(new DVD("titre dvd a", "auteur b", 0));
		lib.add(new DVD("titre dvd a", "auteur b", 1));
		lib.add(new DVD("titre dvd c", "auteur c", 8));
		lib.add(new CD("titre CD a", "auteur b", "MP3"));
		List<Media> result = lib.filtre("zone", "7");
		assertEquals(2, result.size());
	}

}
