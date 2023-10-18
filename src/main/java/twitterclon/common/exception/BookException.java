package twitterclon.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class BookException extends RuntimeException {
    private final HttpStatus errorCode;
    private final String errorMessage;
}
