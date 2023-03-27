package com.example.newfeatures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4 , StaggeredGridLayoutManager.VERTICAL));
       // mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , R.drawable.ic_launcher_background);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch swatch = palette.getVibrantSwatch();
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(swatch.getRgb()));
            }
        });





    }

    public static final int PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public void call(){
        //检查App是否有permission.CALL_PHONE的权限
        if(ActivityCompat.checkSelfPermission(this , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //如果没有permission.CALL_PHONE的权限，就申请该权限
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.CALL_PHONE} , PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == PERMISSIONS_REQUEST_CALL_PHONE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            callPhone();
        }else{
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.CALL_PHONE)){
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("该功能需要访问电话的权限，不开启将无法正常工作")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
            }
            return ;
        }
        super.onRequestPermissionsResult(requestCode , permissions , grantResults);
    }


    public void callPhone(){

    }
}