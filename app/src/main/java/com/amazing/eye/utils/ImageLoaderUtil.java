package com.amazing.eye.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;


import com.amazing.eye.ApplicationContext;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * 作者：xiaoguoqing
 * 创建时间：2019-07-17 下午 1:56
 * 文件描述：
 */
public class ImageLoaderUtil {

    private static class ImageUtilHolder {
        static ImageLoaderUtil instance = new ImageLoaderUtil();
    }

    public static ImageLoaderUtil getInstance() {
        return ImageUtilHolder.instance;
    }

    /**
     * 加载普通图
     */
    public void loadNormalImg(ImageView imageView, String url, Drawable placeholderDrawable,
                              Drawable errorDrawable) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderDrawable)
                        .error(errorDrawable)
                        .centerCrop())
                .into(imageView);
    }

    /**
     * 加载圆形图
     */
    public void loadCircleImg(final ImageView imageView, String url, Drawable placeholderDrawable,
                              Drawable errorDrawable) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(placeholderDrawable)
                        .error(errorDrawable)
                        .centerCrop()
                )
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                                .create(ApplicationContext.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        loadCircleTransform(imageView.getContext(), errorDrawable, imageView);
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (imageView.getDrawable() == null) {
                            loadCircleTransform(imageView.getContext(), placeholder, imageView);
                        }
                    }
                });
    }

    private void loadCircleTransform(Context context, Drawable drawable, final ImageView imageView) {
        Glide.with(context)
                .asDrawable()
                .load(drawable)
                .apply(new RequestOptions()
                        .transform(new GlideCircleTransform())
                        .centerCrop()
                .circleCrop())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });

    }

    /**
     * 加载圆角图
     */
    public void loadRoundImg(final ImageView imageView, String url, Drawable placeholderDrawable,
                             Drawable errorDrawable, final int radius) {

        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(placeholderDrawable)
                        .error(errorDrawable)
                        .transform(new GlideRoundTransform(radius)))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                                .create(ApplicationContext.getContext().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(radius);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        loadRoundTransform(imageView.getContext(), errorDrawable, imageView, radius);
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (imageView.getDrawable() == null) {
                            loadRoundTransform(imageView.getContext(), placeholder, imageView, radius);
                        }
                    }
                });
    }

    private void loadRoundTransform(Context context, Drawable placeholderId, final ImageView imageView, int radius) {
        Glide.with(context)
                .asDrawable()
                .load(placeholderId)
                .apply(new RequestOptions()
                        .transform(new GlideRoundTransform(radius))
                        .centerCrop())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });

    }
}
