package com.example.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this , R.layout.fruit_item , fruitList);
        ListView listView = findViewById(R.id.listView1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                System.out.println(fruit.getName());
            }
        });

    }

    private void initFruits(){
        for(int i = 0 ; i < 2 ; i++){
            Fruit apple = new Fruit("Apple" , R.drawable.draw_shape1);
            Fruit Banana = new Fruit("Banana" , R.drawable.draw_shape1);
            Fruit Pear = new Fruit("Pear" , R.drawable.draw_shape1);
            Fruit awfawf = new Fruit("awfawf" , R.drawable.draw_shape1);
            Fruit wfewaf = new Fruit("rgw" , R.drawable.draw_shape1);
            Fruit wfaf = new Fruit("htre" , R.drawable.draw_shape1);
            Fruit wgweg = new Fruit("asdg" , R.drawable.draw_shape1);
            Fruit hqhq = new Fruit("hqh" , R.drawable.draw_shape1);
            Fruit qh = new Fruit("qrgeh" , R.drawable.draw_shape1);
            Fruit hwh = new Fruit("erqherh" , R.drawable.draw_shape1);
            fruitList.add(apple);
            fruitList.add(Banana);
            fruitList.add(apple);
            fruitList.add(Banana);
            fruitList.add(apple);
            fruitList.add(Banana);
            fruitList.add(apple);
            fruitList.add(Banana);
            fruitList.add(apple);
            fruitList.add(Banana);
        }
    }
}