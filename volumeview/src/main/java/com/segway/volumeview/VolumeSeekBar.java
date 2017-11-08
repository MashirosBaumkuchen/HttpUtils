package com.segway.volumeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * @author No.47 create at 2017/11/8.
 */
public class VolumeSeekBar extends SeekBar {
    private final String TAG = getClass().getSimpleName();

    private RectF rect;
    private float x, y;

    private int seekPointRadius = dp2px(8f);
    private int seekLineLength = dp2px(152f);
    private int seekLineWidth = dp2px(2.67f);
    private int seekLineColor = Color.argb(255, 0, 183, 254);
    private int seekLineHintColor = Color.BLACK;
    private int seekPointColor = Color.WHITE;
    private Paint paint;

    private final float SCALE = 0.6f;

    private Bitmap on;
    private Bitmap off;

    private VolumeSeekBar.CallBack callback;

    public VolumeSeekBar(Context context) {
        super(context);
        init(context);
    }

    public VolumeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        rect = new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        on = scaleBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.phone_home_icon_audioup));
        off = scaleBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.phone_home_icon_audiodown));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = (this.getRight() - this.getLeft()) / 2;
        y = (this.getBottom() - this.getTop()) / 2;
        rect.set(x - seekPointRadius, y - seekLineLength - seekPointRadius,
                x + seekPointRadius, y + seekLineLength + seekPointRadius);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(seekLineWidth);
        paint.setColor(seekLineColor);
        canvas.drawLine(x, y + seekLineLength / 2, x, y + seekLineLength / 2 - getProgress() * seekLineLength / 100f, paint);
        paint.setColor(seekLineHintColor);
        canvas.drawLine(x, y + seekLineLength / 2 - getProgress() * seekLineLength / 100f, x, y - seekLineLength / 2, paint);
        paint.setColor(seekPointColor);
        canvas.drawCircle(x, y + seekLineLength / 2 - getProgress() * seekLineLength / 100f, seekPointRadius, paint);
        canvas.drawBitmap(on, x - on.getWidth() / 2, x - on.getWidth() / 2, paint);
        canvas.drawBitmap(off, x - on.getWidth() / 2, 2 * y - x + on.getWidth() / 2 - on.getHeight(), paint);
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    private int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }

    public interface CallBack {
        int onVolumeChanged(int progress);
    }

    public void setOnVolumeChangedListener(VolumeSeekBar.CallBack callback) {
        this.callback = callback;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                updateSeekBar(event.getY());
                if(callback!=null){
                    callback.onVolumeChanged(getProgress());
                }
                break;
        }
        return true;
    }

    private void updateSeekBar(float eventY) {
        eventY = eventY + this.getTop();
        float total = seekLineLength;
        float target = y + seekLineLength / 2 + this.getTop() - eventY;
        this.setProgress((int) (100 * target / total));
    }

    public Bitmap scaleBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(SCALE, SCALE);
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbm;
    }
}
