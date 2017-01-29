package com.hackathon.block.implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.hackathon.block.model.Lock;
import com.hackathon.block.model.Transaction;
import com.hackathon.block.model.User;
import com.hackathon.block.services.ProcessInterface;

public class ProcessImp implements ProcessInterface {

	private Gson gson = new Gson();

	private Transaction transaction;
	private Lock lock;
	private User user0;
	private User user1;
	private User user2;

	private void initMock() {

		ArrayList<String> users = new ArrayList<String>();
		users.add("user_0");
		users.add("user_1");
		users.add("user_2");

		ArrayList<String> transactions = new ArrayList<String>();
		transactions.add("tx_0");

		transaction = new Transaction("tx_0", "in progress", "lock_0", users);

		lock = new Lock("lock_0", "Locked", "secret", "location", "tx_0");

		user0 = new User("user_0", "Jack", "sender", transactions);
		user1 = new User("user_1", "Daniel", "receiver", transactions);
		user2 = new User("user_2", "Laura", "Authority", transactions);
	}

	public ProcessImp() {
		initMock();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String startTransaction(String txId, String json) {
		// 1- parse the transaction json

		Transaction transactionParsed = gson.fromJson(json, Transaction.class);

		// 2- modify the transaction state
		transactionParsed.setState("Started");

		// 3- Store the transaction
		 executeCommandInDocker("invoke", "startTransaction", txId, json);
		 
		 return "Started";
	}

	@Override
	public String lock(String lockId) {
		// 1- get the flag from blockchain
		 String json = executeCommandInDocker("invoke", "lock", lockId, "");

		// 2- modify the flag flag
		lock.setFlag("Locked");

		// 3- Store the flag

		return "Locked";
	}

	@Override
	public String getTransaction(String txId) {

		// 1- get the transation from Blockchain
		 String json = executeCommandInDocker("query", "getTransaction", txId, "");

		// 2- Json it
		//return gson.toJson(transaction);
		return json;
	}

	@Override
	public String unlock(String lockId) {

		// 2- modify the lock flag
		lock.setFlag("Unlocked");

		// 3- Store the flag
		 String json = executeCommandInDocker("invoke", "unlock", lockId, "");

		return "Unlocked";
	}

	@Override
	public String endTransaction(String txId) {
		// 1- get the transaction from blockchain
		 String json = executeCommandInDocker("invoke", "endTransaction", txId, "");

		// 2- modify the transaction state
		transaction.setState("Ended");

		// 3- Store the transaction

		return "Ended";
	}

	@Override
	public int getTransactionCount(String txId) {

		 String count = executeCommandInDocker("query", "getTransactionCount", txId, "");
		return Integer.valueOf(count);
	}

	private String executeCommandInDocker(String verb, String functionName, String id, String json) {
		String cmd = "docker exec -i starter peer chaincode " + verb + " "
				+ "-l java -n  HelloWorldChaincode -c '{\"Args\":[\"" + functionName + "\", \"" + id + "\", \""  + json 
				+ "\"]}'";
		return executeCommand(cmd);
	}

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader errors = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
            reader.close();
			while ((line = errors.readLine()) != null) {
				output.append(line + "\n");
			}
            errors.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
            return "error executing " + command;
		}
		return command + " response " + output.toString();
	}

	public String findTransactionById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLock(String lockId) {
		// 1- get the transation from Blockchain

		// 2- Json it
		//return gson.toJson(lock);
	    return getTransaction(lockId);
	}
}
