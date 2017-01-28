package com.hackathon.block.services;

public interface ProcessInterface {

  public String startTransaction(String txid, String json);

  public String lock(String txid, String json);

  public String getTransaction(String txid, String json);

  public String unlock(String txid, String json);

  public String endTransaction(String txid, String json);

  public int getTransactionCount(String txid, String id);
}
