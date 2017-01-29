package com.hackathon.block.controllers;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.block.implementation.ProcessImp;

@RestController
@RequestMapping("/lock")
public class LockController {

	private ProcessImp service = new ProcessImp();

	@RequestMapping("/{id}")
	public String findById(@PathVariable(value = "id") String lockId) {
		 return service.getLock(lockId);
//		return "";
	}

	@RequestMapping(value = "/lock/{id}", method = RequestMethod.POST)
	public String lock(@RequestBody @NotNull String lockId) {
		return service.lock(lockId);
	}

	@RequestMapping(value = "/unlock/{id}", method = RequestMethod.POST)
	public String unlock(@RequestBody @NotNull String lockId) {
		return service.unlock(lockId);
	}

	@RequestMapping("/all")
	public String findAll() {
//		return service.getAllLocks();
		return "locks";
	}

}
