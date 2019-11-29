package com.example.huedroid.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huedroid.Lamp;
import com.example.huedroid.R;
import com.example.huedroid.ui.ui.main.LightsFragment;
import com.example.huedroid.ui.ui.main.LightsFragment.OnLightsFragmentInteractionListener;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.LampViewHolder> {

    private final List<Lamp> mValues;
    private final LightsFragment.OnLightsFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Lamp> items, OnLightsFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public LampViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new LampViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LampViewHolder holder, int position) {
        holder.lamp = mValues.get(position);
        holder.tbxName.setText(String.valueOf(holder.lamp.getId()));
        holder.tbxState.setText(holder.lamp.isStateOn()?"On":"Off");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.lamp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class LampViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView tbxName;
        public final TextView tbxState;
        public Lamp lamp;

        public LampViewHolder(View view) {
            super(view);
            this.view = view;
            tbxName = view.findViewById(R.id.tbxLightName);
            tbxState = view.findViewById(R.id.tbxLightState);
        }
    }
}
