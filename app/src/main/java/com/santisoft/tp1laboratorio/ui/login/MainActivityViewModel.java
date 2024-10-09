package com.santisoft.tp1laboratorio.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.santisoft.tp1laboratorio.model.Usuario;
import com.santisoft.tp1laboratorio.request.ApiClient;
import com.santisoft.tp1laboratorio.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private MutableLiveData<String> mensajeMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuario() {
        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public LiveData<String> getMensaje() {
        if (mensajeMutableLiveData == null) {
            mensajeMutableLiveData = new MutableLiveData<>();
        }
        return mensajeMutableLiveData;
    }

    public void ingresar(String correo, String password) {
        Usuario usuario = ApiClient.login(context, correo, password);
        if (usuario != null) {
            usuarioMutableLiveData.setValue(usuario);
        } else {
            mensajeMutableLiveData.setValue("Correo o contraseña incorrectos");
        }
    }

    public void registrar(boolean esNuevoRegistro) {
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("esNuevo", esNuevoRegistro);
        context.startActivity(intent);
    }
}


