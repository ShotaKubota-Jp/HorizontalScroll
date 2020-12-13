package jp.shotakubota.horizontalscroll.MAIN;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jp.shotakubota.horizontalscroll.MyData.MyData;
import jp.shotakubota.horizontalscroll.List.ImageArrayAdapter;
import jp.shotakubota.horizontalscroll.List.ListItem;
import jp.shotakubota.horizontalscroll.MyData.MyMusic;
import jp.shotakubota.horizontalscroll.R;

/** Created by 翔太 on 2017/02/10. */

public class Main02 extends MyMusic {

    private MyData s;
    private Intent intent;
    private ListView lv;
    private List<ListItem> list = new ArrayList<ListItem>();

    private static final String[] texts = {
            "Level 1" ,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);                        // タイトルバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);     // ステータスバーを消す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 画面のタイムアウト防止

        setContentView(R.layout.activity_list_view);

        for (int i=0; i<texts.length; i++) {
            ListItem item = new ListItem();
            item.setText(texts[i]);
            if(i%6 == 0)      item.setImageId(R.drawable.ic01);
            else if(i%6 == 1) item.setImageId(R.drawable.ic02);
            else if(i%6 == 2) item.setImageId(R.drawable.ic03);
            else if(i%6 == 3) item.setImageId(R.drawable.ic04);
            else if(i%6 == 4) item.setImageId(R.drawable.ic05);
            else if(i%6 == 5) item.setImageId(R.drawable.ic06);
            list.add(item);
        }

        // Adapterのインスタンスを作成
        ImageArrayAdapter adapter = new ImageArrayAdapter(this, R.layout.list_view_image_item, list);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);

        //リスト項目が選択された時のイベントを追加
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mpStop();
                if     (position%11 ==  8) mpChange(R.raw.combat2);
                else if(position%11 == 10) mpChange(R.raw.combat3);
                else                       mpChange(R.raw.combat1);
                mpStart();
                s.pattern = position; // 状態
                intent = new Intent(Main02.this, Main03.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        /* //リスト項目が長押しされた時のイベントを追加
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = position + "番目のアイテムが長押しされました";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                return false;
            }
        }); */

    }

    // OS標準の戻るボタン動作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // ボタンが押された時に呼ばれる
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            return false;
        }
        // それ以外のボタンなら標準の動きをさせる
        else return super.onKeyDown(keyCode, event);
    }

}
