package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private List<MusicInfo> musicInfos = null;
    private MusicListAdapter Listadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.list);

        musicInfos = MusicUtil.getMp3Infos(MainActivity.this);
        Listadapter = new MusicListAdapter(this, musicInfos);
        listview.setAdapter(Listadapter);
    }


}
