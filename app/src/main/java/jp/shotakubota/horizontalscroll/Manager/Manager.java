package jp.shotakubota.horizontalscroll.Manager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.LinkedList;

import jp.shotakubota.horizontalscroll.Barricade.Barricade;
import jp.shotakubota.horizontalscroll.Barricade.BarricadeCircle;
import jp.shotakubota.horizontalscroll.Barricade.BarricadeSquare;
import jp.shotakubota.horizontalscroll.Barricade.BarricadeStar;
import jp.shotakubota.horizontalscroll.Barricade.BarricadeTriangle;
import jp.shotakubota.horizontalscroll.BarricadeConf.BConf;
import jp.shotakubota.horizontalscroll.BarricadeConf.Def;
import jp.shotakubota.horizontalscroll.MyData.MyData;
import jp.shotakubota.horizontalscroll.Object.Circle;
import jp.shotakubota.horizontalscroll.Object.Player;
import jp.shotakubota.horizontalscroll.Object.Vec;

/** Created by Shota on 2017/02/11. */

public class Manager extends Task{

    private enum eStatus{  // 状態
        NORMAL,            // 普通
        GAMEOVER,          // ゲームオーバー
        GAMECLEAR          // ゲームクリア
    };

    private MyData s;
    private static final float PI = (float) Math.PI;
    private ArrayList<Barricade> _barrList = new ArrayList<Barricade>(); // Barricade list
    private LinkedList<Task> list = new LinkedList<Task>();              // Main list
    private eStatus _status = eStatus.NORMAL;                            // Status
    private Player _player;                                              // Player
    private Paint paint = new Paint();                                   // Paint

    public Manager(){
        _player = new Player(s.width*11/12,s.height*11/12);   // Player display

        _barrList.add(new BarricadeSquare(  0,  0, s.width, s.wide, null)); // 画面4隅に四角形を配置
        _barrList.add(new BarricadeSquare(  0,  0, s.wide, s.height, null));
        _barrList.add(new BarricadeSquare( s.width-s.wide,  0, s.wide, s.height, null));
        _barrList.add(new BarricadeSquare(  0, s.height-s.wide, s.width, s.wide, null));

        // タスクリストに障害物を追加
        for (Barricade bar : _barrList) list.add(bar);
        list.add(new FpsController());   // Fps display
        list.add(_player);

    }

    // 衝突判定
    private boolean Collision(){
        Vec vec = new Vec();
        final Circle cir = _player.getPt();           // プレイヤーの中心円を取得
        for(Barricade barr : _barrList){              // 障害物の数だけループ
            Def.eHitCode code = barr.isHit(cir, vec); // 接触判定
            switch(code){
                case OUT:                             // 接触したものが「アウト」なら
                    _status = eStatus.GAMEOVER;       // アウト状態に
                    return true;
                case GOAL:
                    _status = eStatus.GAMECLEAR;
                    return true;
            }
        } return false;
    }

    private void drawStatus(Canvas c){ // 状態を表示する
        switch( _status ){
            case GAMEOVER:
                paint.setTextSize(s.wide*8);
                paint.setColor(Color.WHITE);
                c.drawText("GameOver",  s.wide*4, s.wide*8, paint);
                break;
            case GAMECLEAR:
                paint.setTextSize(s.wide*8);
                paint.setColor(Color.WHITE);
                c.drawText("GameClear", s.wide*4, s.wide*8, paint);
                break;
        }
    }

    public boolean onUpdate(){
        // ゲームの状態が通常でないなら計算しない
        if( _status != eStatus.NORMAL ) return true;
        // 衝突判定　衝突したならメソッドを抜ける
        if( Collision() ) return true;
        for(int i=0;i<list.size();i++){
            if(list.get(i).onUpdate() == false){
                list.remove(i);
                i--;
            }
        }return true;
    }

    public void onDraw(Canvas c){
        for (Task task : list) task.onDraw(c); // 描画
        drawStatus(c); // 状態を表示する
    }
}
