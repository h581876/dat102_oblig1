package no.hvl.data102.filmarkiv.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.xml.transform.Result;

import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Filmarkiv;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class FilmarkivTest {
	@Test
	void finnFilm() {
		Filmarkiv arkiv = new Filmarkiv(10);
		Film film = new Film(1, "Quentin Tarantino", "Pulp Fiction", 1994, Sjanger.ACTION, "Miramax Films");

		arkiv.leggTilFilm(film);

		assertEquals(film, arkiv.finnFilm(1), "Fant ikke riktig film");
		assertNull(arkiv.finnFilm(3));

	}

	@Test
	void testLeggTilFilm() {
		// Opprett et filmarkiv med plass til 5 filmer
		Filmarkiv arkiv = new Filmarkiv(5);

		// Opprett en film
		Film film = new Film(1, "Quentin Tarantino", "Pulp Fiction", 1994, Sjanger.ACTION, "Miramax Films");

		// Legg filmen til arkivet
		arkiv.leggTilFilm(film);

		// Sjekker tilstanden
		Film[] filmer = arkiv.hentFilmer();

		// Sjekker at filmen er lagt til på riktig plass
		assertNotNull(filmer[0], "Forventet at filmen var lagt til på plass 0");
		assertEquals(film, filmer[0], "Forventet at den lagrede filmen var lik den som ble lagt til");
	}

	@Test
	void testSlettFilm() {
		Filmarkiv arkiv = new Filmarkiv(5);

		Film film1 = new Film(1, "Quentin Tarantino", "Pulp Fiction", 1994, Sjanger.ACTION, "Miramax Films");
		Film film2 = new Film(2, "Christopher Nolan", "Inception", 2010, Sjanger.ACTION, "Warner Bros");

		arkiv.leggTilFilm(film1);
		arkiv.leggTilFilm(film2);

		// Slett den første filmen
		boolean slettet = arkiv.slettFilm(1);

		// Sjekker at slettingen var vellykket
		assertTrue(slettet, "returnerer true");

		// Sjekker at den slettede filmen ikke lenger finnes
		Film[] filmer = arkiv.hentFilmer();
		assertNull(filmer[1], "Forventet at posisjonen der film1 var, nå er tom");

		// Forsøk å slette en film som ikke finnes
		boolean slettetIkkeEksisterende = arkiv.slettFilm(3);
		assertFalse(slettetIkkeEksisterende, "returnerer false");
	}

	@Test
	void testSoekTittel() {
		Filmarkiv arkiv = new Filmarkiv(10);
		
		arkiv.leggTilFilm(new Film(1, "Regissør 1", "Lord of the Rings 1", 2001, Sjanger.ACTION, "filmselskap1"));
		arkiv.leggTilFilm(new Film(2, "Regissør 2", "Lord of the Rings 2", 2002, Sjanger.ACTION, "filmselskap1"));
		
		Film [] resultat = arkiv.soekTittel("Lord");
		
		assertEquals(2, resultat.length);
		
		
		
	}
	
	
	@Test
	void testSoekProdusent() {
		Filmarkiv arkiv = new Filmarkiv(10);
		
		arkiv.leggTilFilm(new Film(1, "Peter Jackson", "Lord of the Rings 1", 2001, Sjanger.ACTION, "filmselskap1"));
		arkiv.leggTilFilm(new Film(2, "Peter Jackson", "Lord of the Rings 2", 2002, Sjanger.ACTION, "filmselskap1"));
		
		Film [] resultat = arkiv.soekProdusent("Peter Jackson");
		
		assertEquals(2, resultat.length);
		
	}
	
	@Test
	void testAntallSjangre() {
	    Filmarkiv arkiv = new Filmarkiv(10);
	    arkiv.leggTilFilm(new Film(1, "Regissør 1", "Film 1", 2000, Sjanger.ACTION, "Selskap 1"));
	    arkiv.leggTilFilm(new Film(2, "Regissør 2", "Film 2", 2001, Sjanger.DRAMA, "Selskap 2"));
	    arkiv.leggTilFilm(new Film(3, "Regissør 3", "Film 3", 2002, Sjanger.ACTION, "Selskap 3"));

	    assertEquals(2, arkiv.antall(Sjanger.ACTION)); // Det er to ACTION-filmer
	    assertEquals(1, arkiv.antall(Sjanger.DRAMA));  // Det er én DRAMA-film
	    assertEquals(0, arkiv.antall(Sjanger.SCIFI));  // Ingen SCIFI-filmer
	}
	
	@Test
	void testAntall() {
	    Filmarkiv arkiv = new Filmarkiv(10); 
	    
	    // Sjekker at tomt
	    assertEquals(0, arkiv.antall());
	    
	    arkiv.leggTilFilm(new Film(1, "Regissør 1", "Film 1", 2000, Sjanger.ACTION, "Selskap 1"));
	    assertEquals(1, arkiv.antall());
	    
	    arkiv.leggTilFilm(new Film(2, "Regissør 2", "Film 2", 2001, Sjanger.DRAMA, "Selskap 2"));
	    assertEquals(2, arkiv.antall());
	}



}
