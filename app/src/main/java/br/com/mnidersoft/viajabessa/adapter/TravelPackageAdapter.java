package br.com.mnidersoft.viajabessa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.mnidersoft.viajabessa.Constant;
import br.com.mnidersoft.viajabessa.R;
import br.com.mnidersoft.viajabessa.controller.TravelPackageActivity_;
import br.com.mnidersoft.viajabessa.model.dto.TravelPackage;
import br.com.mnidersoft.viajabessa.util.GenericUtil;

public class TravelPackageAdapter extends RecyclerView.Adapter<TravelPackageAdapter.ViewHolder> {

    private List<TravelPackage> travelPackageList;
    private Context context;

    public TravelPackageAdapter(List<TravelPackage> travelPackageList, Fragment fragment) {
        this.travelPackageList = travelPackageList;
        this.context = fragment.getActivity();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.travel_package_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        final TravelPackage travelPackage = this.travelPackageList.get(i);
        viewHolder.titleTxt.setText(travelPackage.title);
        viewHolder.priceTxt.setText(String.valueOf(travelPackage.price));

        GenericUtil.loadImageByUrl(this.context, travelPackage.photo, viewHolder.photoImg);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TravelPackageActivity_.class);
                intent.putExtra(Constant.TRAVEL_PACKAGE, travelPackageList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.travelPackageList == null ? 0 : this.travelPackageList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public TextView titleTxt;
        public TextView priceTxt;
        public ImageView photoImg;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.titleTxt = (TextView) itemView.findViewById(R.id.titleTxt);
            this.priceTxt = (TextView) itemView.findViewById(R.id.priceTxt);
            this.photoImg = (ImageView) itemView.findViewById(R.id.photoImg);
        }
    }
}
