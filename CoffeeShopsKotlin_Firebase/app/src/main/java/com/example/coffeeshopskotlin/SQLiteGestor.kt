package com.example.coffeeshopskotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteGestor(context: Context,name: String) : SQLiteOpenHelper(context, name,null,1) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {}

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {}
}