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
    
    public RegisterDTO getRegisterById(Long registerId) throws ServiceException {
        try {
            return new RegisterDTO(registerDao.getRegisterById(registerId));
        } catch (DomainEntityNotFoundException notFound) {
            throw new EntityNotFoundServiceException();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int save(RegisterDTO registerDto, Long storeId) throws ServiceException {
        try {
            return registerDao.save(createRegister(registerDto, storeId));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation.getMessage());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int update(RegisterDTO registerDto, Long storeId, Long registerId) throws ServiceException {
        try {
            return registerDao.update(createRegister(registerDto, storeId), registerId);
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation.getMessage());
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public int delete(Long storeId, Long registerId) throws ServiceException {
        try {
            return registerDao.delete(storeId, registerId);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    
    private Register createRegister(RegisterDTO registerDto, Long storeId) {
        return new Register.Builder().setStoreId(storeId)
                                    .setName(registerDto.getName())
                                    .setTimezone(registerDto.getTimezone())
                                    .build();
    }

}
