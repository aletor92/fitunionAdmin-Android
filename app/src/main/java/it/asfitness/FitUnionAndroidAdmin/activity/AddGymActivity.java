package it.asfitness.FitUnionAndroidAdmin.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoPoint;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.asfitness.FitUnionAndroidAdmin.data.Palestre;
import it.asfitness.FitUnionAndroidAdmin.R;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class AddGymActivity extends AppCompatActivity implements OnMapReadyCallback {

    MaterialEditText nome;
    MaterialEditText indirizzo;
    MaterialEditText telefono;
    MaterialEditText email;
    MaterialEditText password;
    MaterialEditText prezzo;
    MapFragment map;
    Marker marker;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        setContentView(R.layout.activity_add_gym);
        setupActionBar();
        nome = (MaterialEditText) findViewById(R.id.et_nome);
        indirizzo = (MaterialEditText) findViewById(R.id.et_indirizzo);
        telefono = (MaterialEditText) findViewById(R.id.et_telefono);
        prezzo = (MaterialEditText) findViewById(R.id.et_prezzo);
        email = (MaterialEditText) findViewById(R.id.et_mail);

        map = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        map.getMapAsync(this);


    }


    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_gym, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_save) {
        if (marker !=null){
             //   String idUser = Utils.getInstance(getApplicationContext()).getUser().getObjectId();
                Date date = new Date();
                BackendlessUser user = Backendless.UserService.CurrentUser();
                try {
                    addGym(nome.getText().toString(), indirizzo.getText().toString(), telefono.getText().toString(), prezzo.getText().toString(), user.getUserId(), date, true, email.getText().toString());
                }catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(this,"Errore. Controllare i campi e riprovare",Toast.LENGTH_SHORT).show();
                }
            }
        else{
              Toast.makeText(this,"Inserire la palestra sulla mappa",Toast.LENGTH_LONG).show();

        }
            //CRAAAAAAAAAAAAAAAASTUUUUUUUUUUUUUUUUU

        }

        return super.onOptionsItemSelected(item);
    }

    public void addGym(final String nome, String via, String telefono, String prezzoString, String idAdmin, Date dataIscrizione, boolean attivo, final String mail) throws Exception{
        final Palestre palestre = new Palestre();
        palestre.setNome(nome);
        palestre.setVia(via);
        palestre.setEmail(mail);
        palestre.setIdAdmin(idAdmin);
        palestre.setLatitude(marker == null ? 0.0 : marker.getPosition().latitude);
        palestre.setLongitude(marker == null ? 0.0 : marker.getPosition().longitude);
        palestre.setTelefono(Double.parseDouble(telefono));
        int prezzo = Integer.parseInt(prezzoString);
        if (prezzo < 300) {
            palestre.setLevel(1);
        } else if (prezzo >= 300 && prezzo < 600) {
            palestre.setLevel(2);
        } else {

            palestre.setLevel(3);
        }
        Backendless.Persistence.save(palestre, new AsyncCallback<Palestre>() {
            public void handleResponse(Palestre response) {
                registerGymAccount(mail, nome);
            }

            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getApplicationContext(), "Errore " + fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    public void registerGymAccount(String mail,  String nomepalestra){
        BackendlessUser user = new BackendlessUser();
        user.setProperty("email", mail);
        user.setProperty("name", nomepalestra);
        user.setProperty("userStatus", true);
        user.setProperty("user_level", 2);
        user.setPassword("fitunion");

        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser registeredUser) {
                Toast.makeText(getApplicationContext(), "Palestra registrata correttamente.", Toast.LENGTH_LONG).show();
                finish();
            }

            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getApplicationContext(), "Errore nella registrazione, riprovare. " + fault.getCode(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e("ADG", "LOCATION ENABLED");
            map.getMap().setMyLocationEnabled(true);
            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(41.8919300,
                            12.5113300));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(8);

            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
          map.getMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    map.getMap().clear();
                    marker = map.getMap().addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Posizione aggiunta")
                            .snippet("Latitudine: " + latLng.latitude + "\n" + "Longitudine: " + latLng.longitude));
                }
            });
        } else {
            Log.e("CRASTUUU" ,  "error map"  );
        }
    }


}
