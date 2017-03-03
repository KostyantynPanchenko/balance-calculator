package com.softserveinc.balance.calculator.api.resources.impl;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.StoreService;

public class StoreResourceImpl implements StoreResource {

    private StoreService storeService;

    public StoreResourceImpl(StoreService storeService) {
        this.storeService = storeService;
    }

    public StoreDTO getStoreById(Long id) {
        StoreDTO store = storeService.getStoreById(id);
        checkOperationResult((store == null) ? 0 : 1, id);
        return store;
    }

    public Response save(StoreDTO storeDto) {
        if (storeService.save(storeDto) != 1) {
            throw new InternalServerErrorException("Could not save a record in database.");
        }
        return Response.status(Status.CREATED).build();
    }

    public Response update(StoreDTO storeDto, Long id) {
        checkOperationResult(storeService.update(storeDto, id), id);
        return Response.noContent().build();
    }

    public Response delete(Long id) {
        checkOperationResult(storeService.delete(id), id);
        return Response.noContent().build();
    }
    
    private void checkOperationResult(int result, Long id) {
        if (result != 1) {
            throw new NotFoundException("Store record with id=" + id + " not found.");
        }
    }
}
