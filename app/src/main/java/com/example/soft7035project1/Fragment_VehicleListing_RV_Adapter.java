package com.example.soft7035project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment_VehicleListing_RV_Adapter extends RecyclerView.Adapter<Fragment_VehicleListing_RV_Adapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    Fragment_VehicleListing_RV_Adapter(Fragment_VehicleListing context, List<String> data) {
        this.mInflater = LayoutInflater.from(context.getActivity());
        this.mData = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row_vehicle_listing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.RvRowTextView.setText(animal);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView RvRowTextView;

        ViewHolder(View itemview) {
            super(itemview);
            RvRowTextView = itemview.findViewById(R.id.RvRowHeaderTextView);
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }


    }
    String getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
