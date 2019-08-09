package com.dongxie.bezierdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dongxie.bezierdemo.R;

/**
 * @author Robot2019
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void secondBezier(View view) {
        startActivity(new Intent(MainActivity.this, SecondBezierActivity.class));
    }

    public void thirdBezier(View view) {
        startActivity(new Intent(MainActivity.this, ThirdBezierActivity.class));
    }

    public void drawPad(View view) {
        startActivity(new Intent(MainActivity.this, DrawPadActivity.class));
    }

    public void pathMorthing(View view) {
        startActivity(new Intent(MainActivity.this, PathMorphingActivity.class));
    }

    public void wave(View view) {
        startActivity(new Intent(MainActivity.this, WaveActivity.class));
    }

    public void pathBezier(View view) {
        startActivity(new Intent(MainActivity.this, PathBezierActivity.class));
    }
}
