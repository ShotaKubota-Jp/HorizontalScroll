package jp.shotakubota.horizontalscroll.MAIN;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import jp.shotakubota.horizontalscroll.MyData.MyData;
import jp.shotakubota.horizontalscroll.MyData.MyMusic;
import jp.shotakubota.horizontalscroll.R;

/** Created by Shota.K on 2017/04/21. */

public class Main01 extends MyMusic {

    private Intent intent;
    private int w,h;
    private MyData s;
    private ImageButton bt1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);                        // タイトルバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);     // ステータスバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 画面のタイムアウト防止

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        w = dm.widthPixels;   // ハードウェアの幅
        h = dm.heightPixels;  // ハードウェアの高さ
        s = new MyData(w,h);

        mpStart(); // 音楽再生

        setContentView(R.layout.xml_main01); // 画像の貼り付け
        setScreen();
    }

    public void setScreen(){
        bt1 = (ImageButton)findViewById(R.id.imgB);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Main01.this,Main02.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    //--------------------------------------------------------
    // OS標準の戻るボタン動作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // ボタンが押された時に呼ばれる
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        // それ以外のボタンなら標準の動きをさせる
        else return super.onKeyDown(keyCode, event);
    }

}


