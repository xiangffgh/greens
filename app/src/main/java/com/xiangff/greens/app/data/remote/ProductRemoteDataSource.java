package com.xiangff.greens.app.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.data.ProductDataSource;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by xiangff on 2016/8/19.
 */
public class ProductRemoteDataSource implements ProductDataSource{

    private static ProductRemoteDataSource instance;

    private ProductRemoteDataSource(){}

    public static ProductRemoteDataSource getInstance(){
        if (instance == null){
            instance=new ProductRemoteDataSource();
        }
        return instance;
    }

    @Nullable
    @Override
    public List<Product> getProducts(@NonNull LoadProductsCallback callback) {
        return null;
    }

    @Nullable
    @Override
    public Product getProduct(@NonNull String pId, @NonNull GetProductCallback callback) {
        return null;
    }

    @Override
    public void saveProduct(@NonNull Product product) {

    }
}
