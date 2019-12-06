package com.isabelle.registerevent;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.isabelle.registerevent.controller.CadastrarController;
import com.isabelle.registerevent.dao.Localidades_dao;
import com.isabelle.registerevent.model.Localidades;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;

    private final int TAG_CODE_PERMISSION_LOCATION=1;


    CadastrarController controller;
    LatLng latLng;
    boolean confirmed = false;
    Address address;
    Localidades_dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.sv_location);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public void vibrar(MainActivity view){
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location != null || !location.equals("")){
                    vibrar(MainActivity.this);
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Confirmar ação")
                            .setMessage("Registrar este local?")
                            .setIcon(R.drawable.calendar)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    controller = new CadastrarController();
                                    confirmed = controller.cadastrarEvento(latLng); //chama o controller
                                    if(confirmed == true){

                                        boolean success = dao.salvar(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude), "Isa");

                                        if(success){

                                            MarkerOptions marker = new MarkerOptions();


                                            marker.position(new LatLng(address.getLatitude(), address.getLongitude())).title(address.getAddressLine(0));
                                            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.calendar));


                                            map.addMarker(marker);
                                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));
                                            Toast.makeText(MainActivity.this, "Evento Marcado!!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }})
                            .setNegativeButton("Não", null).show();
                    try{
                        addressList = geocoder.getFromLocationName(location,1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    address = addressList.get(0);
                    latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        dao = new Localidades_dao(getBaseContext());
        ArrayList<Localidades> locList = new ArrayList<Localidades>();
        locList = dao.getLocalizacoes();
        Localidades loca = new Localidades();

        Log.i("LOCLIST: ", locList.toString());

        for (Iterator<Localidades> localizacao = locList.iterator(); localizacao.hasNext();) {
            loca = localizacao.next();
            Log.i("LOCAAAAA: ", loca.getLatitude());


            MarkerOptions marker = new MarkerOptions();

            marker.position(new LatLng(Double.parseDouble(loca.getLatitude()), Double.parseDouble(loca.getLongitude()))).title(loca.getEndereco());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.calendar));


            map.addMarker(marker);

        }

//        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//
//                Log.i("ponto: ", point.toString());
//
//            }
//        });
    }


}
