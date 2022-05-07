package hu.oe.hoe.base;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nem található." )
public class NotFoundException extends RuntimeException{

}
