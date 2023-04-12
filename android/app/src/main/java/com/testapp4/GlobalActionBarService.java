package com.testapp4;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.AbstractMutableList;

public class GlobalActionBarService extends AccessibilityService {
    private Context context = this;
    FrameLayout mLayout;
    public static String TAG = "myAccessibility";
    private String eventHere = "";
    AccessibilityUtil accessibilityUtil=new AccessibilityUtil();


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.v(TAG,event+"");
        String result = accessibilityUtil.filterBrowser(event,this,getSupportedBrowser());
        if(!result.equalsIgnoreCase("")) {
            sendMessageToActivity(result);
        }
    }

    private List<SupportedBrowser> getSupportedBrowser() {
        List<SupportedBrowser> browsers = new ArrayList<SupportedBrowser>();
         browsers.add(0,new SupportedBrowser("com.android.chrome",
                 "com.android.chrome:id/url_bar"));
         return browsers;
    }

    private void sendMessageToActivity(String msg) {
        Intent intent = new Intent("Event Message");
// You can also include some extra data.
        intent.putExtra ("Status", msg);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public boolean onGesture(@NonNull AccessibilityGestureEvent gestureEvent) {
        Log.v("myGesture",gestureEvent.toString());
        return super.onGesture(gestureEvent);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        Log.v(TAG,"onServiceConnected");
        // Create an overlay and display the action bar
       /* WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.action_bar, mLayout);
        wm.addView(mLayout, lp);
        configurePowerButton(); */

        AccessibilityServiceInfo info = getServiceInfo();
          info.eventTypes = AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED | AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED | AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED;
          info.feedbackType = AccessibilityServiceInfo.FEEDBACK_VISUAL;
       // info.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;
          setServiceInfo(info);
          super.onServiceConnected();
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }
}
