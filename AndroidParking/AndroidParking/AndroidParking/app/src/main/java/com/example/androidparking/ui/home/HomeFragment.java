package com.example.androidparking.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidparking.DetailActivity;
import com.example.androidparking.MapsActivity;
import com.example.androidparking.R;
import com.example.androidparking.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    View view;
    private GoogleMap mMap;
    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng TEC = new LatLng(19.029193, 73.016580);
    LatLng TEM = new LatLng(19.031729,73.016760);

    ArrayList<String> title=new ArrayList<String>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        init();
        return view;
    }
    private void init() {

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        arrayList.add(TEC);
        arrayList.add(TEM);


        title.add("Terna Engg College Id = tuf");
        title.add("Terna Medical College Id= tui");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //now we will add marker to location
        for (int i=0;i<arrayList.size();i++){
            // this loop is setting title of marker
            for (int j=0;j<title.size();j++) {
                mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(i))));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TEC,17));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }
        //now we will add on click listerner for marker

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                String markertitle = marker.getTitle();

                Intent i = new Intent(getActivity(),DetailActivity.class);
                i.putExtra("title", markertitle);
                startActivity(i);
                return false;
            }
        });
    }
}