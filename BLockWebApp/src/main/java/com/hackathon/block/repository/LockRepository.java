package com.hackathon.block.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.Lock;

@RepositoryRestResource
public interface LockRepository extends CrudRepository<Lock, Long> {

	Lock findOne(String id);

	List<Lock> findByTransactionId(long transactionId);

	Page<Lock> findAll(Pageable page);

	List<Lock> findAll();
}
