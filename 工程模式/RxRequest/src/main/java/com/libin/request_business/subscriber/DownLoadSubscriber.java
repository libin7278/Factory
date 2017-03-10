package com.libin.request_business.subscriber;

import android.os.Handler;
import android.os.Looper;

import com.libin.request_business.SolidApplication;
import com.libin.request_business.utils.FileUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;

/**
 * Created by doudou on 2017/3/9.
 */

public abstract class DownLoadSubscriber implements Subscriber<ResponseBody> {
    private static String TAG = "DownLoadSubscriber";
    private String mSaveFilePath;
    private File mFile;

    public DownLoadSubscriber(@NonNull String fileName) {
        mSaveFilePath = FileUtils.getCacheDir(SolidApplication.getContext()).getAbsolutePath();
        mFile = new File(mSaveFilePath + File.separator + fileName);
    }

    public DownLoadSubscriber(@NonNull String filePath, @NonNull String fileName) {
        mSaveFilePath = filePath;
        mFile = new File(mSaveFilePath + File.separator + fileName);
    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {
        onCompleted(mFile);
    }

    public abstract void onCompleted(File file);

    public abstract void onProgress(double progress, long downloadByte, long totalByte);


    public File getFile() {
        return mFile;
    }

    Handler handler = new Handler(Looper.getMainLooper());
    long fileSizeDownloaded = 0;
    long fileSize = 0;

    public boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                fileSize = body.contentLength();


                inputStream = body.byteStream();
                outputStream = new FileOutputStream(getFile());

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onProgress(fileSizeDownloaded * 1.0f / fileSize, fileSizeDownloaded, fileSize);

                        }
                    });
                }

                outputStream.flush();

                return true;
            } catch (final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onError(e);
                    }
                });

                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (final IOException e) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onError(e);
                }
            });
            return false;
        }
    }
}
