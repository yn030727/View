package com.example.handler1;

import android.os.AsyncTask;

import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;

public class DownloadFilesTask extends AsyncTask<URL, Integer , Long> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        for(int i = 0 ; i < count ; i++){
             totalSize += Downloader.downloadFile(urls[i]);
             publishProgress((int)( i / (float) count * 100));

             if(isCancelled())
                 break;
        }

        return null;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        setProgressPercent(values[0]);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        showDialg("Downloaded" + aLong + " bytes");
    }
}
