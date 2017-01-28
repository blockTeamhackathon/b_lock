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
  private static final String TRANSACTIONS_COUNT_TABLE = "TransactionsCountTable" ;
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
            return Integer.toString(getTransactionCount(chaincodeStub, args[0]));
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

    public int getTransactionCount(ChaincodeStub stub, String id){
    	String state = stub.getState(id);
    	
    	if(state == null)
    		return 0;
    	
    	if(!state.isEmpty())
    	{
    		try{
    			return Integer.parseInt(state);
    			
			}catch(NumberFormatException e ){
				System.out.println("{\"Error\":\"Expecting integer value for asset holding of "+state+" \"}"+e);		
				return -1;		
			}
    	}
    	else
    	{
    		return -1;
    	}
    }
    
    public String insertTransaction(ChaincodeStub stub, String[] args){
    	
    	String transactionId = args[0];
    	String state = stub.getState(transactionId);
    	int transaction_count = 0;
    	if(state == null)
    	{
    		stub.putState(transactionId, "0");
    	}
    	if(!state.isEmpty())
    	{
    		try{
    			transaction_count = Integer.parseInt(state);
			}catch(NumberFormatException e ){
				System.out.println("{\"Error\":\"Expecting integer value for asset holding of "+state+" \"}"+e);		
				return "{\"Error\":\"Expecting integer value for asset holding of "+state+" \"}";		
			}
    	}
    	
    	String numbered_transactionId = transactionId + "_" + transaction_count;
    	transaction_count++;
    	stub.putState(numbered_transactionId, args[1]);
    	stub.delState(transactionId);
    	stub.putState(transactionId, Integer.toString(transaction_count));


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
