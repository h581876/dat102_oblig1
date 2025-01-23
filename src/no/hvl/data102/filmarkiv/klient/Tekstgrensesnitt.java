package no.hvl.data102.filmarkiv.klient;

import java.util.Scanner;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class Tekstgrensesnitt {
	private Scanner scanner = new Scanner(System.in);

	// Leser inn opplysninger om en film fra tastatur og returnere et Film-objekt
	public Film lesFilm() {
		System.out.print("Filmnummer: ");
        int filmnr = Integer.parseInt(scanner.nextLine());

        System.out.print("Tittel: ");
        String tittel = scanner.nextLine();

        System.out.print("Produsent: ");
        String produsent = scanner.nextLine();

        System.out.print("År: ");
        int aar = Integer.parseInt(scanner.nextLine());

        System.out.print("Sjanger (ACTION, DRAMA, HISTORY, SCIFI): ");
        Sjanger sjanger = Sjanger.valueOf(scanner.nextLine().toUpperCase());
        
        System.out.print("Filmselskap: ");
        String filmSelskap = scanner.nextLine();

        return new Film(filmnr, produsent, tittel, aar, sjanger, filmSelskap);
	}

	// Skriver ut en film med alle opplysninger på skjerm (husk tekst for sjanger)
	public void skrivUtFilm(Film film) {
		  if (film != null) {
	            System.out.println(film.toString());
	        } else {
	            System.out.println("Ingen film funnet.");
	        }
	}

	// Skriver ut alle filmer med en spesiell delstreng i tittelen
	public void skrivUtFilmDelstrengITittel(FilmarkivADT arkiv, String delstreng) {
		Film[] filmer = arkiv.soekTittel(delstreng);
        for (Film film : filmer) {
            skrivUtFilm(film);
        }
	}

	// Skriver ut alle Filmer av en produsent (produsent er delstreng)
	public void skrivUtFilmProdusent(FilmarkivADT arkiv, String delstreng) {
		Film[] filmer = arkiv.soekProdusent(delstreng);
        for (Film film : filmer) {
            skrivUtFilm(film);
        }
	}

	// Skriver ut en enkel statistikk som inneholder antall filmer totalt
	// og hvor mange det er i hver sjanger.
	public void skrivUtStatistikk(FilmarkivADT arkiv) {
		 System.out.println("Antall filmer totalt: " + arkiv.antall());
	        for (Sjanger sjanger : Sjanger.values()) {
	            System.out.println("Antall " + sjanger + "-filmer: " + arkiv.antall(sjanger));
	        }
	    }
	}
	// osv ... andre metode


