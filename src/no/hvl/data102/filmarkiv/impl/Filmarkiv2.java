package no.hvl.data102.filmarkiv.impl;

import java.util.ArrayList;
import java.util.List;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {
	
	private int antall;
	private LinearNode<Film> start;
	
	private class LinearNode<T>{
		public T data;
		public LinearNode<T> neste;
		
		public LinearNode(T data, LinearNode<T> neste) {
			this.data = data;
			this.neste = neste;
		}
		
		
	}
	

	public Filmarkiv2(int antall, LinearNode<Film> start) {
		this.antall = antall;
		this.start = start;
	}

	@Override
	public Film finnFilm(int nr) {
		LinearNode<Film> film =start;
		
		while (film != null) {
			if (film.data.getFilmnr() == nr) {
				return film.data;
			}
			film = film.neste;
		}
		return null;
	}

	@Override
	public void leggTilFilm(Film nyFilm) {
		LinearNode<Film> node = new LinearNode<>(nyFilm, start);
		start = node;
		antall++;
	}

	@Override
	public boolean slettFilm(int filmnr) {
		LinearNode<Film> aktuell = start;
		LinearNode<Film> forrige = null;
		
		while (aktuell != null) {
			if (aktuell.data.getFilmnr() == filmnr) {
				if (forrige == null){
					start = aktuell.neste;
				} else {
					forrige.neste = aktuell.neste;
				}
				antall--;
				return true;
			}
			forrige = aktuell;
			aktuell = aktuell.neste;
		}
		return false;
	}

	@Override
	public Film[] soekTittel(String delstreng) {
		List<Film> tittel = new ArrayList<>();
		
		LinearNode<Film> filmtittel = start;
		
		while(filmtittel != null) {
			if (filmtittel.data.getTittel().toLowerCase().contains(delstreng.toLowerCase())) {
				tittel.add(filmtittel.data);
			}
			filmtittel = filmtittel.neste;
		}
		return tittel.toArray(new Film[0]);
	}

	@Override
	public Film[] soekProdusent(String delstreng) {
		List<Film> prod = new ArrayList<>();
		
		LinearNode<Film> aktuell = start;
		
		while(aktuell != null) {
			if (aktuell.data.getProdusent().toLowerCase().contains(delstreng.toLowerCase())) {
				prod.add(aktuell.data);
			}
			aktuell = aktuell.neste;
		}
		return prod.toArray(new Film[0]);
	}

	@Override
	public int antall(Sjanger sjanger) {
		int antallSjangre = 0;
		LinearNode<Film> film = start;
		
		while (film != null) {
			if (film.data.getSjanger() == sjanger) {
				antallSjangre++;
			}
			film = film.neste;
		}
		return antallSjangre;
	}

	@Override
	public int antall() {
		return antall;
	}
	
	
	

}
