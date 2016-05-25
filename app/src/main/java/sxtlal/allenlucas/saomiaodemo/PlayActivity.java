package sxtlal.allenlucas.saomiaodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by AllenLucas on 2016/5/24.
 */
public class PlayActivity extends Activity {
    private VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        setContentView(R.layout.play_layout);
        initView();

    }


    private void initView() {
        vv = (VideoView) findViewById(R.id.vv);
        Intent intent = getIntent();
        String uri = intent.getStringExtra("Uri");
        vv.setVideoLayout(2,0);
        vv.setVideoPath(uri);
        vv.setMediaController(new MediaController(this));
    }
}
