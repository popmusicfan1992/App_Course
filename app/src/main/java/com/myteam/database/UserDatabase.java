package com.myteam.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.myteam.model.Student;



@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
