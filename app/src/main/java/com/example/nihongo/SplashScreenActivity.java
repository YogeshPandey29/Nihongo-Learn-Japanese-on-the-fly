package com.example.nihongo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    // variable for setting the splash screen duration

    private static int SPLASH_SCREEN_DURATION = 3000;

    // declare variables for referencing and adding animation effects to the layout elements

    Animation topAnimationEffect, bottomAnimationEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        // add the animation effects

        topAnimationEffect = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimationEffect = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // find the imageview and textview in splash screen layout file

        ImageView splashScreenImage = findViewById(R.id.splash_screen_icon);
        TextView splashScreenText = findViewById(R.id.splash_screen_text);

        // hook the above added animation effects to the layout elements obtained above

        splashScreenImage.setAnimation(topAnimationEffect);
        splashScreenText.setAnimation(bottomAnimationEffect);

        // using the handler method for displaying the splash screen followed by the main activity screen (main menu)

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_SCREEN_DURATION);

    }
}