package com.example.farmapsbeta;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private GeoPoint userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Configurar OSMDroid
        Configuration.getInstance().load(getApplicationContext(),
                getSharedPreferences("osmdroid", MODE_PRIVATE));

        // Inicializar MapView
        mapView = findViewById(R.id.map);
        boolean b = true;
        boolean b1 = b;
        //habilitar controles tactiles
        mapView.setMultiTouchControls(b1);
        //zoom
        mapView.getController().setZoom(15.0);

        // Inicializar la ubicación del usuario
        userLocation = new GeoPoint(-33.4489, -70.6693); // Cambia esto por la ubicación actual del usuario

        // Comprobar y solicitar permisos de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        } else {
            // Si ya se tienen permisos, obtener la ubicación del usuario
            getUserLocation();
        }

        // Añadimos marcadores de las farmacia
        addMarker(new GeoPoint(-33.44834097985506, -70.63262276271865), "Farmacia 1", R.drawable.salud1);
        addMarker(new GeoPoint(-33.42726530010337, -70.62668648230218), "Farmacia 2", R.drawable.salud1);
        addMarker(new GeoPoint(-33.43023354843337, -70.63257500441654), "Farmacia 3", R.drawable.salud1);

        // Listener para el mapa
        mapView.setOnClickListener((marker, mapView1) -> {
            //trazamos la linea entre puntos
            drawLine(userLocation, marker.getRotation());
            //indicamos clicks
            return b1;
        });
    }

    private void getUserLocation() {
        // Utiliza el servicio de ubicación de Android para obtener la ubicación actual del usuario
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                userLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
                mapView.getController().setCenter(userLocation);
            } else {
                Toast.makeText(this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addMarker(GeoPoint point, String title, int resourceId) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        //titulo del marcador
        marker.setTextLabelFontSize(title);
       //icono del marcador
        marker.setIcon(getResources().getDrawable(resourceId));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(marker);
    }

    private void drawLine(GeoPoint startPoint, float endPoint) {
        Polyline line = new Polyline();
        line.addPoint(startPoint);
        line.addPoint(endPoint);
        line.setColor(0xFFFF0000);
        line.setWidth(5f);
        mapView.getOverlays().add(line);
        mapView.invalidate(); //
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permiso pa la ubicacion
                getUserLocation(); 
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
/// no se que mas hacer, es media noche y estoy perdido aaaaaaaaaaaaaaa