package com.example.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button)findViewById(R.id.btnDayTime);
        final AlertDialog ad = new AlertDialog.Builder(this).create();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
                Date date = new Date();

                String message = "Thời gian hiện tại: " + dateFormat.format(date);

                ad.setMessage(message);
                ad.show();
            }
        });
    }
}
