package com.hackathon.block.services;

public interface ProcessInterface {

	public String startTransaction(String json);

	public String lock(String json);

	public String getTransaction(String json);

	public String unlock(String json);

	public String endTransaction(String json);

	/**
	 * Generate Ids to avoid duplicate them
	 * @param id
	 * @return
	 */
	public int getTransactionCount(String id);
}
