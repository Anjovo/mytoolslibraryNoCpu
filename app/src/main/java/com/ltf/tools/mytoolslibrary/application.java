package com.ltf.tools.mytoolslibrary;

import com.ltf.mytoolslibrary.viewbase.app.ApplicationBase;
import com.ltf.mytoolslibrary.viewbase.swipebacklayout.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/3/31.
 */

public class application extends ApplicationBase {
    @Override
    protected boolean setIsOpenCrashErrorMessage() {
        return true;
    }

    @Override
    public String setCrashErrorMessageName() {
        return "JiaBao";
    }//全局使用的文件夾名字包括臨時文件儲存的地方

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
