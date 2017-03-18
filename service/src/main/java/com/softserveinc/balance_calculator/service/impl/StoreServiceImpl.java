package com.softserveinc.balance_calculator.service.impl;

import com.softserveinc.balance_calculator.domain.Store;
import com.softserveinc.balance_calculator.dto.StoreDTO;
import com.softserveinc.balance_calculator.repository.StoreDAO;
import com.softserveinc.balance_calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.StoreService;
import com.softserveinc.balance_calculator.service.exceptions.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exceptions.ServiceException;
import com.softserveinc.balance_calculator.service.impl.mappers.StoreMapper;

public class StoreServiceImpl implements StoreService {

    private StoreDAO storeDao;

    public StoreServiceImpl(StoreDAO storeDao) {
        this.storeDao = storeDao;
    }

    public StoreDTO getStoreById(Long id) throws ServiceException {
        try {
            Store store = storeDao.getStoreById(id);
            if (store == null) {
                return null;
            }
            return StoreMapper.toDTO(store);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    
    public Long save(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return storeDao.save(StoreMapper.toDomainObject(storeDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    
    public int update(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return storeDao.update(StoreMapper.toDomainObject(storeDto));
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

}
