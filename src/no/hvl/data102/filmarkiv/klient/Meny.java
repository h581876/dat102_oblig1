package no.hvl.data102.filmarkiv.klient;

import java.util.Scanner;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;


public class Meny {

	private Tekstgrensesnitt tekstgr;
	private FilmarkivADT filmarkiv;
	private Scanner scanner = new Scanner(System.in);

	public Meny(FilmarkivADT filmarkiv) {
		tekstgr = new Tekstgrensesnitt();
		this.filmarkiv = filmarkiv;
	}

	public void start() {

		boolean fortsett = true;
		while (fortsett) {
			visMeny();

			int valg = Integer.parseInt(scanner.nextLine());

			switch (valg) {
			case 1 -> tekstgr.skrivUtFilmDelstrengITittel(filmarkiv, lesInput("Del av tittel: "));
			case 2 -> tekstgr.skrivUtFilmProdusent(filmarkiv, lesInput("Produsentnavn: "));
			case 3 -> tekstgr.skrivUtStatistikk(filmarkiv);
			case 4 -> filmarkiv.leggTilFilm(tekstgr.lesFilm());
			case 5 -> {
				int filmnr = Integer.parseInt(lesInput("Filmnummer: "));
				Film film = filmarkiv.finnFilm(filmnr); 
				if (film != null) {
					tekstgr.skrivUtFilm(film); // Vis filmen med Tekstgrensesnitt
				} else {
					System.out.println("Fant ikke filmen.");
				}
			}
			case 6 -> {
				int filmnr = Integer.parseInt(lesInput("Filmnummer: "));
				if (filmarkiv.slettFilm(filmnr)) {
					System.out.println("Filmen ble slettet.");
				} else {
					System.out.println("Fant ikke filmen.");
				}
			}
			case 7 -> fortsett = false;
			default -> System.out.println("Ugyldig valg, prøv igjen.");
			}
		}
	}

	private void visMeny() {
		System.out.println("\n=== FILMARKIV MENY ===");
		System.out.println("1: Søk etter filmer basert på tittel");
		System.out.println("2: Søk etter filmer basert på produsent");
		System.out.println("3: Vis statistikk");
		System.out.println("4: Legg til ny film");
		System.out.println("5: Finn en film");
		System.out.println("6: Slett en film");
		System.out.println("7: Avslutt");
		System.out.print("Velg handling: ");
	}

	private String lesInput(String melding) {
		System.out.print(melding);
		return scanner.nextLine();
	}
}
