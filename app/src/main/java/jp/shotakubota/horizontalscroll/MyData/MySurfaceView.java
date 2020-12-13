package jp.shotakubota.horizontalscroll.MyData;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import jp.shotakubota.horizontalscroll.Manager.Manager;
import jp.shotakubota.horizontalscroll.R;

/** Created by 翔太 on 2016/11/17. */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder holder = null; // サーフェイス
    private Thread thread = null;        // スレッド
    private Manager mgr = new Manager(); // マネージャー
    private Paint paint;                 // ペイント
    private MyData data;                   // ハードウェアのデータ
    private Bitmap bitmap1,bitmap2;      // 画像
    private int image1X,image2X;         // 画像の座標

    //--------------------------------------------------------
    // コンストラクタ
    public MySurfaceView(Context context){
        super(context);
        paint = new Paint();
        Resources res = this.getContext().getResources();
        bitmap1 = BitmapFactory.decodeResource(res,R.drawable.back);
        bitmap2 = BitmapFactory.decodeResource(res,R.drawable.back);
        image1X = 0;
        image2X = data.width;
        reSize();
        holder = getHolder();
        holder.addCallback(this);
    }

    //--------------------------------------------------------
    // サーフェイス生成
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        thread = new Thread(this); // 別スレッドでメインループを作る
        thread.start();            // スレッド開始
    }

    //--------------------------------------------------------
    // サーフェイス変更
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    //--------------------------------------------------------
    // サーフェイス破棄
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null; //スレッド終了
    }

    //--------------------------------------------------------
    // ループ処理
    public void run(){
        // メインループ
        while (thread != null) {
            bgiUpdate();
            mgr.onUpdate();
            draw(getHolder());
        }
    }

    //--------------------------------------------------------
    private void draw(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();            // ダブルバッファリング
        if (c == null) return;                     // キャンバスが確保できなかったら終了
        c.drawColor(Color.WHITE);                  // 背景を白に更新する
        c.drawBitmap(bitmap1,image1X,0,paint);     // 背景画像スクロール用1
        c.drawBitmap(bitmap2,image2X,0,paint);     // 背景画像スクロール用1
        mgr.onDraw(c);                             // ManagerクラスのonDrawを描画
        holder.unlockCanvasAndPost(c);             // アンロック
    }

    //--------------------------------------------------------
    // 画像の座標軸を動かす
    private void bgiUpdate(){
        image1X--;
        image2X--;
        if(image1X == -data.width) image1X = data.width;
        if(image2X == -data.width) image2X = data.width;
    }

    //--------------------------------------------------------
    // 画像のリサイズ
    private void reSize(){
        bitmap1 = Bitmap.createScaledBitmap(bitmap1,data.width,data.height,false);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2,data.width,data.height,false);
    }

}