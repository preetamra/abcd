package com.testapp4;

import android.util.Log;

public class TempClass {
    private String Temp="";
    public static String TAG = "myTempClass";

    public void setTemp(String a) {
        Temp=a;
        Log.v(TAG,Temp);
    }

    public String getTemp() {
        return Temp;
    }
}
