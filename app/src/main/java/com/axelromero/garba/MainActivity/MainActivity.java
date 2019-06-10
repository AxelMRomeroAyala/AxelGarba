package com.axelromero.garba.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.axelromero.garba.ProductDetail.ProductDetailActivity;
import com.axelromero.garba.R;
import com.axelromero.garba.models.GarbaItemListModel;

public class MainActivity extends AppCompatActivity implements MainActivityInteractor {

    public static final String EXTRA_PRODUCT_ID = "EXTRA_PRODUCT";
    public static final String EXTRA_PRODUCT_IMAGE = "EXTRA_PRODUCT_IMAGE";
    public static final String EXTRA_IMAGEN_TRANSITION_NAME= "TRANSITION_NAME";

    RecyclerView recyclerView;

    MainActivityPresenter presenter;
    ProductsListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler_view);

        presenter = new MainActivityPresenter(this, getApplication());
        presenter.getProducts(getBaseContext());
    }

    @Override
    public void getProducts(GarbaItemListModel itemListModel) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        adapter = new ProductsListAdapter(getApplicationContext(), itemListModel, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProductSelected(View sharedView, String productId, String image) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);
        intent.putExtra(EXTRA_PRODUCT_IMAGE, image);
        intent.putExtra(EXTRA_IMAGEN_TRANSITION_NAME, ViewCompat.getTransitionName(sharedView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedView,
                ViewCompat.getTransitionName(sharedView));

        startActivity(intent, options.toBundle());
    }
}
