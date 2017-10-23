package incorporation.app.primera.mi.proyectofinal.Cartelera;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.widget.FrameLayout;

import incorporation.app.primera.mi.proyectofinal.BaseDrawer.BaseDrawer;
import incorporation.app.primera.mi.proyectofinal.R;

public class Cartelera extends BaseDrawer
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.cartelera_activity_cartelera);
        FrameLayout frame_contenedorsote = (FrameLayout) findViewById(R.id.frame);
        getLayoutInflater().inflate(R.layout.cartelera_activity_cartelera, frame_contenedorsote);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
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
                    Cartelera_AccionFragment accionFragment = new Cartelera_AccionFragment();
                    return accionFragment;
                case 1:
                    Cartelera_ComediaFragment comediaFragment = new Cartelera_ComediaFragment();
                    return comediaFragment;
                case 2:
                    Cartelera_TerrorFragment terrorFragment = new Cartelera_TerrorFragment();
                    return terrorFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Acción";
                case 1:
                    return "Comedia";
                case 2:
                    return "Terror";
            }
            return null;
        }
    }
}
