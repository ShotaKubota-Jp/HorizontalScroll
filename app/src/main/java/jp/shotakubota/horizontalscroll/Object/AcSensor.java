package jp.shotakubota.horizontalscroll.Object;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

/** Created by Shota on 2017/02/12. */

public class AcSensor implements SensorEventListener {

    private SensorManager _sensorManager = null;
    private float _x, _y, _z;

    public void onCreate(Context c) {
        _sensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);//センサーマネージャを取得
        onResume();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    //アクティビティが動き始めたらリスナーを登録する
    public void onResume() {
        List<Sensor> sensorList = _sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);//センサーリストを取得
        if (sensorList != null && !sensorList.isEmpty()) {
            Sensor sensor = sensorList.get(0);
            _sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);//リスナー登録
        }
    }

    //アクティビティがポーズになったらリスナーを止める
    public void onPause() {
        if( _sensorManager == null ) return;
        _sensorManager.unregisterListener(this);
    }

    //センサーの値に変化があった時呼ばれる
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            _x = event.values[SensorManager.DATA_X]; // X軸
            _y = event.values[SensorManager.DATA_Y]; // Y軸
            _z = event.values[SensorManager.DATA_Z]; // Z軸
        }
    }

    public float getX(){return _x;}
    public float getY(){return _y;}
    public float getZ(){return _z;}

    //シングルトン
    private static AcSensor _instance = new AcSensor();
    private AcSensor() {_x = _y = _z = 0;}
    public static AcSensor Inst() {return _instance;}
}
