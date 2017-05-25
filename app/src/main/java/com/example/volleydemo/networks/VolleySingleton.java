package com.example.volleydemo.networks;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.MyApplication;
//import com.nostra13.universalimageloader.core.ImageLoader;
import com.android.volley.toolbox.ImageLoader;


public class VolleySingleton {

    private static VolleySingleton mInstance = null;
    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue ;

    private VolleySingleton(){

        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private LruCache<String,Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/1024/8));
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                cache.put(url,bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(){

        if(mInstance==null){

            mInstance = new VolleySingleton();
        }

        return mInstance;

    }

    public RequestQueue getRequestQueue(){

        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){

        return mImageLoader;
    }

}
