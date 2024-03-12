package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private EditText tvDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvDetails = findViewById(R.id.tv_details);
        Intent receivedIntent = getIntent();

        if (receivedIntent != null){
            String data = receivedIntent.getStringExtra("Number");
            tvDetails.setText(data);
        }

        tvDetails.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Number", tvDetails.getText().toString());
                    setResult(MainActivity.RESULT_OK, returnIntent);
                    finish();

                    return true;
                }
                return false;
            }
        });
    }
}