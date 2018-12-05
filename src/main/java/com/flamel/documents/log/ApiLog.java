package com.flamel.documents.log;

import com.flamel.documents.error.ApiExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiLog {

    public static Logger getLogger(Class type) {
        return LoggerFactory.getLogger(ApiExceptionHandler.class);
    }
}
