package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
//import com.afollestad.appthemeengine.ATE;
//import com.afollestad.appthemeengine.ATEActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private List<MusicInfo> musicInfos = null;
    private MusicListAdapter Listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //获取权限
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            return;
        }

        listview = (ListView) findViewById(R.id.list);

        musicInfos = MusicUtil.getMp3Infos(MainActivity.this);
        Listadapter = new MusicListAdapter(this, musicInfos);
        listview.setAdapter(Listadapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tr = musicInfos.get(i).getUrl();//获取绝对路径
//                Toast.makeText(MainActivity.this,"Path:"+ tr,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,MusicActivity.class);
                intent.putExtra("data",tr);
                startActivity(intent);
            }
        });
    }
}
