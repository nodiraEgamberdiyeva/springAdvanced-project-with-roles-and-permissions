package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends Throwable {
    String type;
    String message;

    public ForbiddenException(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
