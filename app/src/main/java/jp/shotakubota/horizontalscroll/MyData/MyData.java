package jp.shotakubota.horizontalscroll.MyData;

/** Created by Shota on 2017/02/10. */

public class MyData {

    public static int width;    // Hardware's width
    public static int height;   // Hardware's height
    public static int wide;     // Barricade's width
    public static int pattern;  // ゲームの状態
    public static int hit;      // 障害物へのヒット判定
    public static int x;        // タッチしたx座標
    public static int y;        // タッチしたy座標
    public static boolean flag; // タッチしたかどうかの判定

    public MyData(int w, int h){
        this.width = w;
        this.height = h;
        wide = this.width/84;
        flag = false;
        pattern = hit = 0;
        x = y = 400;
    }

}
