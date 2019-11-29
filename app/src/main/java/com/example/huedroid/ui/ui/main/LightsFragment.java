package com.example.huedroid.ui.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huedroid.Lamp;
import com.example.huedroid.R;
import com.example.huedroid.ui.MainScreen;
import com.example.huedroid.ui.LightsRecyclerViewAdapter;

public class LightsFragment extends Fragment {
    private OnLightsFragmentInteractionListener mListener;
    private LightsRecyclerViewAdapter adapter;

    public void notifyDataSetChanged(){
        if (adapter == null) return;
        adapter.notifyDataSetChanged();
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        if (view instanceof RecyclerView) {
            MainScreen context = (MainScreen) view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new LightsRecyclerViewAdapter(context.getLamps(), mListener);
            recyclerView.setAdapter(adapter);
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
        void onListFragmentInteraction(Lamp item);
    }
}
