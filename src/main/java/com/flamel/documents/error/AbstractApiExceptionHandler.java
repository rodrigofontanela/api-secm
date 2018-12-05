package com.flamel.documents.error;

import com.flamel.documents.log.ApiLog;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@RequiredArgsConstructor
public abstract class AbstractApiExceptionHandler {
    protected final MessageSource apiErrorMessageSource;

    protected ErrorResponse.ApiError toApiError(final String code, final Locale locale, Object... args) {
        String message = "No message available";;

        try {
            message = apiErrorMessageSource.getMessage(code, args, locale);

        } catch (NoSuchMessageException exception) {
            ApiLog.getLogger(com.flamel.documents.error.ApiExceptionHandler.class).error("Could not find any message for {} code under {} locale", code, locale);
        }

        return new ErrorResponse.ApiError(code, message);
    }
}
