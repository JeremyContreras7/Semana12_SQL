package com.example.sqllite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite.model.ConexionHelper;
import com.example.sqllite.R;
import com.example.sqllite.controller.Utility;

public class EditarUsuarioActivity extends AppCompatActivity {

    private EditText etIdBuscar, etNombreEditar, etCorreoEditar;
    private Button btnBuscar, btnActualizar, btnCancelar;
    private ConexionHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        conexion = new ConexionHelper(this, "db_usuarios", null, 1);

        etIdBuscar = findViewById(R.id.etIdBuscar);
        etNombreEditar = findViewById(R.id.etNombreEditar);
        etCorreoEditar = findViewById(R.id.etCorreoEditar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarUsuario();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void buscarUsuario() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] projection = {Utility.CAMPO_NOMBRE, Utility.CAMPO_CORREO};
        String selection = Utility.CAMPO_ID + " = ?";
        String[] selectionArgs = {etIdBuscar.getText().toString()};

        Cursor cursor = db.query(
                Utility.TABLA_USUARIO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            etNombreEditar.setText(cursor.getString(cursor.getColumnIndexOrThrow(Utility.CAMPO_NOMBRE)));
            etCorreoEditar.setText(cursor.getString(cursor.getColumnIndexOrThrow(Utility.CAMPO_CORREO)));

            etNombreEditar.setVisibility(View.VISIBLE);
            etCorreoEditar.setVisibility(View.VISIBLE);
            btnActualizar.setVisibility(View.VISIBLE);

            cursor.close();
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void actualizarUsuario() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_NOMBRE, etNombreEditar.getText().toString());
        values.put(Utility.CAMPO_CORREO, etCorreoEditar.getText().toString());

        String selection = Utility.CAMPO_ID + " = ?";
        String[] selectionArgs = {etIdBuscar.getText().toString()};

        int count = db.update(
                Utility.TABLA_USUARIO,
                values,
                selection,
                selectionArgs);

        if (count > 0) {
            Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
