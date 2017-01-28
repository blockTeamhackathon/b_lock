package com.hackathon.block.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.Transaction;
import com.hackathon.block.model.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOne(String id);

	List<User> findByTransactionId(long lockId);

	Page<User> findAll(Pageable page);

	List<User> findAll();

}
