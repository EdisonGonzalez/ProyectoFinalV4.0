package incorporation.app.primera.mi.proyectofinal.Principio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import incorporation.app.primera.mi.proyectofinal.Cartelera.Cartelera;
import incorporation.app.primera.mi.proyectofinal.R;

public class Splash extends AppCompatActivity {


    private static final long DELAY = 1500;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Intent intent;
    private int optLog;     //1 = Google | 2 = Facebook | 3 = Normal
    SharedPreferences datosSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//solo funciona en portrait
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//quita la barra de arriba

        setContentView(R.layout.principio_activity_splash);//se baja para que no salga error, porque sno abre primero est actividad

        datosSP = PreferenceManager.getDefaultSharedPreferences(this);
        optLog = datosSP.getInt("opcion", 0);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (optLog == 0){
                    intent = new Intent(Splash.this, Login.class);
                }else {
                    intent = new Intent(Splash.this,Cartelera.class);
                }

                startActivity(intent);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, DELAY);//que se cunpla esta tarea(task)cuando se cumpla este tiempo (SPLASH_DELAY)
    }
}
