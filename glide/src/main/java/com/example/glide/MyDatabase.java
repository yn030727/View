package com.example.glide;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class} , version = 1)
public abstract class MyDatabase extends RoomDatabase {
    //1.数据库名字
    private static final String DATABASE_NAME = "my_db";
    //2.数据库实例声明
    private static MyDatabase databaseInstance;
    //synchronized是同步锁，这里修饰静态方法，相当于给类对象加锁
    public static synchronized MyDatabase getInstance(Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext() , MyDatabase.class , DATABASE_NAME).build();
        }
        return databaseInstance;
    }

    public abstract StudentDao studentDao();
}
