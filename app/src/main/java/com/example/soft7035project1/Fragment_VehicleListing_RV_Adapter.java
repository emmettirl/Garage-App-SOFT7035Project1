package com.example.soft7035project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_VehicleListing_RV_Adapter extends RecyclerView.Adapter<Fragment_VehicleListing_RV_Adapter.ViewHolder> {

    private List<String> modelData;
    private List<String> priceData;
    private List<String> yearData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    Fragment_VehicleListing_RV_Adapter(Fragment_VehicleListing context, List<String> modelData, List<String> priceData, List<String> yearData) {
        this.mInflater = LayoutInflater.from(context.getActivity());
        this.modelData = modelData;
        this.priceData = priceData;
        this.yearData = yearData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row_vehicle_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.RvRowHeaderTextView.setText(modelData.get(position));
        holder.RvRowYearTextView.setText(priceData.get(position));
        holder.RvRowPriceTextView.setText(yearData.get(position));
    }

    @Override
    public int getItemCount() {
        return modelData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView RvRowHeaderTextView;
        TextView RvRowYearTextView;
        TextView RvRowPriceTextView;

        ViewHolder(View itemview) {
            super(itemview);
            RvRowHeaderTextView = itemview.findViewById(R.id.RvRowHeaderTextView);
            RvRowYearTextView = itemview.findViewById(R.id.RvRowYearTextView);
            RvRowPriceTextView = itemview.findViewById(R.id.RvRowPriceTextView);
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }


    }
    String getItem(int id) {
        return modelData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
