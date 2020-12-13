package jp.shotakubota.horizontalscroll.MAIN;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import jp.shotakubota.horizontalscroll.MyData.MyData;
import jp.shotakubota.horizontalscroll.MyData.MyMusic;
import jp.shotakubota.horizontalscroll.MyData.MySurfaceView;
import jp.shotakubota.horizontalscroll.R;

/** Created by Shota on 2017/02/10. */

public class Main03 extends MyMusic {

    private MyData data;

    //--------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);                        // タイトルバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);     // ステータスバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 画面のタイムアウト防止

        setContentView(new MySurfaceView(this));
        //AcSensor.Inst().onCreate(this); // センサー初期化
    }

    //-------------------------------------------------------
    // タッチイベントの取得
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                data.x = (int)event.getX();
                data.y = (int)event.getY();
                data.flag = true;
                break;
            case MotionEvent.ACTION_MOVE:
                data.x = (int)event.getX();
                data.y = (int)event.getY();
                break;
            case MotionEvent.ACTION_UP:
                data.flag = false;
                break;
            default:
                break;
        } return true;
    }

    //--------------------------------------------------------
    // アクティビティが動き始める時呼ばれる
    @Override
    protected void onResume() {
        super.onResume();
        //AcSensor.Inst().onResume(); // 開始時にセンサーを動かし始める
    }

    //--------------------------------------------------------
    // アクティビティの動きが止まる時呼ばれる
    @Override
    protected void onPause() {
        super.onPause();
        //AcSensor.Inst().onPause();  // 中断時にセンサーを止める
    }

    //--------------------------------------------------------
    // OS標準の戻るボタン動作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // ボタンが押された時に呼ばれる
        if(keyCode == KeyEvent.KEYCODE_BACK){
            mpStop();
            mpChange(R.raw.op);
            mpStart();
            finish();
            return false;
        }
        // それ以外のボタンなら標準の動きをさせる
        else return super.onKeyDown(keyCode, event);
    }

}
