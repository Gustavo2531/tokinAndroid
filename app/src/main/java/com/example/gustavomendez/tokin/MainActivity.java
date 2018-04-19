package com.example.gustavomendez.tokin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends  YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private Button button;
    private Button button2;
    public static final String VIDEO_ID = "cSLAO7zxS2M";
    public static final String API_KEY = "AIzaSyCY1-S0n5IJr3wR6f-1XJWYL3BFjVidKn0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.bringToFront();
        button2 = (Button) findViewById(R.id.button2);
        button2.bringToFront();

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(API_KEY, this);

    }
    public void clickLogin(View view){
        Intent it = new Intent( MainActivity.this, LoginActivity.class);
        startActivity(it);
    }
    public void clickSignUp(View view){
        Intent it = new Intent( MainActivity.this, SignUpActivity.class);
        startActivity(it);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(null== player) return;

        // Start buffering
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failed to initialize.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
