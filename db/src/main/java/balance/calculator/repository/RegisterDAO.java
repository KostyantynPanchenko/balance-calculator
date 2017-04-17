package balance.calculator.repository;

import balance.calculator.domain.Register;

/**
 * Provides CRUD operations for <code>Register</code> domain objects.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public interface RegisterDAO {

    /**
     * Retrieves a <code>Register</code> entity with specified id.
     *
     * @param registerId    id of <code>Register</code> entity to be
     *                      retrieved
     * @return              retrieved <code>Register</code> entity
     */
    Register getRegisterById(Long registerId);
    
    /**
     * Saves given <code>Register</code> entity.
     * 
     * @param register  <code>Register</code> entity to be saved
     * @return          auto generated key of created entity
     */
    Long insert(Register register);
    
    /**
     * Updates given <code>Register</code> entity.
     * 
     * @param register  <code>Register</code> entity to be updated
     * @return          number of modified rows
     */
    int update(Register register);
    
    /**
     * Deletes <code>Register</code> entity.
     * 
     * @param registerId    id of <code>Register</code> to be deleted
     * @return              number of modified rows
     */
    int delete(Long registerId);
}
