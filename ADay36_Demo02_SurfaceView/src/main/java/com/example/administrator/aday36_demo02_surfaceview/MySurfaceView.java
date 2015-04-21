package com.example.administrator.aday36_demo02_surfaceview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2015/4/14.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    boolean isRunning=false;
    private Paint paint;
    int x,y;
    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        holder =getHolder();
        holder.addCallback(this);
        paint=new Paint();
        paint.setColor(Color.YELLOW);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x= (int) event.getX();
        y= (int) event.getY();

        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning=true;
        new MyThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning=false;
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (isRunning){
                //绘制
                Canvas canvas=holder.lockCanvas();
                canvas.drawColor(Color.BLUE);
                canvas.drawCircle(x,y,100, paint);
                holder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
