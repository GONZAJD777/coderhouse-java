package com.coderhouse.ProyectoFinal_PrimeraEntrega.handler;

import com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType;
import com.coderhouse.ProyectoFinal_PrimeraEntrega.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.coderhouse.ProyectoFinal_PrimeraEntrega.model.ErrorType.*;

@Component
public class ErrorHandler {

    public static HttpStatus getStatus(ErrorType errorType) {
        HttpStatus status;


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
                 PRODUCT_CODE_ALREADY_EXIST,
                 PRODUCT_CODE_NOT_NULLABLE:
                status = HttpStatus.BAD_REQUEST;
                break;
            case SYSTEM_ERROR,DATE_SERVICE_UNAVAILABLE,DATABASE_ISSUES:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        return status;
    }

    public static String getErrorMessage (ErrorType errorType){
        return "Error: " + errorType.getDescription();
    }


}

