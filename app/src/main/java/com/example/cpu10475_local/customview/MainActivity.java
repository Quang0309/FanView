package com.example.cpu10475_local.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CustomFan customFan;
    CustomNode customNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customFan = findViewById(R.id.customFan);
        customNode = findViewById(R.id.customNode);

        customNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customNode.incActiveSelection();
                customNode.redraw();
                int temp = customNode.getmActiveSelection();
               // Log.e("temp"," " + temp);
                if (temp == 0)
                    customFan.setAngle(0);
                else if(temp == 1) {
                    customFan.setSpeed(10);
                    customFan.setAngle(1);
                }
                else if(temp == 2)
                {
                    customFan.setSpeed(8);
                    customFan.setAngle(3);
                 }
                else if(temp==3)
                {
                    customFan.setSpeed(5);
                    customFan.setAngle(5);
                 }

            }
        });


    }
}
