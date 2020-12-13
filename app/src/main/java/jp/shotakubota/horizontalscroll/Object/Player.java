package jp.shotakubota.horizontalscroll.Object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import jp.shotakubota.horizontalscroll.MyData.MyData;
import jp.shotakubota.horizontalscroll.Manager.Task;

/** Created by 翔太 on 2017/02/05. */

public class Player extends Task {

    private final static float MAX_SPEED = 20; // 移動する最大スピード
    private Circle _cir = null;
    private Paint _paint = new Paint();
    private Vec _vec = new Vec();
    private Vec _sensorVec = new Vec();        // センサーのベクトル
    private MyData data;

    //---------------------------------------------------------------------------
    public Player(){
        _cir = new Circle(240,0,data.wide*2);
        _paint.setColor(Color.BLUE);
        _paint.setAntiAlias(true);
    }

    //---------------------------------------------------------------------------
    public Player(int x,int y){
        _cir = new Circle(x,y,data.wide);
        _paint.setColor(Color.BLUE);
        _paint.setAntiAlias(true);
    }

    //---------------------------------------------------------------------------
    // ベクトルをセットする
    private void setVec(){
        float x = -AcSensor.Inst().getX()*2;    // 加速度センサーの情報を取得
        float y =  AcSensor.Inst().getY()*2;
        _sensorVec._x = x < 0 ? -x*x : x*x;     // 2乗して変化を大袈裟にする
        _sensorVec._y = y < 0 ? -y*y : y*y;     // 2乗すると+になるので、負ならマイナスを付ける
        _sensorVec.setLengthCap(MAX_SPEED);     // ベクトルの大きさが最大スピード以上にならないようにする
        _vec.blend( _sensorVec, 0.01f );        // センサーのベクトル方向に実際の移動ベクトルを5%近づける
    }

    //---------------------------------------------------------------------------
    // 移動ベクトルの向いている方に動かす
    private void Move(){
        _cir._x += _vec._x;     // 移動ベクトル_vecが指す方向に移動させる
        _cir._y += _vec._y;
    }

    //---------------------------------------------------------------------------
    // タッチした座標をPlayerの座標に入れ込む
    private void Touch(){
        if(data.flag) {
            _cir._x = data.x;
            _cir._y = data.y;
        }
    }

    //---------------------------------------------------------------------------
    // 自機中心円を取得する
    public final Circle getPt(){
        return _cir;
    }

    //---------------------------------------------------------------------------
    @Override
    public boolean onUpdate(){
        //setVec();       // 移動ベクトルをセットする
        //Move();         // 移動ベクトルが向いている方に動かす
        Touch();
        return true;
    }

    //---------------------------------------------------------------------------
    @Override
    public void onDraw( Canvas c ){
        c.drawCircle(_cir._x, _cir._y, _cir._r, _paint);
    }

}
