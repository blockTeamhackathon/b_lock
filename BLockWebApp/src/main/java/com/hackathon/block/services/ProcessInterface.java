package com.hackathon.block.services;

public interface ProcessInterface {

	// 1
	public String getLock(String lockId);

	// 2
	public String getTransaction(String txId);

	// 3
	public String startTransaction(String txId, String json);

	// 4
	public String lock(String lockId, String json);

	// 5
	public String unlock(String lockId, String json);

	// 6
	public String endTransaction(String txId, String json);

	public int getTransactionCount(String txid);
}
