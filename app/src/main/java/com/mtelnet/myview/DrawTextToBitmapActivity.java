package com.mtelnet.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.mtelnet.myview.utils.Utils;

public class DrawTextToBitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_text_to_bitmap);
        ImageView iv_draw= (ImageView) findViewById(R.id.iv_draw);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_header);
        Bitmap watermarBm=AddWatermark(this,bitmap,"zhenyue");
        iv_draw.setImageBitmap(watermarBm);
    }

    public static  Bitmap AddWatermark(Context context,Bitmap bm,String string){
      int width=bm.getWidth();
        int height=bm.getHeight();

        Bitmap bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);

        Paint paint=new Paint();

        //设置水印颜色
        paint.setColor(Color.RED);

        //设置水印字体大小
        paint.setTextSize(Utils.dip2px(context,60));

        //去锯齿
        paint.setAntiAlias(true);

        //获取原图片的内容
        canvas.drawBitmap(bm,0,0,paint);

        //在最右下方位置添加水印
        canvas.drawText(string,width*3/4,height-Utils.dip2px(context,36),paint);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return bitmap;
    };

}
