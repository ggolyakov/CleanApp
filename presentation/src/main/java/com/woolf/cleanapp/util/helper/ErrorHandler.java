package com.woolf.cleanapp.util.helper;


import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.woolf.cleanapp.R;
import com.woolf.cleanapp.util.ResUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;


public class ErrorHandler {

    private static final int LIMIT_REQUEST = 403;

    private String defaultMessage;
    private ResUtils resUtils;

    public ErrorHandler(ResUtils resUtils) {
        this.resUtils = resUtils;
        this.defaultMessage = resUtils.getString(R.string.error_default);
    }

    public String getError(Throwable throwable) {
        if (throwable instanceof UnknownHostException || throwable instanceof ConnectException) {
            return resUtils.getString(R.string.error_connect);
        } else if (throwable instanceof SocketTimeoutException) {
            return resUtils.getString((R.string.error_timeout));
        } else if (throwable instanceof HttpException) {
            int errorCode = ((HttpException) throwable).code();
            if (errorCode == LIMIT_REQUEST) {
                return resUtils.getString(R.string.error_limit);
            } else {
                return defaultMessage;
            }
        } else if (throwable instanceof SSLException) {
            return resUtils.getString((R.string.error_connect));

        } else {
            return defaultMessage;
        }
    }
}
