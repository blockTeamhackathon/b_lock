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

	@RequestMapping("/find/{id}")
	public String findById(@PathVariable(value = "id") String txid) {
		return service.getTransaction(txid, new String());
	}

	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String startTransaction(@RequestBody @NotNull String txid, String json) {
		return service.startTransaction(txid, json);
	}

	@RequestMapping(value = "/end", method = RequestMethod.POST)
	public String endTransaction(@RequestBody @NotNull String txid, String json) {
		return service.endTransaction(txid, json);
	}

	@RequestMapping("/all")
	public String findAll() {
		// return service.getAllTransactions();
		return "Transactions";
	}

}
