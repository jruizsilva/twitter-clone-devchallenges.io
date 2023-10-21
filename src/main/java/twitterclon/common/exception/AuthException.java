package twitterclon.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class AuthException extends RuntimeException {
    private final HttpStatus errorCode;
    private final String errorMessage;
}
