package com.webshopapp.customerpanel.checkout;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class Checkout {

    private float productCost;

    private float productTotal;

    private float shippingCost;

    private final int deliveryDays = 7;

    public Date getDeliveryDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, deliveryDays);

        return calendar.getTime();
    }

}