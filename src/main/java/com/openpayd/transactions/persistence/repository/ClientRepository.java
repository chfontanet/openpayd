package com.openpayd.transactions.persistence.repository;

import com.openpayd.transactions.persistence.model.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * The client repository
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
}
