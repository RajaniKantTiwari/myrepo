package com.app.community.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.app.community.R;
import com.app.community.databinding.FragmentZoomImageSliderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.StoreImages;
import com.app.community.ui.base.BaseFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;



public class ZoomAnimationImageFragment extends BaseFragment {
    private ArrayList<StoreImages> storeImageList;
    private ImageViewPager mImageAdapter;
    private int selectedPosition = 0;
    private Animation animation;
    private FragmentZoomImageSliderBinding mBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_zoom_image_slider,container,false);
        return mBinding.getRoot();
    }

    private void setCurrentItem(int position) {
        mBinding.viewPager.setCurrentItem(position, false);
        displayMetaInfo(selectedPosition);
    }

    //	page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
        if(CommonUtils.isNotNull(storeImageList)){
            mBinding.tvCount.setText((position + 1) + " of " + storeImageList.size());
            StoreImages storeImage = storeImageList.get(position);
            //lblTitle.setText(storeImage.getCreated_at());
        }
    }

    @Override
    public void initializeData() {
        Bundle bundle=getArguments();
        if(CommonUtils.isNotNull(bundle)){
            storeImageList = bundle.getParcelableArrayList(GeneralConstant.IMAGE_LIST);
            selectedPosition = bundle.getInt(GeneralConstant.POSITION);
        }
        mImageAdapter = new ImageViewPager(getBaseActivity(),storeImageList);
        mBinding.viewPager.setAdapter(mImageAdapter);
        mBinding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        setCurrentItem(selectedPosition);
    }

    @Override
    public void setListener() {
        mBinding.imageLayout.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return ZoomAnimationImageFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomout);
        mBinding.getRoot().startAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().onBackPressed();
                //EventBus.getDefault().post(new EditPostEvent(newsFeedBean, imagePosition));
            }
        }, GeneralConstant.ANIMATION_TIME);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    /*//	adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);

            Image image = images.get(position);

           *//* Glide.with(getActivity()).load(image.getLarge())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewPreview);*//*
            final CircularAnimatedDrawable animPlaceholder = CircularAnimatedDrawable.getInstance(getActivity().
                    getResources().getColor(R.color.progresscolor), 6);
            animPlaceholder.start();

            Glide.with(getActivity())
                    .load(image.getLarge())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .placeholder(animPlaceholder)
                    .into(new BitmapImageViewTarget(imageViewPreview) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                            super.onResourceReady(resource, glideAnimation);
                            animPlaceholder.stop();
                        }

                        @Override
                        public void onLoadCleared(Drawable placeholder) {
                        }

                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                        }
                    });



            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }*/
}
