package com.woolf.cleanapp.util;


import java.util.HashMap;

public class RequestParams {

    private HashMap<String, String> params;

    public RequestParams() {
        params = new HashMap<>();
    }

    public static Builder newBuilder() {
        return new RequestParams().new Builder();
    }

    public class Builder {
        private Builder() {
            // private constructor
        }

        public Builder append(String key, String value) {
            params.put(key,value);
            return this;
        }


        public HashMap<String,String> build(){
            return params;
        }
    }
}
