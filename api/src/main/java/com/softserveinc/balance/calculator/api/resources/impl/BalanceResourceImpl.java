package com.softserveinc.balance.calculator.api.resources.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.softserveinc.balance.calculator.api.exception.ErrorMessage;
import com.softserveinc.balance.calculator.api.resources.BalanceResource;

public class BalanceResourceImpl implements BalanceResource {

    @Override
    public Response getBalance(String date) {
        if (date != null) {
            System.out.println(date);
            LocalDate theDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return Response.status(Status.OK).entity(new ErrorMessage(900, theDate.toString())).build();
        }
        return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(900, "GOTCHA!")).build();
    }

}
