package com.example.sqllite.controller;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sqllite.model.Usuario;
import com.example.sqllite.R;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> listaUsuarios;
    private Context context;

    public UsuarioAdapter(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuarioActual = listaUsuarios.get(position);

        holder.tvUsuarioId.setText(String.valueOf(usuarioActual.getId()));
        holder.tvUsuarioNombre.setText(usuarioActual.getNombre());
        holder.tvUsuarioCorreo.setText(usuarioActual.getCorreo());

        holder.btnEditar.setOnClickListener(view -> {
            // Lógica para editar el usuario
            Toast.makeText(context, "Editar: " + usuarioActual.getNombre(), Toast.LENGTH_SHORT).show();
        });

        holder.btnEliminar.setOnClickListener(view -> {
            // Lógica para eliminar el usuario
            Toast.makeText(context, "Eliminar: " + usuarioActual.getNombre(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvUsuarioId;
        protected TextView tvUsuarioNombre;
        protected TextView tvUsuarioCorreo;
        protected Button btnEditar;
        protected Button btnEliminar;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            tvUsuarioId = itemView.findViewById(R.id.tvUsuarioId);
            tvUsuarioNombre = itemView.findViewById(R.id.tvUsuarioNombre);
            tvUsuarioCorreo = itemView.findViewById(R.id.tvUsuarioCorreo);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}

