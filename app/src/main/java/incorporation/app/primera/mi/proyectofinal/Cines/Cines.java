package incorporation.app.primera.mi.proyectofinal.Cines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import incorporation.app.primera.mi.proyectofinal.BaseDrawer.BaseDrawer;
import incorporation.app.primera.mi.proyectofinal.R;

public class Cines extends BaseDrawer
        implements NavigationView.OnNavigationItemSelectedListener {

    public FragmentManager fm;
    public FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            ft = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.Bottom_cine1:
                    ft = fm.beginTransaction();
                    Cines_UnoFragment cinesUnoFragment = new Cines_UnoFragment();
                    ft.replace(R.id.content,cinesUnoFragment).commit();
                    return true;
                case R.id.Bottom_cine2:
                    ft = fm.beginTransaction();
                    Cines_DosFragment cinesDosFragment = new Cines_DosFragment();
                    ft.replace(R.id.content,cinesDosFragment).commit();
                    return true;
                case R.id.Bottom_cine3:
                    ft = fm.beginTransaction();
                    Cines_TresFragment cinesTresFragment = new Cines_TresFragment();
                    ft.replace(R.id.content,cinesTresFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.perfil2_activity);
        FrameLayout frame_contenedorsote = (FrameLayout)findViewById(R.id.frame);
        getLayoutInflater().inflate(R.layout.cines_activity,frame_contenedorsote);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Cines_DosFragment fragmentdefault= new Cines_DosFragment();
        ft.add(R.id.content,fragmentdefault).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
