package hci201.se1171.oceanstudy;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class BackgroundMusicService extends Service{
    MediaPlayer mp_object;
    public BackgroundMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public void onCreate(){
        super.onCreate();
        mp_object = MediaPlayer.create(getApplicationContext(), R.raw.funny_background);
        mp_object.setAudioStreamType(AudioManager.STREAM_MUSIC);
        startService(new Intent(this, BackgroundMusicService.class));
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){


        mp_object.start();

        return 0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }







}
