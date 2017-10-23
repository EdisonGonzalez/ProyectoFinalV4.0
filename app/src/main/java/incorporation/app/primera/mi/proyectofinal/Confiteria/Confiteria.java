package incorporation.app.primera.mi.proyectofinal.Confiteria;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.TextView;

import incorporation.app.primera.mi.proyectofinal.BaseDrawer.BaseDrawer;
import incorporation.app.primera.mi.proyectofinal.Cartelera.Cartelera_AccionFragment;
import incorporation.app.primera.mi.proyectofinal.Cartelera.Cartelera_ComediaFragment;
import incorporation.app.primera.mi.proyectofinal.Cartelera.Cartelera_TerrorFragment;
import incorporation.app.primera.mi.proyectofinal.R;

public class Confiteria extends BaseDrawer
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.confiteria_activity_confiteria);
        FrameLayout frame_contenedorsote = (FrameLayout)findViewById(R.id.frame);
        getLayoutInflater().inflate(R.layout.confiteria_activity_confiteria,frame_contenedorsote);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Confiteria_UnoFragment confiteria_unoFragment = new Confiteria_UnoFragment();
                    return confiteria_unoFragment;
                case 1:
                    Confiteria_DosFragment confiteria_dosFragment = new Confiteria_DosFragment();
                    return confiteria_dosFragment;
            }

            return null;
    }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Combos para uno";
                case 1:
                    return "Combos para dos";
            }
            return null;
        }
    }
}
