package com.example.asoro.healthygroceryassistant.db_java;

import java.util.List;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

public class UserAllPets {
	@Embedded
	public User user;
	@Relation(parentColumn = "user.id", entityColumn = "userId", entity = Pet.class)
	public List pets;
}
