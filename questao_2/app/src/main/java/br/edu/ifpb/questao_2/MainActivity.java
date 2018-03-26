package br.edu.ifpb.questao_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView tv;
    private BroadcastReceiver DownloadReceiver=new BroadcastReceiver(){
        public void onReceive(Context context,Intent intent){

            Bundle b=intent.getExtras();
            if(b!=null){

                tv.setText(b.getString(DownloadService.EXTRA_MESSAGE));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        tv=(TextView)findViewById(R.id.txtmessage);
        Button btDownload=(Button)findViewById(R.id.btdownload);
        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText url=(EditText)findViewById(R.id.txturl);
                String urlStr=url.getText().toString();
                if(!urlStr.equals("")){

                    Intent newIntent=new Intent(context,DownloadService.class);
                    newIntent.setAction(DownloadService.ACTION_DOWNLOAD);
                    newIntent.putExtra(DownloadService.EXTRA_URL,urlStr);

                    tv.setText("Downloading...");
                    context.startService(newIntent);
                }
            }
        });
    }

    protected void onResume(){
        super.onResume();
        registerReceiver(DownloadReceiver, new IntentFilter(DownloadService.ACTION_DOWNLOAD));
    }

    protected void onPause(){
        super.onPause();
        unregisterReceiver(DownloadReceiver);
    }
}

