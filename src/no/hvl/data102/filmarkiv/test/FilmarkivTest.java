package no.hvl.data102.filmarkiv.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Filmarkiv;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class FilmarkivTest {
	private Filmarkiv arkiv;
	private Film film1;
	private Film film2;
	private Film film3;

	@BeforeEach
	void oppsett() {
		arkiv = new Filmarkiv(10);
		film1 = new Film(1, "Peter Jackson", "Lord of the Rings 1", 2001, Sjanger.ACTION, "New Line");
		film2 = new Film(2, "Peter Jackson", "Lord of the Rings 2", 2002, Sjanger.ACTION, "New Line");
		film3 = new Film(3, "Christopher Nolan", "Inception", 2010, Sjanger.SCIFI, "Warner Bros");

		arkiv.leggTilFilm(film1);
		arkiv.leggTilFilm(film2);
		arkiv.leggTilFilm(film3);
	}

	@Test
	void metodeTester() {
		// Test find
		assertEquals(film1, arkiv.finnFilm(1));
		assertNull(arkiv.finnFilm(999));

		// Test delete
		assertTrue(arkiv.slettFilm(1));
		assertNull(arkiv.finnFilm(1));
		assertEquals(2, arkiv.antall());
	}

	@Test
	void søkeTester() {
		// Title search
		Film[] titleResultat = arkiv.soekTittel("Lord");
		assertEquals(2, titleResultat.length);

		// Producer search
		Film[] prodResultat = arkiv.soekProdusent("Peter Jackson");
		assertEquals(2, prodResultat.length);
	}

	@Test
	void telleTester() {
		assertEquals(3, arkiv.antall());
		assertEquals(2, arkiv.antall(Sjanger.ACTION));
		assertEquals(1, arkiv.antall(Sjanger.SCIFI));
		assertEquals(0, arkiv.antall(Sjanger.DRAMA));
	}
}


