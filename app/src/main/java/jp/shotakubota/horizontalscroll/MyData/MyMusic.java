package jp.shotakubota.horizontalscroll.MyData;

/** Created by Shota on 2017/05/17. */

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import jp.shotakubota.horizontalscroll.R;

public class MyMusic extends Activity {

    private static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mp == null) {
            mp = MediaPlayer.create(this, R.raw.op);
            try{
                mp.prepare();
            } catch ( Exception e ){}
            mp.setLooping(true);
        }
    }

    public void mpStart() { if (!mp.isPlaying()) mp.start(); }
    public void mpStop() { if (mp.isPlaying()) mp.stop(); }
    public void mpChange(int intSong) {
        mp = MediaPlayer.create(this, intSong);
        mp.setLooping(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

}