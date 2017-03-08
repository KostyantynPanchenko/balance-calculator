package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance.calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO registerDao) {
        this.registerDao = registerDao;
    }
    
    public RegisterDTO getRegisterById(Long storeId, Long registerId) throws ServiceException {
        try {
            return new RegisterDTO(registerDao.getRegisterById(storeId, registerId));
        } catch (DomainEntityNotFoundException notFound) {
            throw new EntityNotFoundServiceException(notFound.getMessage(), notFound);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Long save(RegisterDTO registerDto) throws ServiceException {
        try {
            return registerDao.insert(toRegister(registerDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation.getMessage(), violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int update(RegisterDTO registerDto) throws ServiceException {
        try {
            return registerDao.update(toRegister(registerDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation.getMessage(), violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int delete(Long storeId, Long registerId) throws ServiceException {
        try {
            return registerDao.delete(storeId, registerId);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    
    private Register toRegister(RegisterDTO registerDto) {
        return new Register.Builder()
                .setStoreId(registerDto.getStoreId())
                .setName(registerDto.getName())
                .setTimezone(registerDto.getTimezone())
                .build();
    }

}
