package com.example.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

public class ImageResizer {
    private static final String TAG = "ImageResizer";

    public ImageResizer(){
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res , int resID , int reqWidth , int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res , resID , options);

        options.inSampleSize = calculateInSampleSize(options , reqWidth , reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res , resID , options);
    }
    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fd , int reqWidth , int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFileDescriptor(fd , null , options);
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd , null , options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options , int reqWidth , int reqHeight){
        if (reqWidth == 0 || reqHeight == 0){
            return 1;
        }

        final int height = options.outHeight;
        final int width = options.outWidth;
        Log.d(TAG, "calculateInSampleSize: w = " + width + " h = " + height);
        int inSampleSize = 1;
        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
