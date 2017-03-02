package com.softserveinc.balance.calculator.api.resources.impl;

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
        if (store == null) {
            throw new NotFoundException();
        }
        return store;
    }

    public Response save(StoreDTO storeDto) {
        if (storeService.save(storeDto) != 1) {
            throw new NotFoundException();
        }
        return Response.status(Status.CREATED).build();
    }

    public Response update(StoreDTO storeDto, Long id) {
        if (storeService.update(storeDto, id) != 1) {
            throw new NotFoundException();
        }
        return Response.noContent().build();
    }

    public Response delete(Long id) {
        if (storeService.delete(id) != 1) {
            throw new NotFoundException();
        }
        return Response.noContent().build();
    }
}
