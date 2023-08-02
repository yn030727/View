package com.example.normalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RightFragment rightFragment = new RightFragment();
        LeftFragment leftFragment = new LeftFragment();


        Uri uri = Uri.parse("app://hello");
        Intent intent = new Intent(Intent.ACTION_VIEW , uri);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment , leftFragment);
        fragmentTransaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("niubi" , "123");
        leftFragment.setArguments(bundle);
    }
}