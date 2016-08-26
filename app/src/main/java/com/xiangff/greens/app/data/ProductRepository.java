package com.xiangff.greens.app.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * M层入口,为P提供统一接口
 *
 * Created by xiangff on 2016/8/19.
 */
public class ProductRepository implements  ProductDataSource{
    /**单例*/
    private static ProductRepository instance=null;

    private final ProductDataSource productsRemoteDataSource;
    private final ProductDataSource productsLocalDataSource;

    private List<ProductRepositoryObserver> observers=new ArrayList<ProductRepositoryObserver>();

    Map<String,Product> cachedProducts;
    /**表示缓存是否过期*/
    boolean cacheIsDirty=false;

    public static ProductRepository getInstance(ProductDataSource productRemoteDataSource,ProductDataSource productLocalDataSource){
        if (instance==null)
            instance=new ProductRepository(productRemoteDataSource,productLocalDataSource);
        return instance;
    }
    public static void destoryInstance(){
        instance=null;
    }
    private ProductRepository(@NonNull ProductDataSource productsRemoteDataSource,
                            @NonNull ProductDataSource productsLocalDataSource) {
        this.productsRemoteDataSource = checkNotNull(productsRemoteDataSource);
        this.productsLocalDataSource = checkNotNull(productsLocalDataSource);
    }

    @Nullable
    @Override
    public List<Product> getProducts(@NonNull LoadProductsCallback callback) {
        List<Product> products=null;
        if (!cacheIsDirty){
            //如果内存有直接从内存获取
            if (cachedProducts!=null){
                products=getCachedProducts();
                return products;
            }else {
                //从本地获取
                products=productsLocalDataSource.getProducts(callback);
            }
        }
        //内存和本地都没有，请求网络数据
        if (products==null||products.isEmpty()){
            products=productsRemoteDataSource.getProducts(callback);
            //本地保存
            saveProductsInLocalDataSource(products);
        }
        //缓存到本地
        processLoadedProducts(products);
        return getCachedProducts();
    }



    private List<Product> getCachedProducts() {
        return cachedProducts==null?null:new ArrayList<Product>(cachedProducts.values());
    }

    private void saveProductsInLocalDataSource(List<Product> products) {
        if (products!=null){
            for (int i = 0; i < products.size(); i++) {
                productsLocalDataSource.saveProduct(products.get(i));
            }
        }
    }
    private void processLoadedProducts(List<Product> products) {
        if (products==null){
            cachedProducts=null;
            cacheIsDirty=false;
            return;
        }
        if (cachedProducts==null){
            cachedProducts=new LinkedHashMap<>();
        }
        cachedProducts.clear();
        for (Product p :
                products) {
            cachedProducts.put(String.valueOf(p.getId()), p);
        }
        cacheIsDirty=false;
    }
    @Nullable
    @Override
    public Product getProduct(@NonNull String pId, @NonNull GetProductCallback callback) {
        return null;
    }

    @Override
    public void saveProduct(@NonNull Product product) {

    }

    public interface ProductRepositoryObserver{
        void onProductsChanged();
    }
}
