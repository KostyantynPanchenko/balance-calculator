package balance.calculator.api.resources.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import balance.calculator.api.resources.BalanceResource;
import balance.calculator.dto.BalanceDTO;
import balance.calculator.service.BalanceService;

/**
 * Implementation of <code>BalanceResource</code>
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @see <code>BalanceResource</code>
 * @since 09/03/2017
 */
public class BalanceResourceImpl implements BalanceResource {

    private BalanceService balanceService;
    private final String NOT_FOUND = "Balance for specified date and register id=%d does not exist.";
    private final String BAD_REQUEST = "Could not parse specified date %s. Check if it is compliant with yyyy-MM-dd pattern";
    
    public BalanceResourceImpl(BalanceService balanceService) {
        this.balanceService = balanceService;
    }
    
    @Override
    public Response getBalance(Long registerId, String date) {
        BalanceDTO balance = null;
        if (date == null) {
            balance = balanceService.getCurrentBalance(registerId);
        } else {
            balance = balanceService.getBalanceForDate(registerId, parseDate(date));
        }
        if (balance == null) {
            throw new NotFoundException(String.format(NOT_FOUND, registerId));
        }
        return Response.status(Status.OK).entity(balance).build();
    }

    /**
     * Obtains an instance of LocalDate from a text string using a specific formatter.
     * The text is parsed using the formatter, returning a date.
     *  
     * @param date the text to parse
     * @return the parsed local date, not null
     * @throws BadRequestException if text could not be parsed
     */
    private LocalDate parseDate(String date) throws BadRequestException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        } catch (DateTimeParseException  e) {
            throw new BadRequestException(String.format(BAD_REQUEST, date));
        }
    }

}
