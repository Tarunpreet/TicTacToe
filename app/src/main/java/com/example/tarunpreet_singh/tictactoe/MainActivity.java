package com.example.tarunpreet_singh.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText player1=(EditText)findViewById(R.id.editText);
        final EditText player2=(EditText)findViewById(R.id.editText1);
        final Button start=(Button)findViewById(R.id.btn1);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play=new Intent(MainActivity.this,PlayActivity.class);
                play.putExtra("name",String.valueOf(player1.getText()));
                play.putExtra("name1",String.valueOf(player2.getText()));
                startActivity(play);
            }
        });
    }
}
