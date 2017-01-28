package com.hackathon.block.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.Lock;
import com.hackathon.block.model.User;

@RepositoryRestResource
public interface LockRepository extends CrudRepository<Lock, Long> {

}
