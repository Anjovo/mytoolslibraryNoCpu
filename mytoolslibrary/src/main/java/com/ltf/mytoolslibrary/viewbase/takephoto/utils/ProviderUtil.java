package com.ltf.mytoolslibrary.viewbase.takephoto.utils;

import android.content.Context;

/**
 * 作者：李堂飞 on 2017/5/9 16:12
 * 邮箱：litangfei119@qq.com
 * 用于解决provider冲突的util
 */

public class ProviderUtil {
    public static String getFileProviderName(Context context){
        return context.getPackageName()+".provider";
    }
}
