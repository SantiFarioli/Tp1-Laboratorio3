package com.santisoft.tp1laboratorio.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.santisoft.tp1laboratorio.model.Usuario;

public class ApiClient {
    private static SharedPreferences sp;

    // Método para obtener la instancia de SharedPreferences
    private static SharedPreferences conectar(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
        }
        return sp;
    }

    // Método para guardar los datos del usuario en SharedPreferences
    public static void guardar(Context context, Usuario usuario) {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nombre", usuario.getNombre());
        editor.putString("apellido", usuario.getApellido());
        editor.putLong("dni", usuario.getDni());
        editor.putString("mail", usuario.getMail());
        editor.putString("password", usuario.getPassword());
        editor.commit(); // Importante para asegurar que los datos se guarden
    }


    // Método para leer los datos del usuario de SharedPreferences
    public static Usuario leer(Context context) {
        SharedPreferences sp = conectar(context);
        String nombre = sp.getString("nombre", "-1");
        String apellido = sp.getString("apellido", "-1");
        long dni = sp.getLong("dni", -1);
        String mail = sp.getString("mail", "-1");
        String password = sp.getString("password", "-1");

        if (dni == -1 || apellido.equals("-1") || nombre.equals("-1") || mail.equals("-1") || password.equals("-1")) {
            return null; // No hay datos guardados
        }
        return new Usuario(nombre, apellido, dni, mail, password);
    }

    // Método para realizar el login verificando mail y password
    public static Usuario login(Context context, String mail, String password) {
        Usuario usuario = leer(context); // Leer los datos guardados

        if (usuario != null && usuario.getMail().equals(mail) && usuario.getPassword().equals(password)) {
            return usuario; // Login exitoso
        }
        return null; // Credenciales incorrectas
    }
}

