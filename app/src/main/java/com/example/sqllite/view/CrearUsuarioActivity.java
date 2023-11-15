package com.example.sqllite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite.model.ConexionHelper;
import com.example.sqllite.R;
import com.example.sqllite.controller.Utility;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText etId, etNombre, etCorreo;
    private Button btnLimpiar, btnCrear, btnCancelar;
    private ConexionHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        conexion = new ConexionHelper(this, "db_usuarios", null, 1);

        etId = findViewById(R.id.etId);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnCrear = findViewById(R.id.btnCrear);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etId.setText("");
                etNombre.setText("");
                etCorreo.setText("");
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void crearUsuario() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            int id = Integer.parseInt(etId.getText().toString());
            String nombre = etNombre.getText().toString();
            String correo = etCorreo.getText().toString();

            if (!nombre.isEmpty() && !correo.isEmpty()) {
                values.put(Utility.CAMPO_ID, id);
                values.put(Utility.CAMPO_NOMBRE, nombre);
                values.put(Utility.CAMPO_CORREO, correo);

                long newRowId = db.insert(Utility.TABLA_USUARIO, null, values);

                if (newRowId != -1) {
                    Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                    etId.setText("");
                    etNombre.setText("");
                    etCorreo.setText("");
                } else {
                    Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Los campos nombre y correo no deben estar vacíos", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El ID debe ser un número", Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
}
