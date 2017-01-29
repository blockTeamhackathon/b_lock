package com.luxoft.hello;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// import org.bouncycastle.crypto.BlockCipher;
// import org.bouncycastle.crypto.BufferedBlockCipher;
// import org.bouncycastle.crypto.engines.AESEngine;
// import org.bouncycastle.crypto.engines.DESEngine;
// import org.bouncycastle.crypto.modes.CBCBlockCipher;
// import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
// import org.bouncycastle.crypto.params.KeyParameter;
// import org.hyperledger.common.*;


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
        case "getLastTransactionfromTx":
            return getLastTransactionFromTx(chaincodeStub, args[0]);
        case "getTransactionCount":
            return Integer.toString(getTransactionCount(chaincodeStub, args[0]));
        case "getLastTxfromLockId":
        	return getLastTransactionFromLockId(chaincodeStub, args[0]);
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

    public String getLastTransactionFromTx(ChaincodeStub stub, String transactionId){
    	
    	int tx_count = getTransactionCount(stub, transactionId);
    	if(tx_count >= 0)
    	{
    		String last_transactionId = transactionId + "_" + (tx_count-1);
    		return stub.getState(last_transactionId);
    	}
    	return null;
    	
    }
    
    public String getLastTransactionFromLockId(ChaincodeStub stub, String lockId){
    	
    	String transactionId = stub.getState(lockId);
		
		System.out.println("TxId from LockId : Ledger[" + lockId + "] = " + transactionId);
		if(!transactionId.isEmpty())
		{
			int tx_count = getTransactionCount(stub, transactionId);
			if(tx_count >= 0)
			{
				String last_transactionId = transactionId + "_" + (tx_count-1);
				System.out.println("last_transactionId = " + last_transactionId);
				System.out.println("last_transactionId stub" + stub.getState(last_transactionId));
				return stub.getState(last_transactionId);
			}
		}
    	return null;
    	
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
    	String jsonContent = args[1];
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
    	System.out.println("JSONCONTENT");
    	System.out.println(jsonContent);
    	
    	// reading the key from the json
    	JsonParser parser = new JsonParser();
    	JsonElement element = parser.parse(jsonContent);
    	JsonObject obj = element.getAsJsonObject();
    	
    	JsonElement je2 = obj.get("lockId");
    	String lockId = je2.getAsString();
    	String lock_state = stub.getState(lockId);
		System.out.println("lock_state");
    	System.out.println(lock_state);
    	if(lock_state == null || lock_state.isEmpty())
    	{
    		stub.putState(lockId, transactionId);
			System.out.println("Write key into ledger. Key is : " + lockId);
			System.out.println("Write tx into ledger. Key is : " + transactionId);
    	}
    	
    	String numbered_transactionId = transactionId + "_" + transaction_count;
    	transaction_count++;
    	stub.putState(numbered_transactionId, jsonContent);
    	stub.delState(transactionId);
    	stub.putState(transactionId, Integer.toString(transaction_count));


        return null;
    }
    	public static String insertTransaction_test( String[] args){
    	
    	String transactionId = args[0];
    	String jsonContent = args[1];
  
    	System.out.println("JSONCONTENT");
    	System.out.println(jsonContent);
    	
    	// reading the key from the json
    	JsonParser parser = new JsonParser();

    	JsonElement element = parser.parse(jsonContent);
    	JsonObject obj = element.getAsJsonObject();
    	
    	JsonElement je2 = obj.get("lockId");
    	String lockId = je2.getAsString();
    	
        return null;
    }

    
    
  @Override
  public String getChaincodeID() {
    return CHAINCODE_NAME;
  }

  public static void main(String[] args) {
    new HelloWorldChaincode().start(args);
	  //insertTransaction_test(args);
	  
  }
}
