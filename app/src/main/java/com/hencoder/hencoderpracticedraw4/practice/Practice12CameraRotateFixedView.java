package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 100);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX1 = point1.x + bitmap.getWidth() / 2;
        int centerY1 = point1.y + bitmap.getHeight() / 2;
        int centerX2 = point2.x + bitmap.getWidth() / 2;
        int centerY2 = point2.y + bitmap.getHeight() / 2;
        Camera camera = new Camera();
        canvas.save();
        camera.save();
        camera.rotateX(30);
        canvas.translate(centerX1, centerY1);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX1, -centerY1);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();
        canvas.save();
        camera.save();
        camera.rotateY(30);
        canvas.translate(centerX2, centerY2);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX2, -centerY2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
