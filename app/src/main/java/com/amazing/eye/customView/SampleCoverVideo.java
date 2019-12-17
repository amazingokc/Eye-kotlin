package com.amazing.eye.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazing.eye.utils.Utils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class SampleCoverVideo extends StandardGSYVideoPlayer {

    public SampleCoverVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SampleCoverVideo(Context context) {
        super(context);
    }

    public SampleCoverVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        //进度条优化
        mProgressBar.setPadding(Utils.dip2px(context, 10), 0, Utils.dip2px(context, 10), 0);

        //旋转框优化
        ViewGroup.LayoutParams layoutParams = mFullscreenButton.getLayoutParams();
        layoutParams.width = Utils.dip2px(context, 40);
        layoutParams.height = Utils.dip2px(context, 40);
        mFullscreenButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mFullscreenButton.setPadding(Utils.dip2px(context, 10), Utils.dip2px(context, 10),
                Utils.dip2px(context, 10), Utils.dip2px(context, 10));
    }

    public long getCurrentPosition() {
        return getGSYVideoManager().getCurrentPosition();
    }

}
