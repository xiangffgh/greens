package com.xiangff.greens.app.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by xiangff on 2016/8/19.
 */
public interface ProductDataSource {
    interface LoadProductsCallback {
        void onProductLoaded(List<Product> products);

        void onDataNotAvailable();
    }

    interface GetProductCallback {
        void onProductLoaded(Product product);

        void onDataNotAvailable();
    }

    List<Product> getProducts(@NonNull LoadProductsCallback callback);

    Product getProduct(@NonNull String pId, @NonNull GetProductCallback callback);

    void saveProduct(@NonNull Product product);
//    void completeTask(@NonNull Product product);
//    void completeTask(@NonNull String pId);
//    void activateProduct(@NonNull Product product);
//    void activateProduct(@NonNull String pId);
//    void clearCompletedProducts();
//    void refreshProducts();
//    void deleteAllProducts();
//    void deleteProduct(@NonNull String pId);
}
