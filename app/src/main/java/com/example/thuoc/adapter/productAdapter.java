package com.example.thuoc.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuoc.MainActivity;
import com.example.thuoc.R;
import com.example.thuoc.activity.chitietactivity;
import com.example.thuoc.model.product;

import java.util.ArrayList;
import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductViewHolder>{
    private ArrayList<product> list;
    private Context context;



    public void setFilterList(ArrayList<product> filterList){
        this.list=filterList;
        notifyDataSetChanged();

    }

    public productAdapter(ArrayList<product> list,Context context){
        this.list = list;
        this.context=context;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thuoc,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        product product = list.get(position);
        holder.tenthuoc.setText(product.getName());
        holder.gia.setText((int)product.getPrice() + " đ");

//        holder.gia.setText(String.valueOf(product.getPrice()+ " đ"));
        holder.img.setImageResource(product.getRes());

        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(context, product.getName(), Toast.LENGTH_SHORT).show();
            Intent detailIntent = new Intent(context, chitietactivity.class);
            detailIntent.putExtra("thuoc", product);
            context.startActivity(detailIntent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView tenthuoc;
        TextView gia;
        ImageView img;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tenthuoc = (TextView) itemView.findViewById(R.id.tv_tenthuoc);
            gia = (TextView)itemView.findViewById(R.id.tv_gia);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
