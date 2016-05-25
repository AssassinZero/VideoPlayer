package sxtlal.allenlucas.saomiaodemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> list;
    private List<String> listuri;
    private String x;
    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_main);
//        vv = (VideoView) findViewById(R.id.vv);
        initView();
        initData();
        queryUriforVideo();
        initAdapter();
        initListener();
    }

    private void initListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                intent.putExtra("Uri",listuri.get(position));
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
    }

    private void queryUriforVideo() {
        Uri externalContentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor query = getContentResolver().query(externalContentUri,
                new String[]{MediaStore.Video.Media.TITLE,MediaStore.Video.Media.DATA}
                , null, null, null);
        Log.e("sad",query.getCount()+"");
        while (query.moveToNext()){
            Log.e("wsx",query.getString(query.getColumnIndex(MediaStore.Video.Media.DATA)));
            Log.e("asd", query.getString(query.getColumnIndex(MediaStore.Video.Media.TITLE)));
            x = query.getString(query.getColumnIndex(MediaStore.Video.Media.DATA));
            list.add(query.getString(query.getColumnIndex(MediaStore.Video.Media.TITLE)));
            listuri.add(x);
        }
        query.close();
    }
//    public void play(View v){
//        vv.setVideoPath(x);
//    }

    private void initData() {
        list = new ArrayList<>();
        listuri = new ArrayList<>();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }

}
