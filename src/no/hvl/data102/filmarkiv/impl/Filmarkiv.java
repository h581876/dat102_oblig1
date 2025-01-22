package no.hvl.data102.filmarkiv.impl;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {
	
	private Film [] filmer;
	private int antall;
	
	public Filmarkiv(int kapasitet) {
		filmer = new Film[kapasitet];
		antall = 0;
	}
	
	
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
	
	
	
	

}
