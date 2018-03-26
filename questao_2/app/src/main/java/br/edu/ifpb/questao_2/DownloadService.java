package br.edu.ifpb.questao_2;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadService extends IntentService {

    public static final String ACTION_DOWNLOAD = "com.example.dara.myapplication.action.DOWNLOAD";

    public static final String EXTRA_URL = "com.example.dara.myapplication.extra.URL";
    public static final String EXTRA_MESSAGE = "com.example.dara.myapplication.extra.message";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                final String url = intent.getStringExtra(EXTRA_URL);
                Log.e("Service", url);
                downloadFile(url);
            }
        }
    }

    private void downloadFile(String urlStr){
        FileOutputStream fos=null;
        InputStream is=null;
        String message="Download falhou.";
        try {

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            is=connection.getInputStream();
            String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
            fos=new FileOutputStream(Environment.getExternalStorageDirectory()+"/"+fileName);
            byte[] buffer=new byte[1024];
            int count;
            while((count=is.read(buffer))>0){
                fos.write(buffer,0,count);
            }
            fos.flush();
            message="Download completo";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

            if(fos!=null){
                try {
                    fos.close();

                }catch(IOException e){}

            }
            if(is!=null){
                try {
                    is.close();

                }catch(IOException e){}

            }

            Intent backIntent=new Intent(DownloadService.ACTION_DOWNLOAD);
            backIntent.putExtra(DownloadService.EXTRA_MESSAGE, message);
            sendBroadcast(backIntent);
        }
    }
}