package com.santisoft.tp1laboratorio.ui.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.santisoft.tp1laboratorio.model.Usuario;
import com.santisoft.tp1laboratorio.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private Context context;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<Usuario> getUsuario() {
        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();  // Inicializa si es null
        }
        return usuarioMutableLiveData;
    }

    public void cargarDatosUsuario(boolean esNuevoRegistro) {
        if (usuarioMutableLiveData == null) {
            usuarioMutableLiveData = new MutableLiveData<>();
        }

        if (esNuevoRegistro) {
            usuarioMutableLiveData.setValue(new Usuario());  // Crear un usuario vacío
        } else {
            Usuario usuario = ApiClient.leer(context);
            if (usuario != null) {
                usuarioMutableLiveData.setValue(usuario);  // Cargar datos del usuario si existen
            }
        }
    }

    public void guardarUsuario(Usuario usuario) {
        ApiClient.guardar(context, usuario);
    }
}