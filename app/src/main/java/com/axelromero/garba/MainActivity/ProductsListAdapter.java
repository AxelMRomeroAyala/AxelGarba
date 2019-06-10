package com.axelromero.garba.MainActivity;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.axelromero.garba.R;
import com.axelromero.garba.Utils;
import com.axelromero.garba.models.GarbaItemListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder> {

    Context context;
    GarbaItemListModel garbaItemListModel;
    List<GarbaItemListModel.GarbaItem> items;
    MainActivityInteractor interactor;

    ProductsListAdapter(Context context, GarbaItemListModel garbaItemListModel, MainActivityInteractor interactor) {
        this.context = context;
        this.garbaItemListModel = garbaItemListModel;
        this.interactor= interactor;
        items= garbaItemListModel.items;


        //We remove products without price or name
        List<GarbaItemListModel.GarbaItem> found = new ArrayList<>();
        for(GarbaItemListModel.GarbaItem item : garbaItemListModel.items){
            if(item.price== 0 || item.description == null || item.description.isEmpty()){
                found.add(item);
            }
        }
        items.removeAll(found);
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_view, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {

        final GarbaItemListModel.GarbaItem item= items.get(position);

        Picasso.get().load("http:"+item.imageUrl).into(holder.imageView);
        holder.name.setText(item.description);
        holder.price.setText(Utils.formatAsPrice(item.price));

        if(items.get(position).discount>0){
            holder.oldPrice.setVisibility(View.VISIBLE);
            holder.discountValue.setVisibility(View.VISIBLE);
            holder.oldPrice.setText(Utils.formatAsPrice(item.listPrice));
            holder.discountValue.setText(Utils.formatAsDiscountPercentage(item.discount));

            holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.oldPrice.setVisibility(View.GONE);
            holder.discountValue.setVisibility(View.GONE);
        }

        ViewCompat.setTransitionName(holder.imageView, item.id);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.onProductSelected(holder.imageView, item.id, "http:"+item.imageUrl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, oldPrice, discountValue;
        ImageView imageView;
        CardView container;

        public ProductViewHolder(View itemView) {
            super(itemView);
            container= itemView.findViewById(R.id.product_container);
            imageView= itemView.findViewById(R.id.product_item_image);
            name= itemView.findViewById(R.id.product_name);
            price= itemView.findViewById(R.id.product_price);
            oldPrice= itemView.findViewById(R.id.product_old_price);
            discountValue= itemView.findViewById(R.id.product_discount_value);
        }
    }
}
