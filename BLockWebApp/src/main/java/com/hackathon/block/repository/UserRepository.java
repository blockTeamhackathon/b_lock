package com.hackathon.block.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

}
