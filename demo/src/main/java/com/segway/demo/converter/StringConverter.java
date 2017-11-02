package com.segway.demo.converter;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author No.47 create at 2017/11/2.
 */
public class StringConverter implements Converter<ResponseBody, String> {

    public static final StringConverter INSTANCE = new StringConverter();

    @Override
    public String Converter(ResponseBody value) throws IOException {
        return value.string();
    }
}