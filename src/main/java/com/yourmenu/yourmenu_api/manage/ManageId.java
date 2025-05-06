package com.yourmenu.yourmenu_api.manage;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
public class ManageId implements Serializable {
    private String administratorId;
    private String restaurantId;
}
