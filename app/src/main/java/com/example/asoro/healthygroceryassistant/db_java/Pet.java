package com.example.asoro.healthygroceryassistant.db_java;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Pet {
	@PrimaryKey(autoGenerate = true)
	int id;

	String name;
	// other fields
	int userId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}