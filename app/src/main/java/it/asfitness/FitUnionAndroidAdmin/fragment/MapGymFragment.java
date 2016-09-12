package it.asfitness.FitUnionAndroidAdmin.fragment;

        import android.Manifest;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import com.backendless.Backendless;
        import com.backendless.BackendlessCollection;
        import com.backendless.async.callback.AsyncCallback;
        import com.backendless.exceptions.BackendlessFault;
        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

        import it.asfitness.FitUnionAndroidAdmin.R;
        import it.asfitness.FitUnionAndroidAdmin.data.Palestre;
        import it.asfitness.FitUnionAndroidAdmin.models.GymModel;

        import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MapGymFragment extends Fragment implements OnMapReadyCallback {
    private OnListFragmentInteractionListener mListener;
    ProgressDialog progress;
    MapFragment map;
    List<Palestre> palestre;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
   /* public GymFragment(OnListFragmentInteractionListener listener) {
        this.mListener=listener;
    }*/


    public static MapGymFragment newInstance() {
        MapGymFragment fragment = new MapGymFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = ProgressDialog.show(getActivity(), "Attendere", "Download dei dati", true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_map_gym, container, false);
        // Set the adapter
        Backendless.Persistence.of(Palestre.class).find(new AsyncCallback<BackendlessCollection<Palestre>>() {

            @Override
            public void handleResponse(BackendlessCollection<Palestre> foundContacts) {
                palestre = foundContacts.getData();
                progress.dismiss();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                progress.dismiss();
                Toast.makeText(getActivity(), "Errore nel download dei dati. Provare più tardi. " + fault.getCode() + " msg " + fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String objectId);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.e("ADG", "LOCATION ENABLED");
            map.getMap().setMyLocationEnabled(true);
            CameraUpdate center =
                    CameraUpdateFactory.newLatLng(new LatLng(41.8919300,
                            12.5113300));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(8);

            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
            for (Palestre mPalestra: palestre) {
                Log.d("CRASTU",mPalestra.getNome());
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mPalestra.getLatitude(), mPalestra.getLongitude()))
                        .title(mPalestra.getNome())
                        .snippet(mPalestra.getEmail()));

            }

        } else {
            Log.e("CRASTUUU", "error map");
        }
    }
}