package com.yourmenu.yourmenu_api.manage;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ManageId implements Serializable {
    private Long administratorId;
    private Long restaurantId;
}
