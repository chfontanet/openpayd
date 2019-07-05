package com.openpayd.transactions.persistence.repository;

import com.openpayd.transactions.persistence.model.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * The address repository
 */
public interface AddressRepository extends CrudRepository<Address, Long> {
}
