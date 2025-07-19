package com.yourmenu.yourmenu_api.order_client.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException() {
        super("Número de telefone inválido");
    }

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
