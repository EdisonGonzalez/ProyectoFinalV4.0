package incorporation.app.primera.mi.proyectofinal.Cartelera;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import incorporation.app.primera.mi.proyectofinal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cartelera_AccionFragment extends Fragment {


    public Cartelera_AccionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cartelera_accion_fragment, container, false);
    }

}
