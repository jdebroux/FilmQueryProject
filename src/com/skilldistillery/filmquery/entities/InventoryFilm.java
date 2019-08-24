package com.skilldistillery.filmquery.entities;

public class InventoryFilm extends Film {
	private String condition;
	private int inventoryId;

	public InventoryFilm(int id, String title, String description, int releaseYear, String category, String language,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures, String condition, int inventoryId) {
		super(id, title, description, releaseYear, category, language, rentalDuration, rentalRate, length,
				replacementCost, rating, specialFeatures);
		this.condition = condition;
		this.inventoryId = inventoryId;
	}

	@Override
	public String toString() {
		String toString = "Inventory ID: " + inventoryId + ", Film: " + InventoryFilm.super.getTitle() + ", condition: " + condition + "]";
		toString = toString.toUpperCase();
		return toString;
	}

}
