package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
		Actor actor = db.findActorById(1);
		System.out.println(actor);
		List<Actor> actors = db.findActorsByFilmId(1);
		for (Actor actor2 : actors) {
			System.out.println(actor2);
		}

	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		int option = 0;
		while (option != 3) {
			System.out.println("*****      LOADING DATABASE     *****\n");
			System.out.println("***** WELCOME TO SDVID DATABASE *****");
			System.out.println();
			System.out.println("PLEASE SELECT AN OPTION: ");
			System.out.println("1) LOOK UP A FILM BY ITS ID");
			System.out.println("2) LOOK UP A FILM BY A KEYWORD ");
			System.out.println("3) QUIT ");
			System.out.println();
			System.out.print("SELECTION >> ");
			try {
				option = input.nextInt();
			} catch (InputMismatchException ime) {
				input.nextLine();
				option = 0;
			}
			if (option < 1 || option > 3) {
				System.out.println("\n***** PLEASE ENTER A VALID OPTION *****\n");
			}
			menuSwitch(option, input);
		}
	}

	private void menuSwitch(int option, Scanner input) {
		int filmId = 0;
		String keyword = "";
		switch (option) {
		case 1:
			System.out.print("\nPLEASE ENTER A FILM ID 1-1000 OR 0 TO EXIT >> ");
			try {
				filmId = input.nextInt();
			} catch (InputMismatchException ime) {
				input.nextLine();
				filmId = 0;
			}
			if (filmId == 0) {
				break;
			}
			if (filmId < 1 || filmId > 1000) {
				System.out.println("\n***** YOU DID NOT ENTER A VALID FILM ID *****");
			} else {
				Film film = db.findFilmById(filmId);
				System.out.println("\n" + film.userFriendlyToString());
				printFilmsActors(filmId);
			}
			break;
		case 2:
			System.out.print("\nSEARCH KEYWORD TO LOOK UP FILM >> ");
			keyword = input.next();
			List<Film> films = db.findFilmsByKeyword(keyword);
			if (films.size() == 0) {
				System.out.println("\n***** NO MATCHING FILMS FOUND *****\n");
			} else {
				for (Film film : films) {
					System.out.println("\n" + film.userFriendlyToString() + "\n");
					printFilmsActors(film.getId());
				}
			}
			break;
		case 3:
			System.out.println("GOODBYE!");
			break;
		}
	}

	private void printFilmsActors(int filmId) {
		System.out.println("ACTORS INCLUDED IN THIS FILM: ");
		List <Actor> actors = db.findActorsByFilmId(filmId);
		for (Actor actor : actors) {
			System.out.println(actor);
		}
		System.out.println();
	}
}
