package it.asfitness.FitUnionAndroidAdmin.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import it.asfitness.FitUnionAndroidAdmin.data.Palestre;
import it.asfitness.FitUnionAndroidAdmin.R;

import java.util.List;

public class MyGymRecyclerViewAdapter extends RecyclerView.Adapter<MyGymRecyclerViewAdapter.ViewHolder> {

    private final List<Palestre> mValues;
    private final GymFragment.OnListFragmentInteractionListener mListener;

    public MyGymRecyclerViewAdapter(List<Palestre> items, GymFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_gym, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mName.setText((String) mValues.get(position).getNome());
        holder.mAddress.setText((String) mValues.get(position).getVia());
        //if ((Boolean)mValues.get(position).get("attivo")==true){
        //}else{
          //  holder.mImageState.setImageResource(R.drawable.ic_active_false);
        //}
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(mValues.get(position).getObjectId());
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mAddress;
        public final ImageView mImageState;

        public Palestre mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.gym_name);
            mAddress = (TextView) view.findViewById(R.id.gym_address);
            mImageState = (ImageView) view.findViewById(R.id.ic_state);

        }

    }
}
