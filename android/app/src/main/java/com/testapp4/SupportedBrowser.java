package com.testapp4;

public class SupportedBrowser {
    private String packagename;
    private String addressBarId;


    SupportedBrowser(String packagename,String addressBarId)
    {
        this.packagename=packagename;
        this.addressBarId=addressBarId;
    }

    public String getAddressBarId() {
        return addressBarId;
    }

    public String getPackagename() {
        return packagename;
    }
}
