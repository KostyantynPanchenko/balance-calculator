package com.softserveinc.balance_calculator.service.impl;

import com.softserveinc.balance_calculator.domain.Register;
import com.softserveinc.balance_calculator.dto.RegisterDTO;
import com.softserveinc.balance_calculator.repository.RegisterDAO;
import com.softserveinc.balance_calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance_calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.RegisterService;
import com.softserveinc.balance_calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO registerDao) {
        this.registerDao = registerDao;
    }
    
    public RegisterDTO getRegisterById(Long storeId, Long registerId) throws EntityNotFoundServiceException, ServiceException {
        try {
            return new RegisterDTO(registerDao.getRegisterById(storeId, registerId));
        } catch (DomainEntityNotFoundException notFound) {
            throw new EntityNotFoundServiceException(notFound);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Long save(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return registerDao.insert(toRegister(registerDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public int update(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return registerDao.update(toRegister(registerDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public int delete(Long storeId, Long registerId) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return registerDao.delete(storeId, registerId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    
    private Register toRegister(RegisterDTO registerDto) {
        return new Register.Builder()
                .setId(registerDto.getId())
                .setStoreId(registerDto.getStoreId())
                .setName(registerDto.getName())
                .setTimezone(registerDto.getTimezone())
                .build();
    }

}
