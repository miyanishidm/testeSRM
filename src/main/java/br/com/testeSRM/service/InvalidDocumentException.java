package br.com.testeSRM.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidDocumentException extends ResponseStatusException {

    public InvalidDocumentException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

}
