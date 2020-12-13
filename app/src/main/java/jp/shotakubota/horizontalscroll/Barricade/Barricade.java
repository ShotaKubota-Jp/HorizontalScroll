package jp.shotakubota.horizontalscroll.Barricade;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import jp.shotakubota.horizontalscroll.BarricadeConf.BConf;
import jp.shotakubota.horizontalscroll.BarricadeConf.Def;
import jp.shotakubota.horizontalscroll.BarricadeConf.DiagramCalcr;
import jp.shotakubota.horizontalscroll.BarricadeConf.Move;
import jp.shotakubota.horizontalscroll.MyData.MyData;
import jp.shotakubota.horizontalscroll.Object.Vec;
import jp.shotakubota.horizontalscroll.Manager.Task;
import jp.shotakubota.horizontalscroll.Object.Circle;

/** Created by Shota on 2017/05/11. */

public class Barricade extends Task {

    public enum eType{
        OUT,
        GOAL
    }

    private MyData s;
    protected PointF  _center = new PointF(0,0); // 図形の中心点
    protected PointF  _pt[];                     // 図形の頂点
    protected Paint   _paint = new Paint();      // ペイント
    protected eType   _type = eType.OUT;         // タイプ(当たるとアウトな壁、ゴールの壁、等)
    protected double  _rotaSpeed = 0;            // 回転スピード
    protected double  _speedX = 0;               // x軸の移動させるスピード
    protected double  _speedY = 0;               // y軸の移動させるスピード
    protected boolean _flag = false;             // 回転させるかどうかのフラグ
    protected int     _count = 0;                // カウントアップ数(入力したカウント)
    protected int     _countUpC = 0;             // カウントアップ数(勝手に上がっていくカウント)
    protected int     _countUpS = 0;             // カウントアップ数(勝手に上がっていくカウント)

    //****************************************************
    // コンストラクタ. type=タイプ.n=頂点の数.conf=設定情報.
    public Barricade(int n, BConf conf){
        if( conf != null ){
            _rotaSpeed = conf.speed;  // 回転スピード
            _type      = conf.type;   // 物体のタイプ
            _flag      = conf.flag;   // 回転判定フラグ
            _speedX    = conf.speedX; // x軸の移動スピード
            _speedY    = conf.speedY; // y軸の移動スピード
            _count     = conf.count;  // カウント
        }
        switch(_type){
            case OUT:                 // 接触してアウトな物
                _paint.setColor(Color.RED);
                break;
            case GOAL:                // 接触してゴールな物
                _paint.setColor(Color.GREEN);
                break;
        }
        _countUpC = _countUpS = 0;
        _pt = new PointF[n];          // 頂点配列を作る
        for( int i=0; i<n; i++ )
            _pt[i] = new PointF();    // 頂点を作る
    }

    //****************************************************
    // 更新する
    public boolean onUpdate() {
        if(_flag) {
            // In the case of a circle, move processing is performed.
            if(_pt.length < 1){
                Move.Moving(_center, _speedX, _speedY, _count, _countUpC);
                _countUpC++;
                return true;
            }
            // In the case of a square, move processing is performed.
            if(_speedX != 0 || _speedY != 0){
                Move.Moving(    _pt, _speedX, _speedY, _count, _countUpS);
                _countUpS++;
                return true;
            }
        }
        if(_rotaSpeed != 0) // if it rotates
            DiagramCalcr.RotateDiagram(_pt, _center, _rotaSpeed); // 頂点リスト(_pt)を_centerを中心に回転する
        return true;
    }

    //****************************************************
    // 接触しているかを問う.円cirが線分vecと接触していれば接触した物体のタイプを返す.接触していなければNOを返す.
    public Def.eHitCode isHit( final Circle cir, Vec vec ){
        if( DiagramCalcr.isHit( _pt, _center, cir, vec ) == true ) {
            switch(_type){
                case OUT:         // アウトな線なら
                    return Def.eHitCode.OUT;
                case GOAL:        // ゴールな線なら
                    return Def.eHitCode.GOAL;
            }
        } return Def.eHitCode.NO; // 何も接触していない
    }

    //****************************************************
    // 描画する
    public void onDraw(Canvas c){
        // 頂点が1未満の描画
        if(_pt.length < 1) {
            c.drawCircle(_center.x, _center.y, s.wide, _paint);
            return;
        }
        Path path = new Path();
        path.moveTo(_pt[0].x, _pt[0].y);     // パスの初期位置をセット
        for( int i=0; i<_pt.length; i++ )
            path.lineTo(_pt[i].x, _pt[i].y); // 頂点の位置へラインを引いていく
        c.drawPath(path, _paint);            // 引いたラインを描画する
    }

}
