package com.hencoder.hencoderpracticedraw4.practice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice14FlipboardView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();
    int degree=45;
    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 270);

    public Practice14FlipboardView(Context context) {
        super(context);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        animator.setDuration(2500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;
        //绘制上半部分
        canvas.save();
        Path path = new Path();
        if (degree <= 45) {
            path.moveTo(x + bitmapWidth/2 * (1 - degree / 45f), y);
            path.lineTo(x, y);
            path.lineTo(x, y + bitmapHeight);
            path.lineTo(x + bitmapWidth/2 * (1 - degree / 45f), y+bitmapHeight);
        } else if (degree <= 135) {
            path.moveTo(x, y + (degree - 45) / 90f * bitmapHeight);
            path.lineTo(x, y+bitmapHeight);
            path.lineTo(x + bitmapWidth, y + bitmapHeight);
            path.lineTo(x + bitmapWidth, y - (degree - 45) / 90f * bitmapHeight);
        } else if (degree <= 225) {
            path.moveTo(x + (degree - 135) / 90f * bitmapWidth, y + bitmapHeight);
            path.lineTo(x + bitmapWidth, y + bitmapHeight);
            path.lineTo(x + bitmapWidth, y);
            path.lineTo(x - (degree - 135) / 90f * bitmapWidth, y);
        } else {
            path.moveTo(x + bitmapWidth, y + bitmapHeight - (degree - 225) / 45f * bitmapHeight);
            path.lineTo(x + bitmapWidth, y);
            path.lineTo(x, y);
            path.lineTo(x, y + (degree - 225) / 45f * bitmapHeight);
        }


        path.close();
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();

        canvas.save();
        path.reset();
        //绘制下半部分
        if (degree <= 45) {
            path.moveTo(x + (degree + 45) / 45f * bitmapWidth / 2, y + bitmapHeight);
            path.lineTo(x + bitmapWidth, y + bitmapHeight);
            path.lineTo(x + bitmapWidth, y);
            path.lineTo(x - (degree + 45) / 45f * bitmapWidth / 2, y);
        } else if (degree <= 135) {
            path.moveTo(x + bitmapWidth, y + bitmapHeight - (degree - 225) / 45f * bitmapHeight);
            path.lineTo(x + bitmapWidth, y);
            path.lineTo(x, y);
            path.lineTo(x, y + (degree - 225) / 45f * bitmapHeight);
        } else if (degree <= 225) {
            path.moveTo(x, y + (degree - 45) / 90f * bitmapHeight);
            path.lineTo(x, bitmapHeight);
            path.lineTo(x + bitmapWidth, y + bitmapHeight);
            path.lineTo(x + bitmapWidth, y - (degree - 45) / 90f * bitmapHeight);
        } else {
            path.moveTo(x + centerX * (1 - degree / 45f), y);
            path.lineTo(x, y);
            path.lineTo(x, y + bitmapHeight);
            path.lineTo(x + centerX * (1 - degree / 45f), y);
        }
        path.close();
        canvas.clipPath(path);
        camera.save();
        camera.rotateY(45);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();

//        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();
    }
}
