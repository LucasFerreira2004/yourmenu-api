package com.yourmenu.yourmenu_api.shared.globalExceptions;

public class EntityDoesNotBelongToAnotherEntityException extends ApplicationException {
    public EntityDoesNotBelongToAnotherEntityException(String childEntity, String parentEntity) {
        super(childEntity, "childEntity" + "nao pertece a(o) " + parentEntity);
    }
}
