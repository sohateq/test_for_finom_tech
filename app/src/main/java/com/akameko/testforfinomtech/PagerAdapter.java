package com.akameko.testforfinomtech;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.akameko.testforfinomtech.repository.PagerItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

private List<PagerItem> pagerItems;
//private OnItemClickListener itemClickListener;
//private ViewGroup parent; //для предоставления локальных ресурсов в onBingViewHolder

//public interface OnItemClickListener {
//    void onItemClick(View view, int position);
//
//    // public void onLongItemClick(View view, int position);
//}
//    public void setOnItemClickListener(OnItemClickListener itemClickListener){
//        this .itemClickListener = itemClickListener;
//    }



public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView currencyName;
    public TextView currencyRate;
    public TextView currencyCount;
    public EditText currencyEditText;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        currencyName = itemView.findViewById(R.id.text_view_currency_name);
        currencyRate = itemView.findViewById(R.id.text_view_currency_rate);
        currencyCount = itemView.findViewById(R.id.text_view_currency_count);
        currencyEditText = itemView.findViewById(R.id.edit_text_currency);
//        mainCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (itemClickListener != null ) {
//                    itemClickListener.onItemClick(v, getAdapterPosition());
//                }
//            }
//        });
        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

    public PagerAdapter(List<PagerItem> pagerItems) {
        this.pagerItems = pagerItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.currencyName.setText(pagerItems.get(position).getCurrencyName());
        holder.currencyCount.setText("We have: " + pagerItems.get(position).getCurrencyCount());

        //holder.currencyRate.setText("1EUR = " + pagerItems.get(position).getCurrencyRate());








    }

    @Override
    public int getItemCount() {
        return pagerItems.size();
    }
}

