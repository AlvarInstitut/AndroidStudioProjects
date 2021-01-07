package com.example.connexiosqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteGestor( context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int )
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = ("CREATE TABLE EMPLEAT("
                + "num INTEGER CONSTRAINT cp_emp PRIMARY KEY, "
                + "nom TEXT, " + "depart INTEGER, " + "edat INTEGER, "
                + "sou REAL " + ")")
        db.execSQL(sql)
        db.execSQL("INSERT INTO EMPLEAT VALUES (1,'Andreu',10,32,1000.0)")
        db.execSQL("INSERT INTO EMPLEAT VALUES (2,'Bernat',20,28,1200.0)")
        db.execSQL("INSERT INTO EMPLEAT VALUES (3,'Clàudia',10,26,1100.0)")
        db.execSQL("INSERT INTO EMPLEAT VALUES (4,'Damià',10,40,1500.0)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}

