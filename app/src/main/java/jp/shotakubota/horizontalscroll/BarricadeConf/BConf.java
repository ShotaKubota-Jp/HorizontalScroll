package jp.shotakubota.horizontalscroll.BarricadeConf;

import jp.shotakubota.horizontalscroll.Barricade.Barricade;

/** Created by Shota on 2017/05/11. */

public class BConf {

    public double speed = 0;                                   // 回転するスピード
    public Barricade.eType type = Barricade.eType.OUT;        //
    public boolean flag = false;                              // <<--- added by shota 移動するかどうか
    public double speedX = 0;                                  // <<--- added by shota
    public double speedY = 0;                                  // <<--- added by shota
    public int count = 0;                                     // <<--- added by shota

    public BConf(Barricade.eType atype){this.type = atype;}   // Type
    public BConf(double aspeed){ this.speed = aspeed; }        // 回転するタイプ

    //****************************************************
    // x,yの値をバラバラにして移動させる場合
    public BConf(double aspeedX, double aspeedY, boolean flag){
        this.flag = flag;
        this.speedX = aspeedX;
        this.speedY = aspeedY;
    }

    //****************************************************
    // x,yの値をバラバラにして移動させる場合
    // count    カウントアップ数
    // aspeedX  x軸のスピード
    // aspeedY  y軸のスピード
    // flag     移動させるかどうかの判定フラグ
    public BConf(int count, double aspeedX, double aspeedY, boolean flag){
        this.flag = flag;
        this.speedX = aspeedX;
        this.speedY = aspeedY;
        this.count = count;
    }

}
