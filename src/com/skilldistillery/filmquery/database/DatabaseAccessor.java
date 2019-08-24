package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.InventoryFilm;

public interface DatabaseAccessor {
	
	public Film findFilmById(int filmId);

	public Actor findActorById(int actorId);

	public List<Actor> findActorsByFilmId(int filmId);
	
	public List<Film> findFilmsByKeyword(String keyword);
	
	public List<InventoryFilm> findAllCopiesAndConditionOfFilm(int filmId);
}
