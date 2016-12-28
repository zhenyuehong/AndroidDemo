package com.mtelnet.myview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class VRActivity extends AppCompatActivity {
    /**
     * 显示VR360度全景图片的控件
     */
    private VrPanoramaView vr_pan_view;

    /**
     * 打印的TAG
     */
    private final String TAG = "VRActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);
        load360Image();
    }


    /**
     * 加载360度全景图片
     */
    private void load360Image() {
        vr_pan_view = (VrPanoramaView) findViewById(R.id.vr_pan_view);

//        vr_pan_view.setFullscreenButtonEnabled (false); //隐藏全屏模式按钮

        vr_pan_view.setStereoModeButtonEnabled(false);//隐藏VR模式按钮

        vr_pan_view.setInfoButtonEnabled(false);//隐藏VR Info 按钮

        /**获取assets文件夹下的图片**/
        InputStream open = null;
        try {
            open = getAssets().open("andes.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(open);
        /**设置加载VR图片的相关设置**/
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        options.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        /**设置加载VR图片监听**/
        vr_pan_view.setEventListener(new VrPanoramaEventListener() {
            /**
             * 显示模式改变回调
             * 1.默认
             * 2.全屏模式
             * 3.VR观看模式，即横屏分屏模式
             * @param newDisplayMode 模式
             */
            @Override
            public void onDisplayModeChanged(int newDisplayMode) {
                super.onDisplayModeChanged(newDisplayMode);
                Log.d(TAG, "onDisplayModeChanged()->newDisplayMode=" + newDisplayMode);
            }

            /**
             * 加载VR图片失败回调
             * @param errorMessage
             */
            @Override
            public void onLoadError(String errorMessage) {
                super.onLoadError(errorMessage);
                Log.d(TAG, "onLoadError()->errorMessage=" + errorMessage);
            }

            /**
             * 加载VR图片成功回调
             */
            @Override
            public void onLoadSuccess() {
                super.onLoadSuccess();
                Log.d(TAG, "onLoadSuccess->图片加载成功");
            }

            /**
             * 点击VR图片回调
             */
            @Override
            public void onClick() {
                super.onClick();
                Log.d(TAG, "onClick()");
            }
        });
        /**加载VR图片**/
        vr_pan_view.loadImageFromBitmap(bitmap, options);
    }

    @Override
    protected void onPause() {
        super.onPause();
        vr_pan_view.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vr_pan_view.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vr_pan_view.shutdown();
    }
}
