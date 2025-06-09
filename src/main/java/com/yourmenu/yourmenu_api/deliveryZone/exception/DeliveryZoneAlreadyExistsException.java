package com.yourmenu.yourmenu_api.deliveryZone.exception;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class DeliveryZoneAlreadyExistsException extends ApplicationException {
    public DeliveryZoneAlreadyExistsException(String field, String message) {
        super(field, message);
    }
    public DeliveryZoneAlreadyExistsException(String field) {
        super(field, "Zona de entrega ja cadastrada");
    }
}
