package com.softserveinc.balance_calculator.service.impl;

import com.softserveinc.balance_calculator.domain.Register;
import com.softserveinc.balance_calculator.dto.RegisterDTO;
import com.softserveinc.balance_calculator.repository.RegisterDAO;
import com.softserveinc.balance_calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.RegisterService;
import com.softserveinc.balance_calculator.service.exceptions.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exceptions.ServiceException;
import com.softserveinc.balance_calculator.service.impl.mappers.RegisterMapper;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO registerDao) {
        this.registerDao = registerDao;
    }
    
    public RegisterDTO getRegisterById(Long registerId) throws ServiceException {
        try {
            Register register = registerDao.getRegisterById(registerId);
            if (register == null) {
                return null;
            }
            return RegisterMapper.toDTO(register);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Long save(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return registerDao.insert(RegisterMapper.toDomainObject(registerDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public int update(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return registerDao.update(RegisterMapper.toDomainObject(registerDto));
        } catch (DataIntegrityViolationRepositoryException violation) {
            throw new DataIntegrityViolationServiceException(violation);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public int delete(Long registerId) throws DataIntegrityViolationServiceException, ServiceException {
        try {
            return registerDao.delete(registerId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}
