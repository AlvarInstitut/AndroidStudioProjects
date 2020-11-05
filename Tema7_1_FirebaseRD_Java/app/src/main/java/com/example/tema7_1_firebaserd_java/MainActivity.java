package com.example.tema7_1_firebaserd_java;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView ultim = (TextView)findViewById(R.id.ultim);
        final TextView area = (TextView)findViewById(R.id.area);
        final EditText text = (EditText)findViewById(R.id.text);
        final Button boto = (Button)findViewById(R.id.boto);
        boto.setText("Enviar");

        // Referències a la Base de Dades i a les variables a1, nomXat i xat
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refA1 = database.getReference("a1");
        final DatabaseReference nomXat = database.getReference("nomXat");
        final DatabaseReference xat = database.getReference("xat");

        // Exemple de guardar dades. Primer sobre a1, i despŕes sobre la llista xat
        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refA1.setValue(text.getText().toString());
                Missatge m = new Missatge("Usuari1",text.getText().toString());
                xat.push().setValue(m);
                text.setText("");
            }
        });

        // Exemple de listener de lectura única addListenerForSingleValue()
        // Per a posar el títol. Sobre nomXat
        nomXat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                setTitle(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        // Exemple de listener de lectura contínua addValueEventListener()
        // Per a posar l'últim missatge registrat. Sobre a1
        refA1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                ultim.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        // Exemple de listener d'una llista addChildEventListener()
        // Per a posar tota la llista de missatges. Sobre xat
        xat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                area.append(dataSnapshot.child("nom").getValue(String.class) + ": " + dataSnapshot.child("contingut").getValue(String.class) + "\n");
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}


