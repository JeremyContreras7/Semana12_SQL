package com.example.sqllite.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.sqllite.model.ConexionHelper;
import com.example.sqllite.R;
import com.example.sqllite.model.Usuario;
import com.example.sqllite.controller.Utility;
import com.example.sqllite.controller.UsuarioAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListarUsuariosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsuarios;
    private UsuarioAdapter adapter;
    private List<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        recyclerViewUsuarios = findViewById(R.id.recyclerViewUsuarios);

        listaUsuarios = cargarUsuarios();

        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsuarioAdapter(this, listaUsuarios);
        recyclerViewUsuarios.setAdapter(adapter);
    }

    private List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        ConexionHelper conexion = new ConexionHelper(this, "db_usuarios", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.query(Utility.TABLA_USUARIO, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Utility.CAMPO_ID));
                String nombre = cursor.getString(cursor.getColumnIndex(Utility.CAMPO_NOMBRE));
                String correo = cursor.getString(cursor.getColumnIndex(Utility.CAMPO_CORREO));

                usuarios.add(new Usuario(id, nombre, correo));
                Log.d("ListarUsuarios", "Usuario cargado: " + id + ", " + nombre + ", " + correo);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("ListarUsuarios", "No se encontraron usuarios");
        }

        db.close();
        return usuarios;
    }

}
