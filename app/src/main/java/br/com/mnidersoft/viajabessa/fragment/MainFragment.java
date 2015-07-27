package br.com.mnidersoft.viajabessa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

import java.util.List;

import br.com.mnidersoft.viajabessa.Constant;
import br.com.mnidersoft.viajabessa.R;
import br.com.mnidersoft.viajabessa.adapter.TravelPackageAdapter;
import br.com.mnidersoft.viajabessa.model.dto.TravelPackage;

@EFragment
public class MainFragment extends Fragment {

    private RecyclerView recyclerView;

    private TravelPackageAdapter adapter;

    private List<TravelPackage> travelPackageList;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        this.travelPackageList = this.getArguments().getParcelableArrayList(Constant.TRAVEL_PACKAGE_LIST);

        this.recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.adapter = new TravelPackageAdapter(this.travelPackageList, this);
        this.recyclerView.setAdapter(this.adapter);

        return rootView;
    }
}