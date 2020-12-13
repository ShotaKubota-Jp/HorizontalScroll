package jp.shotakubota.horizontalscroll.Barricade;

import jp.shotakubota.horizontalscroll.BarricadeConf.BConf;

/** Created by Shota on 2017/05/11. */

public class BarricadeSquare extends Barricade{

    public BarricadeSquare( float x, float y, float w, float h, BConf conf ){
        super(4,conf);
        _pt[0].x  = x;          _pt[0].y = y;
        _pt[1].x  = x+w;        _pt[1].y = y;
        _pt[2].x  = x+w;        _pt[2].y = y+h;
        _pt[3].x  = x;          _pt[3].y = y+h;
        _center.x = x+w/2;
        _center.y = y+h/2;
    }

}
