package com.abhimanyusharma.resumetemplatesapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import at.markushi.ui.CircleButton;

public class ChooseTemplateActivity extends AppCompatActivity {

    CircleButton t, t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_template);

        t = (CircleButton) findViewById(R.id.template);
        t1 = (CircleButton) findViewById(R.id.template1);
        t2 = (CircleButton) findViewById(R.id.template2);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PDFActivity.class));

            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Template1Activity.class));
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Template2Activity.class));
            }
        });

    }
}
