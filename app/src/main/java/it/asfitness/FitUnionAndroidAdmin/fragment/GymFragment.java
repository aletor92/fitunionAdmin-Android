package it.asfitness.FitUnionAndroidAdmin.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

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
@SuppressLint("ValidFragment")
public class GymFragment extends Fragment {
    MyGymRecyclerViewAdapter adapter;
    private OnListFragmentInteractionListener mListener;
    ProgressDialog progress;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GymFragment(OnListFragmentInteractionListener listener) {
        this.mListener=listener;

    }
    // TODO: Customize parameter initialization

    public static GymFragment newInstance(OnListFragmentInteractionListener listener) {
        GymFragment fragment = new GymFragment(listener);

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
        final View view = inflater.inflate(R.layout.fragment_gym_list, container, false);
        // Set the adapter
        Backendless.Persistence.of( Palestre.class).find(new AsyncCallback<BackendlessCollection<Palestre>>() {

            @Override
            public void handleResponse(BackendlessCollection<Palestre> foundContacts) {
                adapter = new MyGymRecyclerViewAdapter(foundContacts.getData(), mListener);
                if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView = (RecyclerView) view;
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);
                }
                progress.dismiss();
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                progress.dismiss();
                Toast.makeText(getActivity(), "Errore nel download dei dati. Provare più tardi. " +fault.getCode() + " msg " + fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


    private void setCustomAdapter(List<GymModel> list) {
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

}
