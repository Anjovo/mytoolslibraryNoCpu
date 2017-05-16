package com.ltf.mytoolslibrary.viewbase.gif;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * 作者：${李堂飞} on 2017/1/5 10:25
 * 邮箱：1195063924@qq.com
 * 注解:gif的绘制
 */

public class GifDrawer {
    private static final String TAG = "GifDrawer";
    private InputStream is;
    private ImageView imageView;
    private Movie movie;
    private Bitmap bitmap;
    private Canvas canvas;
    private Handler handler = new Handler();
    private final long delayMills = 16;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            Log.e(TAG, "run: " + Thread.currentThread().getName() );
            draw();
            handler.postDelayed(runnable, delayMills);
        }
    };

    private void draw() {
        canvas.save();
        movie.setTime((int) (System.currentTimeMillis() % movie.duration()));//这个是获取movie的某一帧，我们就不断地循环它
        movie.draw(canvas, 0, 0);
        imageView.setImageBitmap(bitmap);
        canvas.restore();
    }

    /**
     * 传递imagerview,将gif放到gif中去
     *
     * @param imageView
     */
    public void into(ImageView imageView) {
        this.imageView = imageView;
        if (is == null) {
            return;
        } else if (imageView == null) {

            throw new RuntimeException("imagetView can not be null");
        } else {

//            开始在imageview里面绘制电影
            movie = Movie.decodeStream(is);//gif小电影
            if (movie == null) {
                throw new IllegalArgumentException("Illegal gif file");

            }
            if (movie.width() <= 0 || movie.height() <= 0) {
                return;
            }
            if (bitmap != null){
                bitmap.recycle();
                bitmap = null;
            }
//            需要bitmap
            bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.RGB_565);
            canvas = new Canvas(bitmap);
//            准备把canvas的小电影显示在imageview里面
            handler.post(runnable);
        }
    }

    public void pauseMovie() {
        if (runnable != null){
            handler.removeCallbacks(runnable);
        }
    }

    public void closeMovie(){
        if (is != null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void awakeMovie() {
        handler.postDelayed(runnable, delayMills);
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Runnable getRunnable(){
        return runnable;
    }
}
