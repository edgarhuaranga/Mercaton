package com.edhuar.mercaton;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Login extends AppCompatActivity {

    private String rol;
    static public String USUARIO_LABEL="usuario";
    static public String CLAVE_LABEL="password";
    static public String ROL_LABEL="rol";
    int code_request = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_SETTINGS}, code_request);
        }

        rol = "agente";
    }

    public void onRadioButtonClicked(View view){
        Boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_agente:
                if(checked){
                    rol = "agente";
                }
                else{

                }
                break;
            case R.id.radio_supervisor:
                if(checked){
                    rol = "supervisor";
                }
                else{

                }
                break;
            default:
                break;
        }
    }

    public void accederApp(View view){
        String usuario = ((EditText) findViewById(R.id.edittext_usuario)).getText().toString();
        String pass = ((EditText) findViewById(R.id.edittext_pass)).getText().toString();
        if(Utils.camposValidos(usuario,pass, rol, this) ){ //TODO ponerle && Utils.fechaValida(this) para comparar hora y fecha
            Intent intent = new Intent(this,ListaClientes.class);
            intent.putExtra(USUARIO_LABEL,getNombreUsuario(usuario));
            intent.putExtra(CLAVE_LABEL,pass);
            intent.putExtra(ROL_LABEL,rol);
            startActivity(intent);
        }
    }

    public String getNombreUsuario(String user){
        String loginDB[] = getResources().getStringArray(R.array.loginArray);
        for(int i=0;i<loginDB.length; i+=4){
            if(loginDB[i].equalsIgnoreCase(user)){
                return loginDB[i+3];
            }
        }
        return "notfound";
    }


}

