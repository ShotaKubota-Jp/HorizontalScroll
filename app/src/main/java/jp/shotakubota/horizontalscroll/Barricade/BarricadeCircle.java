package jp.shotakubota.horizontalscroll.Barricade;

import jp.shotakubota.horizontalscroll.BarricadeConf.BConf;

/** Created by Shota on 2017/05/13. */

public class BarricadeCircle extends Barricade {

    public BarricadeCircle( float x, float y, BConf conf){
        super(0,conf);
        _center.x = x;
        _center.y = y;
    }

}
