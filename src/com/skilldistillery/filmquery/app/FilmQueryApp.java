package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.InventoryFilm;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

//	private void test() {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//		Actor actor = db.findActorById(1);
//		System.out.println(actor);
//		List<Actor> actors = db.findActorsByFilmId(1);
//		for (Actor actor2 : actors) {
//			System.out.println(actor2);
//		}
//
//	}

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
			option = tryIntInput(option, input);
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
			System.out.print("\nPLEASE ENTER A FILM ID >> ");
			filmId = tryIntInput(filmId, input);
			
				Film film1 = db.findFilmById(filmId);
				if (film1 == null) {
					System.out.println("\n***** YOU DID NOT ENTER A VALID FILM ID *****");
				}else {
				System.out.println("\n" + 1 + ": " + film1.userFriendlyToString());
				printFilmsActors(filmId, 1);
				List<Film> films = new ArrayList<>();
				films.add(film1);
				subMenu(films, input);
				}
			break;
		case 2:
			System.out.print("\nSEARCH KEYWORD TO LOOK UP FILM >> ");
			keyword = input.next();
			List<Film> films = db.findFilmsByKeyword(keyword);
			if (films.size() == 0) {
				System.out.println("\n***** NO MATCHING FILMS FOUND *****\n");
			} else {
				int filmNumberInList = 0;
				for (Film film : films) {
					filmNumberInList++;
					System.out.println("\n" + filmNumberInList + ": " + film.userFriendlyToString());
					printFilmsActors(film.getId(), filmNumberInList);
				}
				subMenu(films, input);
			}
			break;
		case 3:
			System.out.println("GOODBYE!");
			break;
		}
	}

	private void subMenu(List<Film> films, Scanner input) {
		int option = 0;
		while (option != 1 && option != 2) {
			System.out.println("PLEASE SELECT AN OPTION:");
			System.out.println();
			System.out.println("1) RETURN TO THE MAIN MENU");
			System.out.println("2) VIEW ALL OF A FILM'S DETAILS");
			System.out.println();
			System.out.print("SELECTION >> ");
			option = tryIntInput(option, input);
			if (option < 1 || option > 2) {
				System.out.println("\n***** PLEASE ENTER A VALID OPTION *****\n");
			} else {
				switch (option) {
				case 1:
					System.out.println("\n***** RETURNING TO MAIN MENU *****\n");
					break;
				case 2:
					int filmInList = 0;
					int seeAnotherFilmAnswer = 0;
					boolean goAgain = true;
					while (goAgain == true) {
						System.out.println("WHICH FILM WOULD YOU LIKE TO SEE ALL DETAILS?\n");
						int filmNumberInList = 0;
						for (Film film : films) {
							filmNumberInList++;
							System.out.println(filmNumberInList + ": " + film.getTitle());
						}
						System.out.print("\nSELECTION >> ");
						filmInList = tryIntInput(filmInList, input);
						if (filmInList > 0 && filmInList <= films.size()) {
							System.out.println(films.get(filmInList - 1));
							System.out.println();
							printCopiesAndCondition(films.get(filmInList - 1).getId());
							if (films.size() > 1) {
								System.out.println("\nSEE ALL DETAILS OF ANOHTER FILM?");
								System.out.println();
								System.out.println("1) YES");
								System.out.println("2) NO");
								System.out.println();
								System.out.print("SELECTION >> ");
								switch (tryIntInput(seeAnotherFilmAnswer, input)) {
								case 1:
									continue;
								case 2:
									goAgain = false;
									break;
								}
							} else {
								goAgain = false;
							}
						} else {
							System.out.println("\n***** PLEASE ENTER A VALID OPTION *****\n");
						}
					}
					System.out.println("\n*****   RETURNING TO MAIN MENU  *****\n");
					break;
				}
			}
		}
	}

	private void printCopiesAndCondition(int filmId) {
		List<InventoryFilm> inventoryFilms = db.findAllCopiesAndConditionOfFilm(filmId);
		for (InventoryFilm inventoryItem : inventoryFilms) {
			System.out.println(inventoryItem);
		}
	}

	private int tryIntInput(int option, Scanner input) {
		try {
			option = input.nextInt();
		} catch (InputMismatchException ime) {
			input.nextLine();
			option = 0;
		}
		return option;
	}

	private void printFilmsActors(int filmId, int filmNumberInList) {
		int i = 0;
		String actorsInFilm = "   ACTORS IN FILM: ";
		if (filmNumberInList < 10) {
			System.out.print(actorsInFilm);
		} else if (filmNumberInList > 9 && filmNumberInList < 100) {
			System.out.print(" " + actorsInFilm);
		} else if (filmNumberInList > 99 && filmNumberInList < 1000) {
			System.out.print("  " + actorsInFilm);
		} else {
			System.out.print("   " + actorsInFilm);
		}
		List<Actor> actors = db.findActorsByFilmId(filmId);
		for (Actor actor : actors) {
			if (i == actors.size() - 1) {
				System.out.print(actor);
			} else {
				System.out.print(actor + ", ");
			}
			i++;
		}
		System.out.println("\n");
	}
}
