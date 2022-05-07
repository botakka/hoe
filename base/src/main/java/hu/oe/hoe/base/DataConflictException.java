package hu.oe.hoe.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Adat konfliktus.")
public class DataConflictException extends RuntimeException {}
