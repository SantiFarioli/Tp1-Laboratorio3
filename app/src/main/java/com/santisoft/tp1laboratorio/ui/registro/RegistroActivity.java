package com.santisoft.tp1laboratorio.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.santisoft.tp1laboratorio.databinding.ActivityRegistroBinding;
import com.santisoft.tp1laboratorio.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel rvm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        boolean esNuevoRegistro = getIntent().getBooleanExtra("esNuevo", false);
        rvm.cargarDatosUsuario(esNuevoRegistro);

        rvm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etDni.setText(String.valueOf(usuario.getDni()));
                binding.etEmail.setText(usuario.getMail());
                binding.etPassword.setText(usuario.getPassword());
            }
        });


        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                Long DNI = Long.valueOf(binding.etDni.getText().toString());
                String correo = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

                Usuario usuario = new Usuario(nombre, apellido, DNI, correo, password);
                rvm.guardarUsuario(usuario);
                Toast.makeText(RegistroActivity.this, "Campos guardados correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
