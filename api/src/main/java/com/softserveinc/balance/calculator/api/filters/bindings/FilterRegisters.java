package com.softserveinc.balance.calculator.api.filters.bindings;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.ws.rs.NameBinding;

/**
 * Name binding annotation for marking <code>Register</code> endpoint method 
 * to be filtered by <code>RegisterFilter</code>.
 * 
 * @author Kostyantyn Panchenko 
 * @version 1.0
 * @since 03/17/2017
 */
@NameBinding
@Retention(RUNTIME)
public @interface FilterRegisters {

}
