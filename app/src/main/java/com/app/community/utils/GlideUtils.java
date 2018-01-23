package com.app.community.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by arvind on 01/11/17.
 */

public class GlideUtils {
    private static String TAG=GlideUtils.class.getSimpleName();
    //Circular Image With progressbar and placeholder
    public static void loadImageProfilePic(final FragmentActivity activity, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeholder) {
        try{
            showProgressBar(progressBar);
            GlideApp.with(activity).load(imageUrl)
                    .placeholder(placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    hideProgressBar(progressBar);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    hideProgressBar(progressBar);
                    return false;
                }
            }).transforms(new CircleCrop()).into(imageView);
            // GlideApp.with(mContext).clear(imageView);
        }catch (Exception ex){
            LogUtils.LOGE(TAG,ex.toString());
        }
    }
    //load rounded image with progress bar
    public static void loadImageRoundedCorner(final FragmentActivity activity, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeholder,int roundedRadius) {
        showProgressBar(progressBar);
        GlideApp.with(activity).load(imageUrl)
                .placeholder(placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
        }).transforms(new RoundedCorners(CommonUtils.convertDpToPx(roundedRadius,activity))).into(imageView);
    }
    //load rounded image with progress bar
    public static void loadImage(final Context activity, String imageUrl, final ImageView imageView, final ProgressBar progressBar, final int placeholder) {
        showProgressBar(progressBar);
        GlideApp.with(activity).load(imageUrl)
                .placeholder(placeholder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                hideProgressBar(progressBar);
                return false;
            }
        }).into(imageView);
    }
    private static void showProgressBar(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private static void hideProgressBar(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
