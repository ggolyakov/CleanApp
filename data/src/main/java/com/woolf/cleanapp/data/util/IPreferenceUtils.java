package com.woolf.cleanapp.data.util;


public interface IPreferenceUtils {

    String getStringValue(String key);

    int getIntValue(String key);

    long getLongValue(String key);

    boolean getBooleanValue(String key);
}
