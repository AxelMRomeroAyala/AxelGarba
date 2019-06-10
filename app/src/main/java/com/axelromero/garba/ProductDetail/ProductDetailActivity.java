package com.axelromero.garba.ProductDetail;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.axelromero.garba.ImageCarrousel.ImageCarrouselActivity;
import com.axelromero.garba.MainActivity.MainActivity;
import com.axelromero.garba.R;
import com.axelromero.garba.Utils;
import com.axelromero.garba.models.GarbaItemDetailModel;
import com.axelromero.garba.models.GarbaItemReviewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailInteractor {

    ProductDetailPresenter productDetailPresenter;

    String productId;
    String productImage;

    ConstraintLayout reviewContainer;
    TextView title, description, price, oldPrice, discount, ratingAv, ratingAVFrom, viewAllReviews;
    ImageView productImageView;
    RatingBar ratingBar;
    ProgressBar ratingPBar;
    RecyclerView reviewList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail);
        supportPostponeEnterTransition();
        setUpViews();
        productDetailPresenter = new ProductDetailPresenter(this);

        Bundle extras = getIntent().getExtras();
        productId = extras.getString(MainActivity.EXTRA_PRODUCT_ID);
        productImage = extras.getString(MainActivity.EXTRA_PRODUCT_IMAGE);

        ImageView imageView = findViewById(R.id.product_item_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(MainActivity.EXTRA_IMAGEN_TRANSITION_NAME);
            imageView.setTransitionName(imageTransitionName);
        }

        Picasso.get()
                .load(productImage)
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        supportStartPostponedEnterTransition();
                    }
                });

        ratingPBar.setVisibility(View.VISIBLE);
        productDetailPresenter.loadProductDetail(getApplicationContext(), productId);
        productDetailPresenter.loadProductReviews(getApplicationContext(), productId);
    }

    private void setUpViews() {
        productImageView = findViewById(R.id.product_item_image);
        title = findViewById(R.id.product_detail_name);
        price = findViewById(R.id.product_detail_price);
        oldPrice = findViewById(R.id.product_detail_old_price);
        discount = findViewById(R.id.product_detail_discount_value);

        reviewContainer = findViewById(R.id.product_detail_rating_info);
        ratingPBar = findViewById(R.id.rating_progress_bar);
        ratingAv = findViewById(R.id.product_detail_rating_average);
        ratingAVFrom = findViewById(R.id.product_detail_average_from_x_opinions);
        ratingBar = findViewById(R.id.product_detail_rating_bar);
        reviewList = findViewById(R.id.product_detail_review_list);
        viewAllReviews = findViewById(R.id.product_detail_view_all_reviews);
    }

    @Override
    public void onProductDetailLoaded(final GarbaItemDetailModel model) {
        title.setText(model.description);
        price.setText(Utils.formatAsPrice(model.price));
        oldPrice.setText(Utils.formatAsPrice(model.listPrice));
        discount.setText(Utils.formatAsDiscountPercentage(model.discount));

        oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ImageCarrouselActivity.getCallingIntent(getApplicationContext(), model.resources.images));
            }
        });
    }

    @Override
    public void onProductReviewLoaded(final GarbaItemReviewModel model) {

        ratingPBar.setVisibility(View.GONE);
        if (model != null && model.items != null && !model.items.isEmpty()) {
            reviewContainer.setVisibility(View.VISIBLE);
            ratingAv.setText(String.valueOf(model.items.get(0).reviewStatistics.averageOverallRating));
            ratingBar.setRating((float) model.items.get(0).reviewStatistics.averageOverallRating);
            ratingAVFrom.setText(String.format(getString(R.string.product_av_opinion_from), Utils.fromatAsStringWithPoint(model.items.get(0).totalReviewCount)));

            LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(reviewList.getContext(),
                    layoutManager.getOrientation());

            final ProductDetailReviewAdapter adapter = new ProductDetailReviewAdapter(getBaseContext(), model.items.get(0).reviews.subList(0, 3));
            reviewList.setLayoutManager(layoutManager);
            reviewList.setNestedScrollingEnabled(false);
            reviewList.addItemDecoration(dividerItemDecoration);
            reviewList.setAdapter(adapter);

            viewAllReviews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewAllReviews.setVisibility(View.GONE);
                    adapter.addItems(model.items.get(0).reviews.subList(3, model.items.get(0).reviews.size() - 1));
                }
            });
        }
    }
}

