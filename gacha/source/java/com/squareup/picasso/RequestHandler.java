package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.NetworkInfo;
import com.squareup.picasso.Picasso.LoadedFrom;

public abstract class RequestHandler {

    public static final class Result {
        private final Bitmap bitmap;
        private final int exifOrientation;
        private final LoadedFrom loadedFrom;

        public Result(Bitmap bitmap, LoadedFrom loadedFrom) {
            this(bitmap, loadedFrom, 0);
        }

        Result(Bitmap bitmap, LoadedFrom loadedFrom, int i) {
            this.bitmap = bitmap;
            this.loadedFrom = loadedFrom;
            this.exifOrientation = i;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public LoadedFrom getLoadedFrom() {
            return this.loadedFrom;
        }

        int getExifOrientation() {
            return this.exifOrientation;
        }
    }

    public abstract boolean canHandleRequest(Request request);

    public abstract Result load(Request request);

    int getRetryCount() {
        return 0;
    }

    boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        return false;
    }

    boolean supportsReplay() {
        return false;
    }

    static Options createBitmapOptions(Request request) {
        boolean hasSize = request.hasSize();
        Object obj = request.config != null ? 1 : null;
        Options options = null;
        if (hasSize || obj != null) {
            options = new Options();
            options.inJustDecodeBounds = hasSize;
            if (obj != null) {
                options.inPreferredConfig = request.config;
            }
        }
        return options;
    }

    static boolean requiresInSampleSize(Options options) {
        return options != null && options.inJustDecodeBounds;
    }

    static void calculateInSampleSize(int i, int i2, Options options, Request request) {
        calculateInSampleSize(i, i2, options.outWidth, options.outHeight, options, request);
    }

    static void calculateInSampleSize(int i, int i2, int i3, int i4, Options options, Request request) {
        int i5 = 1;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                i5 = (int) Math.floor((double) (((float) i3) / ((float) i)));
            } else if (i == 0) {
                i5 = (int) Math.floor((double) (((float) i4) / ((float) i2)));
            } else {
                i5 = (int) Math.floor((double) (((float) i4) / ((float) i2)));
                int floor = (int) Math.floor((double) (((float) i3) / ((float) i)));
                if (request.centerInside) {
                    i5 = Math.max(i5, floor);
                } else {
                    i5 = Math.min(i5, floor);
                }
            }
        }
        options.inSampleSize = i5;
        options.inJustDecodeBounds = false;
    }
}