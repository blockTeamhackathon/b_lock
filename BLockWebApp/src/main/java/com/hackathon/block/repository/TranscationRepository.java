package com.hackathon.block.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.Transaction;

@RepositoryRestResource
public interface TranscationRepository extends CrudRepository<Transaction, Long> {

	Transaction findOne(Long id);

	Transaction findByLockId(long lockId);

	Page<Transaction> findAll(Pageable page);

	List<Transaction> findAll();

}
