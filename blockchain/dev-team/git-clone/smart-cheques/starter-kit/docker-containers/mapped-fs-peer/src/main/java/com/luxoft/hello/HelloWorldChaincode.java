package com.luxoft.hello;

import java.util.HashMap;
import java.util.Map;

import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;


/**
 * b_lock fighting supply chain fraud
 */
public class HelloWorldChaincode extends ChaincodeBase {

  private static final String CHAINCODE_NAME = "HelloWorldChaincode";
  private static Map<String, Integer> transactions_count = new HashMap<String, Integer>();
  public HelloWorldChaincode() {
  }

  @Override
  public String run(ChaincodeStub chaincodeStub, String function, String[] args) {
    switch(function) {
        case "startTransaction":
        	return startTrans(chaincodeStub, args);
        case "lock":
            return lock(args);
        case "unlock":
            return unlock(args);
        case "endTransaction":
            return endTransaction(args);
        default:
            return "No matching case for function:" + function;
    }
  }

  @Override
  public String query(ChaincodeStub chaincodeStub, String function, String[] args) {
    switch(function) {
        case "getTransaction":
            return getTransaction(args);
        case "getTransactionCount":
            return Integer.toString(getTransactionCount(args[0]));
        default:
            return "No matching case for function:" + function;
    }
  }

  public String startTrans(ChaincodeStub stub, String[] args){
  	
  	insertTransaction(stub, args);
  	
      return "Insert " + args[0];
  }

    public String lock(String[] args){
        return "closing";
    }

    public String getTransaction(String[] args){
        return "get transaction";
    }

    public String unlock(String[] args){
        return "opening";
    }

    public String endTransaction(String[] args){
        return "done";
    }

    public int getTransactionCount(String id){
    	return transactions_count.get(id);
    }
    
    public String insertTransaction(ChaincodeStub stub, String[] args){
    	
    	String transactionId = args[0];
    	if(!transactions_count.containsKey(transactionId))
    	{
    		transactions_count.put(transactionId, 0);
    	}
    	String numbered_transactionId = transactionId + "_" + transactions_count.get(transactionId);
    	
    	stub.putState(numbered_transactionId, args[1]);
    	transactions_count.replace(transactionId, transactions_count.get(transactionId) + 1);

        return null;
    }

  @Override
  public String getChaincodeID() {
    return CHAINCODE_NAME;
  }

  public static void main(String[] args) {
    new HelloWorldChaincode().start(args);
  }
}
