package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> cont = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cont = new ArrayList<String>();

        EditText text = (EditText) findViewById(R.id.text);

// Des de la versió 3 d'Android, no es permet obrir una connexió des del thread principal.
// Per tant s'ha de crear un nou.
        sqlThread.start();

// i ara esperem a que finalitze el thread fill unint-lo (join)
        try {
            sqlThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i<cont.size();i++)
            text.append(cont.get(i) + " ");

    }


    Thread sqlThread = new Thread() {
        public void run() {
            try {
                Class.forName("org.postgresql.Driver");

                Connection conn = DriverManager.getConnection("jdbc:postgresql://89.36.214.106:5432/geo_ad","geo_ad", "geo_ad");

                String sentencia = "SELECT * FROM comarques ORDER BY 1";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sentencia);
                while (rs.next()) {
                    cont.add(rs.getString(1) + " - " + rs.getString(2) + "\n");
                }
                rs.close();
                conn.close();
            } catch (SQLException se) {
                System.out.println("No es pot connectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("No es troba la classe. Error: " + e.getMessage());
            }
        }
    };
}