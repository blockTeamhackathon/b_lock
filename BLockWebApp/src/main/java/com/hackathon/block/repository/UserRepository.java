package com.hackathon.block.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findOne(Long id);

	Page<User> findAll(Pageable page);

	List<User> findAll();

}
