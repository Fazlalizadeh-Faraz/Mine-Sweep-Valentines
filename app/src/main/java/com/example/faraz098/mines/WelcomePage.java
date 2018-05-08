package com.example.faraz098.mines;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

/**
 * this class is the "Splash screen" basically a nice warm welcome page
 * with animation and the logo of the game
 * */

public class WelcomePage extends AppCompatActivity {

    public int TIMER = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        setTimer();
        videoPlayer();





    }

    public void btnSkip(final Handler handler){
        Animation spin = AnimationUtils.loadAnimation(this,R.anim.spin);
        Animation frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        AnimationSet allanimations = new AnimationSet(false);
        allanimations.addAnimation(spin);
        allanimations.addAnimation(spin);

        allanimations.addAnimation(spin);
        allanimations.addAnimation(frombottom);
        Button skip = (Button)findViewById(R.id.btnRound);
        skip.setAnimation(frombottom);
        skip.setAnimation(allanimations);


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WelcomePage.this,MainActivity.class);
                intent.putExtra("testing" ,true);
                startActivity(intent);
                handler.removeMessages(0);
            }
        });

    }

    public void setTimer(){

        final Handler handler = new Handler();



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomePage.this,MainActivity.class);
                intent.putExtra("testing" ,true);
                startActivity(intent);

            }
        },TIMER);
        btnSkip(handler);

    }

    private void videoPlayer() {

        Animation fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        ImageView myimg2 = (ImageView)findViewById(R.id.imageView);
        myimg2.setAnimation(fromleft);

    }

    public static Intent makeIntentWelcomePage (Context context){
        return new Intent(context,WelcomePage.class);
    }



}
