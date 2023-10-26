package com.example.soft7035project1;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Fragment_Listing_RV_Adapter extends RecyclerView.Adapter<Fragment_Listing_RV_Adapter.ViewHolder> {

    Fragment_Listing context;

    private List<String> modelData;
    private List<String> priceData;
    private List<String> yearData;
    private String xmlTitle;
    private String xmlImagePrefix;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    Fragment_Listing_RV_Adapter(
            Fragment_Listing context,
            List<String> modelData,
            List<String> priceData,
            List<String> yearData,
            String xmlTitle,
            String xmlImagePrefix
    ) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context.getActivity());
        this.modelData = modelData;
        this.priceData = priceData;
        this.yearData = yearData;
        this.xmlTitle = xmlTitle;
        this.xmlImagePrefix = xmlImagePrefix;
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
        String price = "€ " + priceData.get(position);
        holder.RvRowYearTextView.setText(price);
        holder.RvRowPriceTextView.setText(yearData.get(position));

        try {
//            Log.d("MyDebug", "onBindViewHolder: " + xmlImagePrefix);
            InputStream ims = context.getContext().getAssets().open("images/" + xmlImagePrefix+position+".jpeg");
            Drawable d = Drawable.createFromStream(ims, null);
            holder.RvRowListingImageView.setImageDrawable(d);
            ims.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        return modelData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView RvRowHeaderTextView;
        TextView RvRowYearTextView;
        TextView RvRowPriceTextView;
        ImageView RvRowListingImageView;

        ViewHolder(View itemview) {
            super(itemview);
            RvRowHeaderTextView = itemview.findViewById(R.id.RvRowHeaderTextView);
            RvRowYearTextView = itemview.findViewById(R.id.RvRowYearTextView);
            RvRowPriceTextView = itemview.findViewById(R.id.RvRowPriceTextView);
            RvRowListingImageView = itemview.findViewById(R.id.RvRowListingImage);
            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }


    }
    String getModel(int id) {
        return modelData.get(id);
    }
    String getYear(int id) {
        return yearData.get(id);
    }
    String getPrice(int id) {
        return priceData.get(id);
    }
    String getPrefix() {
        return xmlImagePrefix;
    }



    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}