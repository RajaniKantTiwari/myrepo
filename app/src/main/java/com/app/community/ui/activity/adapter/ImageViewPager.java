package com.app.community.ui.activity.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ImageFullscreenPreviewBinding;
import com.app.community.network.response.dashboard.home.StoreImages;
import com.app.community.ui.base.BaseActivity;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by rajnikant on 28/01/18.
 */

//	adapter
public class ImageViewPager extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private ImageFullscreenPreviewBinding mBinding;
    private ArrayList<StoreImages> storeImageList;
    private Activity activity;
    public ImageViewPager(BaseActivity activity, ArrayList<StoreImages> storeImageList) {
        layoutInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.storeImageList=storeImageList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mBinding=DataBindingUtil.inflate(layoutInflater,R.layout.image_fullscreen_preview, container, false);
       initializeData(container,position);
        return mBinding.getRoot();
    }

    private void initializeData(ViewGroup container, int position) {
        StoreImages image = storeImageList.get(position);
           /* Glide.with(getActivity()).load(image.getLarge())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewPreview);*/
        GlideUtils.loadImageWithZoom(activity,image.getPath(),mBinding.ivPreview);
        container.addView(mBinding.getRoot());
    }

    @Override
    public int getCount() {
        return CommonUtils.isNotNull(storeImageList)?storeImageList.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view ==obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
