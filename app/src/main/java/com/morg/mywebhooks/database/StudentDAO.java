package com.morg.mywebhooks.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface StudentDAO {
    @Insert
    Long insertStudent(StudentModel studentModel);
}
