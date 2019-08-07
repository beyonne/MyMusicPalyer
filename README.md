# MyMusicPalyer
my first andriod app designed by myself,a singer native music player

V1.0.3   20190807
１解决闪退问题，原因为增加新的Acitivity时未在Manifest.xml文件注册，添加注册后不再闪退；
２新增Activity最好利用向导直接new一个Activity，这样工程会同时添加Class和相应xml两个文件；
３增加音乐播放按键布局；
４初步实现音乐播放功能，点击ListVew的Item项目，弹出新界面，并把该Item的绝对地址传递给新
界面，就是单个音频的绝对地址；
５增加倍速播放功能，通过下拉列表框可选择不同播放倍率；


V1.0.2   20190806
１增加ListView单个项目点击事件，并取得该项目的绝对路径；
２单击条目弹出新界面；
３单击条目弹出新界面，在手机事物上运行时发现一点击就出现闪退，暂未找到原因；

V1.0.1   20190802
１工程编译成功；
２仅实现扫描手机本地音频文件并添加到ListView中．
３去掉标题栏；
４更换背景图片；
５显示音乐文件路径，不再显示艺术家；