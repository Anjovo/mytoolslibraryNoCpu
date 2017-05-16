package com.ltf.tools.mytoolslibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ltf.mytoolslibrary.viewbase.base.ActivityTitleBase;
import com.ltf.mytoolslibrary.viewbase.takephoto.TakephotoUtils;
import com.ltf.mytoolslibrary.viewbase.takephoto.bean.ImageItem;
import com.ltf.mytoolslibrary.viewbase.utils.PicassoUtil;
import com.ltf.mytoolslibrary.viewbase.utils.show.L;

import java.util.ArrayList;

public class MainActivity extends ActivityTitleBase {

    @Override
    protected void initTitle() {
//        setUpTitleBack();
//        setUpTitleCentreText("中心文字");
    }

    @Override
    public void initisBack() {
        super.initisBack();
        setIsBackUp(false);//设置当前界面不能滑动返回上个界面
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //顯示一張美女圖片
        PicassoUtil.getInstantiation().onWidgetImage(this, "http://img1.mm131.com/pic/1472/1.jpg", (ImageView) findViewById(R.id.imgs));
//        Glide.with(this)                             //配置上下文
//                .load("http://img1.mm131.com/pic/1472/1.jpg")      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.default_image)           //设置错误图片
//                .placeholder(R.mipmap.default_image)     //设置占位图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
//                .into((ImageView) findViewById(R.id.imgs));
    }

    @Override
    public boolean setIsViewStaueColor() {
        return true;
    }

    @Override
    public String setStatusBarTintResource() {
        return "-1";
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    public void onClickd(View v) {
        TakephotoUtils.getTakephotoUtils().initImagePickerMore(this);
        TakephotoUtils.getTakephotoUtils().startTakePhoto(this, new TakephotoUtils.onUserSelectPicBackLisnner() {
            @Override
            public void onUserSelectPicBack(ArrayList<ImageItem> pic) {
                if(pic !=null&&pic.size()>0)
                L.d("mainActivity",pic.get(0).path);//拍照返回
                TakephotoUtils.getTakephotoUtils().startUserSelectYuLan(MainActivity.this, 0, true, pic, new TakephotoUtils.onUserSelectPicBackLisnner() {
                    @Override
                    public void onUserSelectPicBack(ArrayList<ImageItem> pic) {
                    }
                });
            }
        });
//        TakeVideoUtils.getTakeVideoUtils().startTakeVideo(this, false, new TakeVideoUtils.onTakeVideoClickLisnner() {
//            @Override
//            public void onTakeVideoClickBack(String videoPath) {
//                L.d("mainActivity", videoPath);//录制视频返回
//            }
//        });
    }

}
