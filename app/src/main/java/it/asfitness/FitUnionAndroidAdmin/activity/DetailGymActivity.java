package it.asfitness.FitUnionAndroidAdmin.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import it.asfitness.FitUnionAndroidAdmin.R;
import it.asfitness.FitUnionAndroidAdmin.data.Palestre;
import it.asfitness.FitUnionAndroidAdmin.fragment.MyGymRecyclerViewAdapter;
import it.asfitness.FitUnionAndroidAdmin.utils.Utils;

public class DetailGymActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView txName;
    TextView txClassificazione;
    ProgressDialog progressDialog;
    MapFragment map;
    Palestre selectedPalestra;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gym);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        txName = (TextView) findViewById(R.id.tx_name);
        txClassificazione = (TextView) findViewById(R.id.tx_level);
        map = (MapFragment) getFragmentManager().findFragmentById(R.id.map_detail);
        getSelectedItem();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //map.getMapAsync(this);
    }


    public void getSelectedItem() {
        progressDialog = ProgressDialog.show(this, "Attendere", "Download dei dati", true);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if (b != null) {
            String whereClause = "objectId = '" + b.get("objectId") + "'";
            BackendlessDataQuery dataQuery = new BackendlessDataQuery();
            dataQuery.setWhereClause(whereClause);
            Backendless.Persistence.
                    of(Palestre.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Palestre>>() {
                @Override
                public void handleResponse(BackendlessCollection<Palestre> response) {
                    List<Palestre> arrayPalestre = response.getData();
                    for (Palestre palestra : arrayPalestre) {
                        getDataForItem(palestra);
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(getApplicationContext(),"Errore nel download dei dati",Toast.LENGTH_LONG).show();
                }
            });

        }

    }


    public void getDataForItem(Palestre object) {
        double latitude = (double) object.getLatitude();
        double longitude = (double) object.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        String address = (String) object.getVia();
        txName.setText(object.getNome());
        txClassificazione.setText(Utils.getClassificazione(object.getLevel()));
        map.getMap().addMarker(new MarkerOptions()
                .position(latLng)
                .title(address)
                .snippet(""));

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, 11.0f);
        map.getMap().animateCamera(yourLocation);

        progressDialog.dismiss();
    }

    public void onMapReady(GoogleMap googleMap) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e("ADG", "LOCATION ENABLED");
            map.getMap().setMyLocationEnabled(true);
            getSelectedItem();

        } else {

        }
    }


}
