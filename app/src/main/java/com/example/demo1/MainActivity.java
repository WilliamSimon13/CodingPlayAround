package com.example.demo1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demo1.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int Subposition;
    private static final int REQ_CODE = 1;
    private MyViewModel model;
    private ArrayList<Integer> arrayList;
    private ArrayAdapter<Integer> arrayAdapter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        binding.lvCount.setAdapter(arrayAdapter);

        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
                arrayList.add(integer);

                arrayAdapter.notifyDataSetChanged();
            }
        });


        binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.decreasenumber();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.increaseNumber();
            }

        });

        binding.lvCount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

        binding.lvCount.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Subposition = position;
                intent.putExtra("Number", arrayList.get(position).toString());
                startActivityForResult(intent, REQ_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String result = data.getStringExtra("Number");
                arrayList.set(Subposition, Integer.valueOf(result));
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }
}