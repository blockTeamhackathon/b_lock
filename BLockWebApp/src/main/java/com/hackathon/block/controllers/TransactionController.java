package com.hackathon.block.controllers;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.block.implementation.ProcessImp;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	private ProcessImp service = new ProcessImp();

	@RequestMapping("/{id}")
	public String findById(@PathVariable(value = "id") String txId) {
		return service.getTransaction(txId);
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String startTransaction(@RequestBody @NotNull String txId, String json) {
		return service.startTransaction(txId, json);
	}

	@RequestMapping(value = "/end")
	public String endTransaction(@RequestBody @NotNull String txId) {
		return service.endTransaction(txId);
	}

	@RequestMapping("/all")
	public String findAll() {
		// return service.getAllTransactions();
		return "Transactions";
	}

}
