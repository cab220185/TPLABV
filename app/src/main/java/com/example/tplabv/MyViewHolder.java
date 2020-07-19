package com.example.tplabv;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"380\" height=\"200\" src=\"https://www.youtube.com/\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
   //String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"380\" height=\"200\" src=\"https://www.youtube.com/\" frameborder=\"0\" gesture=\"media\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe></body></html>";
   // String frameVideo = "<html><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, shrink-to-fit=no'></header><body style='margin: 0px; padding: 0px; width:100%; height:100%;'>Video From YouTube<br><iframe  src=\"https://www.youtube.com/\" frameborder=\"0\" gesture=\"media\" allow=\" encrypted-media\"  frameborder=\"0\" allowfullscreen></iframe></body></html>";

    String frameVideo = "<html></header><body>Video From YouTube<br><div style=\"height:0;overflow:hidden;padding-bottom:56.25%;padding-top:30px;position:relative\" ><iframe style=\"height:100%;left:0;position:absolute;top:0;width:100%\" src=\"https://www.youtube.com/\" frameborder=\"0\" gesture=\"media\" allow=\" encrypted-media\"  frameborder=\"0\" allowfullscreen></iframe></div></body></html>";

    TextView txtteaser;
    TextView txtTitle;
    TextView txttype;
    TextView txtlinkyoutube;
    ImageView imvPoster;
    WebView displayYoutubeVideo ;
    MyOnItemClick listener;

    private int position;

    public MyViewHolder(View itemView , MyOnItemClick listener ) {
        super(itemView);
        txttype = (TextView) itemView.findViewById(R.id.txttype);
        txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        txtteaser = (TextView) itemView.findViewById(R.id.txttease);
        imvPoster = (ImageView)itemView.findViewById(R.id.imageView) ;
        displayYoutubeVideo = (WebView)itemView.findViewById(R.id.mWebViewyoutube);

        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.setWebChromeClient(new ChromeClient());
        displayYoutubeVideo.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        displayYoutubeVideo.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        displayYoutubeVideo.getSettings().setAppCacheEnabled(true);
        displayYoutubeVideo.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setEnableSmoothTransition(true);

        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
        txtlinkyoutube = (TextView) itemView.findViewById(R.id.linkyoutube);
        txtlinkyoutube.setOnClickListener(this);
        txtteaser.setOnClickListener(this);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(position,v);
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
