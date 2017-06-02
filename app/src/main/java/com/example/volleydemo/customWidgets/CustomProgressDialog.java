package com.example.volleydemo.customWidgets;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.volleydemo.R;
import com.github.ybq.android.spinkit.SpinKitView;


public class CustomProgressDialog extends ProgressDialog {
    public static final String TAG = CustomProgressDialog.class.getSimpleName();
    private SpinKitView progressView;
    private TextView progressMessage;
    private String message;

    public CustomProgressDialog(Context context, String message, int theme) {
        super(context, theme);
        this.message = message;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_progress);
        initViews();
    }

    private void initViews() {
        progressView = (SpinKitView) findViewById(R.id.progressView);
        progressMessage = (TextView) findViewById(R.id.progressText);
        progressMessage.setText(Html.fromHtml(message));
    }
}

