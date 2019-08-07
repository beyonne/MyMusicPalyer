package com.example.mymusic;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends Activity {

    private Button playbtn;
    private Button stopbtn;
    private Button lastmusicbtn;
    private Button nextmusicbtn;
    private TextView pathtv;
    private Spinner RatioSpinner;
    float rate = 1.0f;
    private MediaPlayer player;            //定义MediaPlayer对象
    private boolean isPause = false;       //定义是否暂停
    private String musicpath="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        musicpath = getIntent().getStringExtra("data").toString();
        pathtv = findViewById(R.id.Path);
        pathtv.setText(musicpath);
        player = MediaPlayer.create(this, Uri.parse(musicpath));

        RatioSpinner = findViewById(R.id.Radio);
        RatioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String ret = adapterView.getItemAtPosition(i).toString();//获取选项卡的值
                rate = Float.valueOf(ret);
                player.setPlaybackParams(player.getPlaybackParams().setSpeed(rate));
                Toast.makeText(MusicActivity.this,ret,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button backbtn = findViewById(R.id.Back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//关闭当前窗口
            }
        });

        playbtn = findViewById(R.id.Play);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MusicActivity.this,"播放键按下！",Toast.LENGTH_SHORT).show();
                if (player.isPlaying() && !isPause)
                {
                    player.pause();
                    isPause = true;
                    playbtn.setText("播放");
                }
                else
                {
                    player.start();
                    isPause = false;
                    playbtn.setText("暂停");
                }
            }
        });

        stopbtn = findViewById(R.id.Stop);
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MusicActivity.this,"停止键按下！",Toast.LENGTH_SHORT).show();
                player.stop();
                playbtn.setText("播放");
            }
        });

        lastmusicbtn = findViewById(R.id.LastMusic);
        lastmusicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MusicActivity.this,"上一曲键按下！",Toast.LENGTH_SHORT).show();
            }
        });

        nextmusicbtn = findViewById(R.id.NextMusic);
        nextmusicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MusicActivity.this,"下一曲键按下！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (player.isPlaying())
        {
            player.stop();
        }
        player.release();
        super.onDestroy();
    }
}
