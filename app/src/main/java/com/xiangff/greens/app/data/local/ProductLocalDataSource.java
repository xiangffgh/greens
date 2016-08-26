package com.xiangff.greens.app.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xiangff.greens.app.data.Product;
import com.xiangff.greens.app.data.ProductDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by xiangff on 2016/8/19.
 */
public class ProductLocalDataSource implements ProductDataSource{

    private static ProductLocalDataSource instance;

    private ProductLocalDataSource(@NonNull Context context){
        checkNotNull(context);

    }
    public static ProductLocalDataSource getInstance(@NonNull Context context){
        if (instance==null){
            instance=new ProductLocalDataSource(context);
        }
        return instance;
    }


    @Nullable
    @Override
    public List<Product> getProducts(@NonNull LoadProductsCallback callback) {

        List<Product> products=new ArrayList<Product>();
        //查询SQLite数据库

        return products;
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
