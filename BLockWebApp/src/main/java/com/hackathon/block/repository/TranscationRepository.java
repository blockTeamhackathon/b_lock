package com.hackathon.block.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hackathon.block.model.Transaction;
import com.hackathon.block.model.User;

@RepositoryRestResource
public interface TranscationRepository extends CrudRepository<Transaction, Long> {

}
