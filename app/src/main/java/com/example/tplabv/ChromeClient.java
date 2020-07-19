package com.example.tplabv;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

public class ChromeClient extends WebChromeClient {
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    protected FrameLayout mFullscreenContainer;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;

    ChromeClient() {}

    public Bitmap getDefaultVideoPoster()
    {
        if (mCustomView == null) {
            return null;
        }
        return BitmapFactory.decodeResource(Lista.activitylista.getApplicationContext().getResources(), 2130837573);
    }

    public void onHideCustomView()
    {
        ((FrameLayout)Lista.activitylista.getWindow().getDecorView()).removeView(this.mCustomView);
        this.mCustomView = null;
        Lista.activitylista.getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
        //Lista.activitylista.setRequestedOrientation(this.mOriginalOrientation);
        this.mCustomViewCallback.onCustomViewHidden();
        this.mCustomViewCallback = null;
        Lista.activitylista.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
        if (this.mCustomView != null)
        {
            onHideCustomView();
            return;
        }
        this.mCustomView = paramView;
        this.mOriginalSystemUiVisibility = Lista.activitylista.getWindow().getDecorView().getSystemUiVisibility();
        this.mOriginalOrientation = Lista.activitylista.getRequestedOrientation();
        //Activity.getResources().getConfiguration().orientation
        this.mCustomViewCallback = paramCustomViewCallback;
        ((FrameLayout)Lista.activitylista.getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
       // Lista.activitylista.getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        Lista.activitylista.getWindow().getDecorView().setSystemUiVisibility(3846);
        Lista.activitylista.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}