package com.example.mymusic;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends Activity {

    private static final int INTERNAL_TIME = 1000;
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
    private SeekBar seekBar;
    private TextView currentTV;
    private TextView totalTV;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            // 展示给进度条和当前时间
            int progress = player.getCurrentPosition();
            seekBar.setProgress(progress);
            currentTV.setText(formatTime(progress));
            // 继续定时发送数据
            updateProgress();
            return true;
        }
    });

    //3、使用formatTime方法对时间格式化：
    private String formatTime(int length) {
        Date date = new Date(length);
        //时间格式化工具
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String totalTime = sdf.format(date);
        return totalTime;
    }

    private void updateProgress() {
        // 使用Handler每间隔1s发送一次空消息，通知进度条更新
        Message msg = Message.obtain();// 获取一个现成的消息
        // 使用MediaPlayer获取当前播放时间除以总时间的进度
        int progress = player.getCurrentPosition();
        msg.arg1 = progress;
        mHandler.sendMessageDelayed(msg, INTERNAL_TIME);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        musicpath = getIntent().getStringExtra("data").toString();
        pathtv = findViewById(R.id.Path);
        pathtv.setText(musicpath);
        player = MediaPlayer.create(this, Uri.parse(musicpath));

        currentTV = findViewById(R.id.CurrentTV);
        totalTV = findViewById(R.id.TotalTV);
        totalTV.setText(formatTime(player.getDuration()));

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(player.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
                if (fromTouch) {
                    player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        RatioSpinner = findViewById(R.id.Radio);
        //打开界面就会默认自动执行一次
        RatioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String ret = adapterView.getItemAtPosition(i).toString();//获取选项卡的值
                rate = Float.valueOf(ret);
                player.setPlaybackParams(player.getPlaybackParams().setSpeed(rate));
                Toast.makeText(MusicActivity.this,ret,Toast.LENGTH_SHORT).show();
                if (player.isPlaying())
                {
                    isPause = false;
                    playbtn.setText("暂停");
                }
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

                try {
                    player.prepare();
                    player.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

//    private Thread thread = new Thread(){
//        @Override
//        public void run() {
//            while(player.isPlaying())
//            {
//                try {
//                    sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
//
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if (player.isPlaying())
//            {
//                long duration = player.getDuration();
//                long pos = player.getCurrentPosition();
//                seekBar.setProgress((int)(1000*pos/duration));
//            }
//        }
//    };

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
