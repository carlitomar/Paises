package com.cmmr.paises;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmmr.paises.databinding.FragmentDetallePaisBinding;
import com.cmmr.paises.placeholder.PlaceholderContent;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class DetallePaisFragment extends Fragment {

    private FragmentDetallePaisBinding binding;
    private PlaceholderContent.Pais mPais;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPais = getArguments().getParcelable("pais");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetallePaisBinding.inflate(inflater , container , false);

        ImageView iv = binding.foto;
        iv.setImageResource(getImageId(mPais.foto));
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                /*Intent intent = new Intent(getActivity(), MapsFragment.class);
                startActivity(intent);*/
                Navigation.findNavController(v).navigate((R.id.action_detallePaisFragment_to_mapsFragment));







                return false;
            }
        });
        TextView tv = binding.descripcion;
        tv.setText(mPais.detalles);


        return binding.getRoot();
    }

    private int getImageId( String imagePath) {

        String imageName = imagePath.substring(imagePath.lastIndexOf("/")+1, imagePath.lastIndexOf("."));

        return getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
    }

    }
