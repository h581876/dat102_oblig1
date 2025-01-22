package no.hvl.data102.filmarkiv.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

}
