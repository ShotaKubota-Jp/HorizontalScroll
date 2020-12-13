package jp.shotakubota.horizontalscroll.BarricadeConf;

import android.graphics.PointF;

/** Created by Shota on 2017/05/13. */

public class Move {

    //private static int countUpS;
    //private static int countUpC;

    //****************************************************
    // Process to movement Square Barricade
    // pt[]    - 座標軸(xとyが入っている)
    // speedX  - x軸に進むスピード
    // speedY  - y軸に進むスピード
    // count   - 1秒間にカウントアップする回数
    public static void Moving(PointF pt[],   final double speedX, final double speedY,final int count, int countUpS){
        for(int i=0; i<4; i++) {
            pt[i].x += Math.sin( Math.PI * 2 / count * countUpS) * speedX;
            pt[i].y += Math.cos( Math.PI * 2 / count * countUpS) * speedY;
        } //countUpS++;
    }

    //****************************************************
    // Process to movement Circle Barricade
    public static void Moving(PointF center, final double speedX, final double speedY,final int count, int countUpC){
        center.x += Math.sin( Math.PI * 2 / count * countUpC) * speedX;
        center.y += Math.cos( Math.PI * 2 / count * countUpC) * speedY;
        //countUpC++;
    }

}
