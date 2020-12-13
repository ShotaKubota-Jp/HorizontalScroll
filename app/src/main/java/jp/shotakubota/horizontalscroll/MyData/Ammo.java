package jp.shotakubota.horizontalscroll.MyData;

/** Created by Shota on 2017/02/12. */

public class Ammo {

    //-----------------------------------------------------
    private float x;                // x座標(弾)
    private float y;                // y座標(弾)
    private int speed;              // 弾のスピード
    private double rad;             // 角度
    private boolean flag;           // 弾のフラグ

    //-----------------------------------------------------
    // コンストラクタ
    public Ammo(float x,float y,int speed,double rad){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.rad = rad;
    }

    public float getX(){return x;}
    public float getY(){return y;}
    public int getSpeed(){return speed;}
    public double getRad(){return rad;}
    public boolean getFlag(){return flag;}

}
