package incorporation.app.primera.mi.proyectofinal.BaseDrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import incorporation.app.primera.mi.proyectofinal.Cartelera.Cartelera;
import incorporation.app.primera.mi.proyectofinal.Confiteria.Confiteria;
import incorporation.app.primera.mi.proyectofinal.Perfil.Perfil;
import incorporation.app.primera.mi.proyectofinal.Cines.Cines;
import incorporation.app.primera.mi.proyectofinal.Principio.Login;
import incorporation.app.primera.mi.proyectofinal.R;

public class BaseDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int optLog;     //1 = Google | 2 = Facebook | 3 = Normal
    GoogleApiClient mGoogleApiClient;
    SharedPreferences datosSP;
    SharedPreferences.Editor Mieditor;
    private ImageView Foto_perfil_Header;
    private TextView tvNombre, tvCorreo;
    private String foto_header, nombre, correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basedrawer_activity);

        datosSP = PreferenceManager.getDefaultSharedPreferences(this);
        optLog = datosSP.getInt("opcion", 0);
        nombre = datosSP.getString("nombre", "");
        correo = datosSP.getString("correo", "");
        foto_header = datosSP.getString("foto", "");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //carga dinamica de la cabecera del drawer--------------------------------------------------
        View headerView = navigationView.getHeaderView(0);
        tvNombre = (TextView) headerView.findViewById(R.id.nombre_header);
        tvCorreo = (TextView) headerView.findViewById(R.id.correo_header);
        Foto_perfil_Header = (ImageView) headerView.findViewById(R.id.Fotoheader);

        tvCorreo.setText(correo);
        if (optLog == 3){
            tvNombre.setText("Usuario Anonimo");
        }else {
            tvNombre.setText(nombre);
            loadImageFromUrl(foto_header);
        }
        //------------------------------------------------------------------------------------------
        navigationView.setNavigationItemSelectedListener(this);

        //login con Google--------------------------------------------------------------------------
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Error en login a los putazos", Toast.LENGTH_SHORT).show();
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //------------------------------------------------------------------------------------------

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        if (id == R.id.opcion_cartelera) {
            // Handle the camera action
            intent = new Intent(getApplicationContext(), Cartelera.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        } else if (id == R.id.opcion_cines) {

            intent = new Intent(getApplicationContext(), Cines.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        } else if (id == R.id.opcion_confiteria) {

            intent = new Intent(getApplicationContext(), Confiteria.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        } else if (id == R.id.opcion_perfil) {

            intent = new Intent(getApplicationContext(), Perfil.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        } else if (id == R.id.opcion_cerrar_sesion) {


            optLog = datosSP.getInt("opcion", 0);

            intent = new Intent(this, Login.class);

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
            optLog = 0;
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Mieditor = datosSP.edit();
            Mieditor.putInt("opcion", optLog);
            Mieditor.apply();
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Funcion para cargar la imagen en ImageView----------------------------------------------------
    private void loadImageFromUrl(String foto) {
        Picasso.with(this).load(foto).placeholder(R.mipmap.ic_launcher)
                .into(Foto_perfil_Header, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
    //----------------------------------------------------------------------------------------------
}
