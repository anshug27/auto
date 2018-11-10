package com.example.nishant.automaticcarparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b1;
    TextView t1;
    boolean clicked=false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        t1=findViewById(R.id.textView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method="register";
                String id="12",name="Nishant2",mail="akhgdjs",contact="6354351";
                BackgroundTask bt=new BackgroundTask(MainActivity.this);
                bt.execute(method,id,name,mail,contact);
            }
        });
    }
}
