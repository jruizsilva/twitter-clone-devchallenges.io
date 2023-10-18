package twitterclon.presentation.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import twitterclon.common.exception.BookException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BookException.class)
    public ResponseEntity<String> handleEmptyInput(BookException bookException) {
        return new ResponseEntity<>(bookException.getErrorMessage(), bookException.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()
                                                                                                 .substring(0, 17));
        problemDetail.setProperty("errors", mapErrors(ex.getBindingResult()
                                                        .getFieldErrors()));

        return problemDetail;
    }

    private Map<String, String> mapErrors(List<FieldError> errors) {
        Map<String, String> errorsMap = new HashMap<>();

        for (FieldError error : errors) {
            String fieldName = error.getField();
            String errorMessage = String.format("field %s %s", fieldName, error.getDefaultMessage());
            errorsMap.put(fieldName, errorMessage);
        }

        return errorsMap;
    }

}


