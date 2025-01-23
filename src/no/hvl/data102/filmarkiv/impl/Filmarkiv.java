package no.hvl.data102.filmarkiv.impl;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {
	
	private Film [] filmer;
	private int antall;
	
	public Filmarkiv(int kapasitet) {
		filmer = new Film[kapasitet];
		antall = 0;
	}
	
	@Override
	public Film finnFilm(int nr) {
		for (int i = 0; i < antall; i++) {
			if (filmer[i].getFilmnr() == nr) {
				return filmer [i];
			}
		}
		return null;
	}
	
	// Hjelpemetode for testing
	public Film[] hentFilmer() {
	    return filmer;
	}

	@Override
	public void leggTilFilm(Film nyfilm) {
		if (antall == filmer.length) {
			utvid();
		}
		filmer[antall] = nyfilm;
		antall++;
		
	}
	
	private void utvid() {
		Film[] nytabell = new Film[filmer.length * 2];
		System.arraycopy(filmer, 0, nytabell, 0, antall);
		filmer = nytabell;
	}
	
	@Override
	public boolean slettFilm(int filmnr){
		for (int i = 0; i < antall; i++) {
			if (filmer[i].getFilmnr() == filmnr) {
				filmer[i] = filmer[antall - 1];
				filmer[antall -1] = null;
				antall--;
				return true;
			}
		}
		
		return false;
	}


	@Override
	public Film[] soekTittel(String delstreng) {
		Film[] nyTab = new Film[antall];
		int antallTreff = 0;
		
		for (int i = 0; i < antall; i++) {
			if (filmer[i].getTittel().toLowerCase().contains(delstreng.toLowerCase())) {
				nyTab[antallTreff++] = filmer[i];
			}
		}
		return trimTab(nyTab, antallTreff);
	}


	@Override
	public Film[] soekProdusent(String delstreng) {
		Film[] nyTab = new Film[antall];
		int antallTreff = 0;
		
		for (int i = 0; i < antall; i++) {
			if (filmer[i].getProdusent().toLowerCase().contains(delstreng.toLowerCase())) {
				nyTab[antallTreff++] = filmer[i];
			}
		}
		return trimTab(nyTab, antallTreff);
		
	}


	@Override
	public int antall(Sjanger sjanger) {
		int antallSjangre = 0;
		for (int i = 0; i < antall; i++) {
			if(filmer[i].getSjanger() == sjanger) {
				antallSjangre++;
			}
		}
		return antallSjangre;
	}


	@Override
	public int antall() {
		return antall;
	}
	
	private Film[] trimTab(Film[] tab, int n) {
		Film[] nytab = new Film[n];
		int i = 0;
		while (i < n) {
			nytab[i] = tab[i];
			i++;
		}
		return nytab;
	}
	
	
	
	

}
