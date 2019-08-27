package com.skilldistillery.filmquery.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.InventoryFilm;

class DatabaseAccessTests {
  private DatabaseAccessor db;

  @BeforeEach
  void setUp() throws Exception {
    db = new DatabaseAccessorObject();
  }

  @AfterEach
  void tearDown() throws Exception {
    db = null;
  }
  
  @Test
  void test_getFilmById_with_invalid_id_returns_null() {
    Film f = db.findFilmById(-42);
    assertNull(f);
  }
  
  @Test
  void test_getActorById_with_invalid_id_returns_null() {
	  Actor a = db.findActorById(-42);
	  assertNull(a);
  }
  
  @Test
  void test_findActorsByFilmId_with_filmId1_returns_10() {
	  List <Actor> actors = db.findActorsByFilmId(1);
	  assertEquals(10, actors.size());
  }
  
  @Test 
  void test_findFilmsByKeyword_with_all_returns_69() {
	  List<Film> films = db.findFilmsByKeyword("all");
	  assertEquals(69, films.size());
  }
  
  @Test
  void test_findAllCopiesAndConditionOfFilm_with() {
	  List<InventoryFilm> films = db.findAllCopiesAndConditionOfFilm(17);
	  assertEquals(17,films.size());
  }

}
