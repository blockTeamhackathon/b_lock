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

	@RequestMapping("/find/{id}")
	public String findById(@PathVariable(value = "id") String lockId) {
		 return service.getLock(lockId);
//		return "";
	}

	@RequestMapping(value = "/lock", method = RequestMethod.POST)
	public String lock(@RequestBody @NotNull String txid, String json) {
		return service.lock(txid, json);
	}

	@RequestMapping(value = "/unlock", method = RequestMethod.POST)
	public String unlock(@RequestBody @NotNull String txid, String json) {
		return service.endTransaction(txid, json);
	}

	@RequestMapping("/all")
	public String findAll() {
		// return service.getAllLocks();
		return "locks";
	}

}
