package com.example.bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class MainActivity extends AppCompatActivity {

    //创建ImageLoader对象
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ListView lsv_image_loader;

    //网络图片的URL和drawable下的图片
    private String[] picture_uri = new String[]{
            "http://pic2.16pic.com/00/15/80/16pic_1580359_b.jpg",
            "http://pic.58pic.com/58pic/14/21/47/26t58PICywd_1024.jpg",
            "http://pic.58pic.com/58pic/14/21/42/15T58PICe3i_1024.jpg",
            "http://pic.58pic.com/58pic/14/21/44/52758PICtw2_1024.jpg",
            "http://pic.58pic.com/58pic/14/21/32/81U58PICJqm_1024.jpg",
            "http://pic.58pic.com/58pic/14/21/37/46B58PICudW_1024.jpg",
            "http://pic.58pic.com/58pic/14/21/39/72g58PICd3K_1024.jpg",
            "http://img15.3lian.com/2015/f3/08/d/02.jpg",
            "http://pic.58pic.com/58pic/14/21/47/99N58PICE7q_1024.jpg",
            "drawable://" + R.drawable.ic_launcher_background
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取ListView控件
        lsv_image_loader = (ListView) findViewById(R.id.list1);
        //创建ImageLoaderBaseAdapter
        ImageLoaderBaseAdapter adapter = new ImageLoaderBaseAdapter(this);
        //listview设置adapter
        lsv_image_loader.setAdapter(adapter);
    }


    class ImageLoaderBaseAdapter extends BaseAdapter{

        private LayoutInflater inflater;

        public ImageLoaderBaseAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return picture_uri.length;
        }

        @Override
        public Object getItem(int position) {
            return picture_uri[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.lsv_item_image_loader , null);
                holder = new ViewHolder();
                holder.iv_lin = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            //创建DisplayImageOptions对象并进行相关选项配置
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.ic_launcher_background)//设置图片下载期间显示的图片
                    .showImageForEmptyUri(R.drawable.ic_launcher_background)//设置图片Uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.drawable.ic_launcher_background)//设置图片加载或解码过程中发生错误显示的图片
                    .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                    .displayer(new RoundedBitmapDisplayer(20))// 设置成圆角图片
                    .build();
            //使用ImageLoader来加载图片
            imageLoader.displayImage(picture_uri[position] , holder.iv_lin , options);
            return convertView;
        }

        class ViewHolder{
            ImageView iv_lin;
        }
    }

    @Override
    protected void onDestroy() {
        //回收该页面缓存在内存中的图片
        imageLoader.clearMemoryCache();
        super.onDestroy();
    }
}