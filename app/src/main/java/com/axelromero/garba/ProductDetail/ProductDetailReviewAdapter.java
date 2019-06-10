package com.axelromero.garba.ProductDetail;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.axelromero.garba.R;
import com.axelromero.garba.Utils;
import com.axelromero.garba.models.GarbaItemReviewModel;

import java.util.List;

public class ProductDetailReviewAdapter extends RecyclerView.Adapter<ProductDetailReviewAdapter.ReviewViewHolder> {

    public List<GarbaItemReviewModel.ReviewItem.Reviews> reviews;
    Context context;

    ProductDetailReviewAdapter(Context context, List<GarbaItemReviewModel.ReviewItem.Reviews> reviews) {
        this.context= context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item_view, parent, false);
        return new ProductDetailReviewAdapter.ReviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        GarbaItemReviewModel.ReviewItem.Reviews item= reviews.get(position);

        holder.title.setText(item.title);
        holder.reviewRating.setRating(item.rating);
        holder.body.setText(item.reviewText);
        holder.date.setText(Utils.getFormatedDate(item.submissionTime));
        holder.author.setText(Html.fromHtml(context.getString(R.string.review_author)+" <b>" + item.usernickname + "</b>"));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void addItems(List<GarbaItemReviewModel.ReviewItem.Reviews> reviews) {
        this.reviews.addAll(reviews);
        notifyDataSetChanged();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        RatingBar reviewRating;
        TextView date, title, body, author;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            reviewRating = itemView.findViewById(R.id.review_item_rating);
            date = itemView.findViewById(R.id.review_item_date);
            title = itemView.findViewById(R.id.review_item_title);
            body = itemView.findViewById(R.id.review_item_body);
            author = itemView.findViewById(R.id.review_item_author);
        }
    }
}
