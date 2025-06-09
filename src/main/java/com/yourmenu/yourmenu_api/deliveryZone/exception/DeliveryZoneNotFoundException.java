package com.yourmenu.yourmenu_api.deliveryZone.exception;

import com.yourmenu.yourmenu_api.shared.globalExceptions.ApplicationException;

public class DeliveryZoneNotFoundException extends ApplicationException {
    public DeliveryZoneNotFoundException(String field, String message) {
        super(field, message);
    }
    public DeliveryZoneNotFoundException(String field) {
        super(field, "Zona de entrega nao encontrada");
    }
}
