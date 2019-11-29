package com.example.huedroid.ui.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huedroid.R;
import com.example.huedroid.ui.MyItemRecyclerViewAdapter;
import com.example.huedroid.ui.controls.LightControl;
import com.example.huedroid.ui.controls.LightControl.LightItem;

public class LightsFragment extends Fragment {
    private OnLightsFragmentInteractionListener mListener;

    public LightsFragment() {

    }

    public static LightsFragment newInstance() {
        return new LightsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(LightControl.ITEMS, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLightsFragmentInteractionListener) {
            mListener = (OnLightsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLightsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnLightsFragmentInteractionListener {
        void onListFragmentInteraction(LightItem item);
    }
}
