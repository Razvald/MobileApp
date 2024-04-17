package com.example.disaign;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = findViewById(R.id.animationView);

        img.setImageResource(R.drawable.rabbit_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getDrawable();
        frameAnimation.setOneShot(false);
    }

    public void Start(View view) {
        ImageView img = findViewById(R.id.animationView);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getDrawable();

        frameAnimation.start();
    }

    public void End(View view) {
        ImageView img = findViewById(R.id.animationView);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getDrawable();

        frameAnimation.stop();
    }
}