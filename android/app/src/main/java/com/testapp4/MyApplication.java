package com.testapp4;


public class MyApplication {

    private TempClass tempClass = new TempClass();

    public String getTempClass() {
        return tempClass.getTemp();
    }

    public void setTempClass(String a) {
        tempClass.setTemp(a);
    }
}
