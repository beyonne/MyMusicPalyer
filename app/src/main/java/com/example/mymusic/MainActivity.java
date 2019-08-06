package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        listview = (ListView) findViewById(R.id.list);

        musicInfos = MusicUtil.getMp3Infos(MainActivity.this);
        Listadapter = new MusicListAdapter(this, musicInfos);
        listview.setAdapter(Listadapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tr = musicInfos.get(i).getUrl();//获取绝对路径
                Toast.makeText(MainActivity.this,"Path:"+ tr,Toast.LENGTH_SHORT).show();
            }
        });
    }
//    listview.setOnItemClickLi
}
