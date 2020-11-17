package com.example.coffeeshopskotlinroom

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.io.File


class SQLiteGestor( context: Context, name: String) : SQLiteOpenHelper(context, name, null, 1) {
    private val myContext: Context? = null

    init {

        val PATH_BD = "/data/data/" + context.packageName + "/databases"
        val NOM_BD = "CoffeeShops.sqlite"
        val dir = File(PATH_BD)
        if (!dir.exists())
            dir.mkdir()
        val f = File(dir, NOM_BD)
        if (!f.exists()) {
            val f_in = context.resources.openRawResource(0)
            f.writeBytes(f_in.readBytes())
        }
    }

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

}