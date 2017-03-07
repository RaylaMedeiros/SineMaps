package br.edu.ifpb.sinemaps.Activities;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.location.LocationListener;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import br.edu.ifpb.sinemaps.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    
    // localização
    private LocationManager locationManager;
    private Location location;
    
    // configurações
    private final int REQUEST_LOCATION = 200;
    private static final String TAG = "MapsActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Inicializar comunicação com o módulo de GPS do dispositivo móvel.
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);

        //Verificar permissões de acesso.
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Requisitar permissão de acesso ao usuário.
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, REQUEST_LOCATION);
        } else {

            // Caso a permissão já conscentida atualizar a localização.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, (LocationListener) this);
            if (locationManager != null) {
                // Localização atualizada.
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            if (location != null) {
                // Posições do GPS
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
            }
        } else {
            showGPSDisabledAlertToUser();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Novas posições do GPS
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        // Recupera Logradouro
        //getAddressFromLocation(location, getApplicationContext(), new GeoCoderHandler());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
        if (s.equals(LocationManager.GPS_PROVIDER)) {
            showGPSDisabledAlertToUser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("O GPS está desabilitado no seu dispositivo. Deseja habilitar?")
        .setCancelable(false)
        .setPositiveButton("Direcione para as configurações para habilitar o GPS.", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    /*public static void getAddressFromLocation(final Location location,
                                              final Context context,
                                              final Handler handler){

        // Conexão assíncrona para recuperar o Logradouro na internet.
        Thread thread = new Thread() {

            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;

                try {
                    List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    if (list != null && list.size() > 0) {
                        Address address = list.get(0);

                        // Resposta com a primeira linha de endereço e localidade.
                        result = address.getAddressLine(0) + ", " + address.getLocality() + ", " + address.getCountryName();
                    }

                } catch (IOException e) {
                    Log.e(TAG, "Impossível conectar ao Geocoder", e);

                } finally {
                    Message msg = Message.obtain();
                    msg.setTarget(handler);

                    if (result != null) {
                        msg.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        msg.setData(bundle);
                    } else {
                        msg.what = 0;
                    }
                    msg.sendToTarget();
                }
            }
        };
        thread.start();
    }

    private class GeoCoderHandler extends Handler{

        @Override
        public void handleMessage(Message message) {
            String result;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    result = bundle.getString("address");
                    break;
                default:
                    result = null;
            }
        }
    }*/

    /**
     * Manipulates the map once available.
     @@ -39,10 +237,36 @@ protected void onCreate(Bundle savedInstanceState) {

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
