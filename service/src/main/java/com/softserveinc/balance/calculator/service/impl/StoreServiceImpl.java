package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.repository.StoreDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.service.StoreService;
import com.softserveinc.balance.calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance.calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public class StoreServiceImpl implements StoreService {

    private StoreDAO storeDao;

    public StoreServiceImpl(StoreDAO storeDao) {
        this.storeDao = storeDao;
    }

    public StoreDTO getStoreById(Long id) throws EntityNotFoundServiceException, ServiceException {
        try {
            return new StoreDTO(storeDao.getStoreById(id));
        } catch (DomainEntityNotFoundException empty) {
            throw new EntityNotFoundServiceException(empty);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    
    public Long save(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return storeDao.save(toStore(storeDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    
    public int update(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return storeDao.update(toStore(storeDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public int delete(Long id) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return storeDao.deleteById(id);
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    private Store toStore(StoreDTO storeDto) {
        return new Store.Builder()
                .setId(storeDto.getId())
                .setTenantId(storeDto.getTenantId())
                .setName(storeDto.getName())
                .setDescription(storeDto.getDescription()).build();
    }

}
