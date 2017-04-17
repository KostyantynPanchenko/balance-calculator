package balance.calculator.api.filters.bindings;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.ws.rs.NameBinding;

/**
 * Name binding annotation for marking <code>Store</code> endpoint method 
 * to be filtered by <code>StoreFilter</code>.
 * 
 * @author Kostyantyn Panchenko 
 * @version 1.0
 * @since 03/17/2017
 */
@NameBinding
@Retention(RUNTIME)
public @interface FilterStores {

}
