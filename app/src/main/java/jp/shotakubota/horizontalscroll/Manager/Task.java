package jp.shotakubota.horizontalscroll.Manager;

import android.graphics.Canvas;

/** Created by Shota on 2017/02/11. */

public abstract class Task {

    abstract public boolean onUpdate();
    abstract public void onDraw(Canvas c);

}
