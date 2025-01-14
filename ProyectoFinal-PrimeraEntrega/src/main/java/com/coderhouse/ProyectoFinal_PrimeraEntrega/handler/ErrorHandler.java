package com.coderhouse.ProyectoFinal_PrimeraEntrega.handler;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType.*;

@Component
public class ErrorHandler {

    public ResponseEntity<String> handleError(ErrorType errorType) {
        HttpStatus status;
        String responseMessage = "Error: " + errorType.getDescription();

        switch (errorType) {
            case PRODUCT_NOT_FOUND,
                 CLIENT_NOT_FOUND,
                 TICKET_NOT_FOUND,
                 CART_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            case CART_IS_EMPTY,
                 PRODUCT_OUT_OF_STOCK,
                 CLIENT_DOC_ID_ALREADY_EXIST,
                 TICKET_HAS_NO_ITEMS,
                 PRODUCT_CODE_ALREADY_EXIST:
                status = HttpStatus.BAD_REQUEST;
                break;
            case SYSTEM_ERROR:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        return new ResponseEntity<>(responseMessage, status);
    }
}

