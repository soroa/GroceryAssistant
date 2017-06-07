package com.example.asoro.healthygroceryassistant.db_java;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class, Pet.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public abstract UserDao userDao();

	public abstract PetDao petDao();
//
//	public abstract UserPetDao userPetDao();
}