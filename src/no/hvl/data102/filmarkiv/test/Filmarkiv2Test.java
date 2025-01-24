package no.hvl.data102.filmarkiv.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Filmarkiv2;
import no.hvl.data102.filmarkiv.impl.Sjanger;


class Filmarkiv2Test {

    private Filmarkiv2 filmarkiv;

    @BeforeEach
    void setup() {
        filmarkiv = new Filmarkiv2(0, null);

        // Legg til noen testdata
        filmarkiv.leggTilFilm(new Film(1, "Produsent1", "Film1", 2023, Sjanger.ACTION, "Selskap1"));
        filmarkiv.leggTilFilm(new Film(2, "Produsent2", "Film2", 2022, Sjanger.DRAMA, "Selskap2"));
        filmarkiv.leggTilFilm(new Film(3, "Produsent1", "Film3", 2021, Sjanger.ACTION, "Selskap3"));
    }

    @Test
    void testLeggTilFilm() {
        Film nyFilm = new Film(4, "Produsent4", "Film4", 2024, Sjanger.HISTORY, "Selskap4");
        filmarkiv.leggTilFilm(nyFilm);

        assertEquals(4, filmarkiv.antall());
        assertNotNull(filmarkiv.finnFilm(4));
    }

    @Test
    void testSlettFilm() {
        assertTrue(filmarkiv.slettFilm(1));
        assertEquals(2, filmarkiv.antall());
        assertNull(filmarkiv.finnFilm(1));
    }

    @Test
    void testFinnFilm() {
        Film film = filmarkiv.finnFilm(2);
        assertNotNull(film);
        assertEquals("Film2", film.getTittel());
    }

    @Test
    void testSoekTittel() {
        Film[] resultater = filmarkiv.soekTittel("Film");
        assertEquals(3, resultater.length);

        resultater = filmarkiv.soekTittel("1");
        assertEquals(1, resultater.length);
        assertEquals("Film1", resultater[0].getTittel());
    }

    @Test
    void testSoekProdusent() {
        Film[] resultater = filmarkiv.soekProdusent("Produsent1");
        assertEquals(2, resultater.length);
    }

    @Test
    void testAntall() {
        assertEquals(3, filmarkiv.antall());
    }

    @Test
    void testAntallSjangre() {
        assertEquals(2, filmarkiv.antall(Sjanger.ACTION));
        assertEquals(1, filmarkiv.antall(Sjanger.DRAMA));
        assertEquals(0, filmarkiv.antall(Sjanger.HISTORY));
    }
}

