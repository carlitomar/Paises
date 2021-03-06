package com.cmmr.paises;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cmmr.paises.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class PaisesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PaisesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PaisesFragment newInstance(int columnCount) {
        PaisesFragment fragment = new PaisesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paises_list, container, false);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            String tipoVisualizacion = prefs.getString("tipo visualizacion", ""); /* asignar a esta variable el valor de la propiedad tipo_visualizacion */

            if (tipoVisualizacion.equals("listado")) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            }
            boolean useDivider=prefs.getBoolean("linea", Boolean.parseBoolean("")); /* asignar a esta variable el valor almacenado en la propiedad l??nea */
            if(useDivider) {
                // Dibuja una l??nea divisoria entre elementos
                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        LinearLayout.VERTICAL);
                recyclerView.addItemDecoration(verticalDecoration);
            }
            PlaceholderContent placeholderContent = new PlaceholderContent(getResources(),
                    getContext().getPackageName());
            recyclerView.setAdapter(new PaisRecyclerViewAdapter(PlaceholderContent.PAISES));
        }

        return view;
    }
}