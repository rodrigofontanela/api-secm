package com.flamel.documents.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.flamel.documents.error.ErrorResponse.ApiError;
import com.flamel.documents.log.ApiLog;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ApiExceptionHandler extends AbstractApiExceptionHandler {

    public ApiExceptionHandler(MessageSource apiErrorMessageSource) {
        super(apiErrorMessageSource);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception, final Locale locale) {

        List<ApiError> apiErrors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(toList());

        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(final InvalidFormatException exception, final Locale locale) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(HttpStatus.BAD_REQUEST, toApiError("GEN-010", locale, exception.getValue())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception, final Locale locale) {
        ApiLog.getLogger(ApiExceptionHandler.class).error("Error not expected", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, toApiError("INT-010", locale)));
    }
}
