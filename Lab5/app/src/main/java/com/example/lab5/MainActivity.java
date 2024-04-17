package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {
    MediaPlayer mediaPlayer;
    Button startButton, pauseButton, stopButton;
    VideoView VideoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoPlayer =  (VideoView)findViewById(R.id.videoView);
        mediaPlayer=MediaPlayer.create(this, R.raw.circus);

        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.cat);
        VideoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        VideoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(VideoPlayer);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });

        startButton = (Button) findViewById(R.id.buttonStart);
        pauseButton = (Button) findViewById(R.id.buttonPause);
        stopButton = (Button) findViewById(R.id.buttonStop);

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

    }

    private void stopPlay(){
        mediaPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onStartClick(View view)
    {
        mediaPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }
    public void onPauseClick(View view)
    {
        mediaPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void onStopClick(View view)
    {
        stopPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            stopPlay();
        }
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
    }
}