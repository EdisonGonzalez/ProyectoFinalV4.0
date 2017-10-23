package incorporation.app.primera.mi.proyectofinal.Perfil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;


import com.google.android.gms.common.api.GoogleApiClient;

import incorporation.app.primera.mi.proyectofinal.BaseDrawer.BaseDrawer;
import incorporation.app.primera.mi.proyectofinal.R;

public class Perfil extends BaseDrawer
        implements NavigationView.OnNavigationItemSelectedListener {

    private String nombre, correo, contraseña, foto;
    private int optLog;     //1 = Google | 2 = Facebook | 3 = Normal
    private TextView tvNombre, tvCorreo, tvContraseña;
    private ImageView Foto_perfil;
    GoogleApiClient mGoogleApiClient;

    SharedPreferences datosSP;
    SharedPreferences.Editor Mieditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_perfil);
        FrameLayout frame_contenedorsote = (FrameLayout) findViewById(R.id.frame);
        getLayoutInflater().inflate(R.layout.activity_perfil, frame_contenedorsote);

        tvNombre = (TextView) findViewById(R.id.Nombre);
        tvCorreo = (TextView) findViewById(R.id.Correo);
        tvContraseña = (TextView) findViewById(R.id.Contraseña);
        Foto_perfil = (ImageView) findViewById(R.id.Foto);

        datosSP = PreferenceManager.getDefaultSharedPreferences(this);
        optLog = datosSP.getInt("opcion", 0);

        if (optLog == 1) {

            datosSP = PreferenceManager.getDefaultSharedPreferences(this);
            nombre = datosSP.getString("nombre", "");
            correo = datosSP.getString("correo", "");
            foto = datosSP.getString("foto", "");

            tvNombre.setText("Nombre: " + nombre);
            tvCorreo.setText("Correo: " + correo);
            tvContraseña.setText("");
            loadImageFromUrl(foto);

        } else if (optLog == 2) {

            datosSP = PreferenceManager.getDefaultSharedPreferences(this);
            nombre = datosSP.getString("nombre", "");
            correo = datosSP.getString("correo", "");
            foto = datosSP.getString("foto", "");

            tvNombre.setText("Nombre: " + nombre);
            tvCorreo.setText("Correo: " + correo);
            tvContraseña.setText("");
            loadImageFromUrl(foto);


        } else if (optLog == 3) {

            datosSP = PreferenceManager.getDefaultSharedPreferences(this);
            correo = datosSP.getString("correo", "");
            contraseña = datosSP.getString("contraseña", "");

            tvNombre.setText("");
            tvCorreo.setText("Correo: " + correo);
            tvContraseña.setText("Contraseña: " + contraseña);

        }

    }

    //Funcion para cargar la imagen en ImageView----------------------------------------------------
    private void loadImageFromUrl(String foto) {
        Picasso.with(this).load(foto).placeholder(R.mipmap.ic_launcher).error(R.drawable.icono_cerrar_sesion)
                .into(Foto_perfil, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
    //----------------------------------------------------------------------------------------------

    /*@Override
    public boolean onCreateOptionsMenu(Menu menumain) {

        getMenuInflater().inflate(R.menu.menuperfil, menumain);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu) {
        Intent intent;
        int id = opcion_menu.getItemId();//coge el id de la opcion que selecciona el usuario

        if (id == R.id.principal) {
            if ((optLog == 1) || (optLog == 2) || (optLog == 3)) {
                intent = new Intent(Perfil.this, Main.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

        }
        if (id == R.id.cerrarperfil) {

            intent = new Intent(Perfil.this, Login.class);

            if (optLog == 1) {//Google

                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(//cerrar sesion google
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                            }
                        });

            } else if (optLog == 2) {//Facebook

                LoginManager.getInstance().logOut();// cerrar sesion en facebook

            } else {//Normalito

            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(opcion_menu);//llama al padre y le pasa nuestro item por parametro para que funcione bien
    }*/
}
