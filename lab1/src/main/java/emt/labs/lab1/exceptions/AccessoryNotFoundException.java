package emt.labs.lab1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Accessory Not Found Exception")
public class AccessoryNotFoundException extends RuntimeException {
}
