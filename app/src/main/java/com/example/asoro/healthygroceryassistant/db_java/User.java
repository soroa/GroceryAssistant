package com.example.asoro.healthygroceryassistant.db_java;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
	@PrimaryKey
	@ColumnInfo(name = "id")
	public int id;

	@ColumnInfo(name = "first_name")
	private String firstName;

	@ColumnInfo(name = "last_name")
	private String lastName;

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	// Getters and setters are ignored for brevity,
	// but they're required for Room to work.
}