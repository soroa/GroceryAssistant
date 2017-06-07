package com.example.asoro.healthygroceryassistant.db_java;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface PetDao {
	@Query("SELECT * FROM pet")
	List<Pet> getAll();

	@Insert
	void insertAll(Pet... pets);

	@Delete
	void delete(Pet pet);

}


