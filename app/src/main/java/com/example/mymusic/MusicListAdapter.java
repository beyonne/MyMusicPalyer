package com.example.mymusic;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicListAdapter extends BaseAdapter {
    private Context context;
    private List<MusicInfo> musicInfos;
    private MusicInfo musicInfo;

    /**
     * 构造函数
     * @param context	上下文
     * @param mp3Infos  集合对象
     */
    public MusicListAdapter(Context context, List<MusicInfo> mp3Infos) {
        this.context = context;
        this.musicInfos = mp3Infos;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return musicInfos.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list, null);
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.name);
            viewHolder.musicDuration = (TextView) convertView.findViewById(R.id.time);
//            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.actor);
            viewHolder.musicPath = convertView.findViewById(R.id.path);
            convertView.setTag(viewHolder);			//表示给View添加一个格外的数据，
        } else {
            viewHolder = (ViewHolder)convertView.getTag();//通过getTag的方法将数据取出来
        }
        musicInfo = musicInfos.get(position);
        viewHolder.musicTitle.setText(musicInfo.getTitle());		//显示标题
//        viewHolder.musicArtist.setText(musicInfo.getArtist());		//显示艺术家
        viewHolder.musicPath.setText(musicInfo.getUrl());
        viewHolder.musicDuration.setText(MusicUtil.formatTime(musicInfo.getDuration()));//显示时长

        return convertView;
    }

    /**
     * 定义一个内部类
     * 声明相应的控件引用
     *
     */
    public class ViewHolder {
        //所有控件对象引用
        public ImageView albumImage;	//专辑图片
        public TextView musicTitle;		//音乐标题
        public TextView musicDuration;	//音乐时长
        public TextView musicArtist;	//音乐艺术家
        public TextView musicPath;	//文件路径
    }
}
