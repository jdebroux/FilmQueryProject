package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String USER = "student";
	private final String PASS = "student";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private PreparedStatement createPrepareStatement(String sql, Connection conn, int filmId) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		return stmt;
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, "
				+ "rental_rate, length, replacement_cost, rating, special_features"
				+ " FROM film WHERE id = ?";
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement stmt = createPrepareStatement(sql, conn, filmId);
			ResultSet filmRes = stmt.executeQuery();)
		{
			if (filmRes.next()) {
				film = new Film(filmRes.getInt("id"), filmRes.getString("title"), 
						filmRes.getString("description"), filmRes.getInt("release_year"),
						filmRes.getInt("language_id"), filmRes.getInt("rental_duration"),
						filmRes.getDouble("rental_rate"), filmRes.getInt("length"), 
						filmRes.getDouble("replacement_cost"), filmRes.getString("rating"),
						filmRes.getString("special_features"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}


	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT id, first_name, last_name"
				+ " FROM actor WHERE id = ?";
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement stmt = createPrepareStatement(sql, conn, actorId);
			ResultSet filmRes = stmt.executeQuery();){
			if (filmRes.next()) {
				actor = new Actor(filmRes.getInt("id"), filmRes.getString("first_name"),
						filmRes.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT actor.id, CONCAT(actor.first_name, \" \", actor.last_name) AS \"Full Name\" "
				+ "FROM actor JOIN film_actor ON film_actor.actor_id = actor.id "
				+ "WHERE film_actor.film_id = ?";
		try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement stmt = createPrepareStatement(sql, conn, filmId);
				ResultSet filmRes = stmt.executeQuery();){
				while (filmRes.next()) {
					actors.add(new Actor(filmRes.getInt("id"), filmRes.getString("Full Name")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return actors;
	}
}
