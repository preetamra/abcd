package com.testapp4;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class AccessibilityUtil {
 private String packagename= "";
 private SupportedBrowser browserConfig = null;



 public String filterBrowser(AccessibilityEvent event, GlobalActionBarService accessibility, List<SupportedBrowser> getSupportedBrowser)
 {
  Log.v("myAccessibility","filterBrowser  " + getSupportedBrowser);
  try {
   AccessibilityNodeInfo parentNodeInfo = event.getSource();
   Log.v("myAccessibility","parentNodeInfo"+parentNodeInfo);
   if(event.getPackageName() != null)
   {
    packagename=event.getPackageName().toString();
    Log.v("myAccessibility","packagename"+packagename);
   }
   PackageManager packageManager = accessibility.getPackageManager();
   Log.v("myAccessibility","packageManager"+packageManager);
   try {
    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packagename,0);
    Log.v("myAccessibility","applicationInfo"+ applicationInfo);
    String foregroundAppName = packageManager.getApplicationLabel(applicationInfo).toString();
    Log.v("myAccessibility","foregroundAppName"+ foregroundAppName);
   } catch (PackageManager.NameNotFoundException e) {
    e.printStackTrace();    Log.v("myAccessibility","Error");
   }
   //getChild(parentNodeInfo);
   for (SupportedBrowser supportedBrowser:
        getSupportedBrowser) {
    Log.v("myAccessibility","supportedBrowser "+ supportedBrowser.getPackagename());
    Log.v("myAccessibility ","packagename " +packagename);
    if(supportedBrowser.getPackagename().toString().equalsIgnoreCase(packagename.toString())) {
     browserConfig = supportedBrowser;
     Log.v("myAccessibility","browserConfig"+ browserConfig);
    }
   }
   if(browserConfig == null) {
    Log.v("myAccessibility","browserConfig null");
    return "";
   }
   String capturedUrl=captureUrl(parentNodeInfo,browserConfig);
   Log.v("myAccessibility","capturedUrl" + capturedUrl);
   if (capturedUrl == null) {
    return "";
   }
   Log.v("myAccessibilityUtil","event: "+event);
   Log.v("myAccessibilityUtil","capturedUrl: "+capturedUrl);
   Log.v("myAccessibilityUtil","event: "+event.getContentChangeTypes());
   return capturedUrl;
  } catch (Exception e) {
   Log.v("myAccessibility","Catch" + e);
   e.printStackTrace();
   return "";
  }
 }

 private String captureUrl(AccessibilityNodeInfo parentNodeInfo, SupportedBrowser browserConfig) {
  Log.v("myAccessibility","captureUrl " + "parentNodeInfo: "+ parentNodeInfo+"browserConfig: " + browserConfig);
  if (browserConfig == null) return null;
  List<AccessibilityNodeInfo> nodes = parentNodeInfo.findAccessibilityNodeInfosByViewId(browserConfig.getAddressBarId());
  Log.v("myAccessibility","nodes"+ parentNodeInfo.getWindow());
  if(nodes == null || nodes.size() <= 0)
  {
   Log.v("myAccessibility","null nodes");
    return null;
  }
  AccessibilityNodeInfo addressBarInfo = nodes.get(0);
  Log.v("myAccessibility","addressBarInfo"+addressBarInfo);
  String url = null;
  if(addressBarInfo.getText() != null) {
   url = addressBarInfo.getText().toString();
   Log.v("myAccessibility","URL: "+ url);
  }
  addressBarInfo.recycle();
  return url;
 }

 private void getChild(AccessibilityNodeInfo parentNodeInfo) {
  Log.v("myAccessibility","parentNodeInfo");
  int i = parentNodeInfo.getChildCount();
  Log.v("myAccessibility","parentNodeInfo: i"+ i);
  for (int p = 0; p < i; p++) {
   Log.v("myAccessibility","parentNodeInfo forLoop"+p);
   AccessibilityNodeInfo n = parentNodeInfo.getChild(p);
   Log.v("myAccessibility","AccessibilityNodeInfo "+ n);
   if(n != null) {
    Log.v("myAccessibility","AccessibilityNodeInfo: "+ n);
    n.getViewIdResourceName();
    Log.v("myAccessibility","AccessibilityNodeInfo: getViewIdResourceName "+ n.getViewIdResourceName());
    if(n.getText() != null) {
     Log.v("myAccessibility","AccessibilityNodeInfo: getText "+ n.getText().toString());
     n.getText().toString();
    }
    Log.v("myAccessibility","getChild");
    getChild(n);
   }
  }
 }
}
