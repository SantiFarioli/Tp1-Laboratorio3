package com.santisoft.tp1laboratorio.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.santisoft.tp1laboratorio.databinding.ActivityMainBinding;
import com.santisoft.tp1laboratorio.model.Usuario;
import com.santisoft.tp1laboratorio.ui.registro.RegistroActivity;


public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // Observamos el cambio en el usuario
        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null) {
                    vm.registrar(false); // Navegar a RegistroActivity si el login es exitoso
                }
            }
        });


        // Observamos los mensajes de error
        vm.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mostrarMensajeError(s);

            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = binding.etMail.getText().toString();
                String password = binding.etPass.getText().toString();
                vm.ingresar(correo, password);
            }
        });


        binding.btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.registrar(true);
            }
        });
    }


    private void mostrarMensajeError (String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

