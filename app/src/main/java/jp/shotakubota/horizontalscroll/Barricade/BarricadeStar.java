package jp.shotakubota.horizontalscroll.Barricade;

import jp.shotakubota.horizontalscroll.BarricadeConf.BConf;

/** Created by Shota on 2017/05/12. */

public class BarricadeStar extends Barricade {

    private static final float PI2 = (float) (Math.PI*2);

    public BarricadeStar(float x, float y, float inR, float outR, BConf conf) {
        super(10, conf);
        for(int i=0; i<5; i++){
            _pt[i*2+0].x = (float) (x + Math.cos(PI2/5*i)*inR);//内側
            _pt[i*2+0].y = (float) (y + Math.sin(PI2/5*i)*inR);
            _pt[i*2+1].x = (float) (x + Math.cos(PI2/5*i+PI2/10)*outR);//外側
            _pt[i*2+1].y = (float) (y + Math.sin(PI2/5*i+PI2/10)*outR);
        }
        _center.x = x;
        _center.y = y;
    }

}