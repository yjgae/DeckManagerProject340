package edu.fr5881cw.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BeginActivity extends AppCompatActivity {
    ImageButton clk;
    Button clk2,clk3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        clk = (ImageButton) findViewById(R.id.mov_home_button);
    }

    public void moveHome(View v) {
        Intent In = new Intent(BeginActivity.this,
                HomeActivity.class);
        startActivity(In);
    }
}
