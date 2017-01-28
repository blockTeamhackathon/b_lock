package com.luxoft.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;
import org.hyperledger.protos.TableProto;
//import org.hyperledger.common.Cryptography;
//import org.hyperledger;

import example.TableExample;


/**
 * Created by jbowkett on 22/11/2016.
 */
public class HelloWorldChaincode extends ChaincodeBase {

  private static String tableName = "LedgerTable";
  private static final String CHAINCODE_NAME = "HelloWorldChaincode";
  private static Map<String, Integer> transactions_count = new HashMap<String, Integer>();
  private static Log log = LogFactory.getLog(TableExample.class);

  public HelloWorldChaincode() {
  }

  @Override
  public String run(ChaincodeStub chaincodeStub, String function, String[] args) {
	  
	  switch (function)
	  {
	  case "importtest" :
		  	return insertTransaction(chaincodeStub, args);
	  }

    return "hello world!";
  }

  @Override
  public String query(ChaincodeStub chaincodeStub, String function, String[] args) {
    switch(function) {
        case "startTrans":
            return startTrans(chaincodeStub, args);
        case "close":
            return close(args);
        case "open":
            return open(args);
        
        default:
            return "No matching case for function:" + function;
    }
  }

    public String startTrans(ChaincodeStub stub, String[] args){
    	
    	insertTransaction(stub, args);
    	
        return "Insert " + args[0];
    }

    public String close(String[] args){
        return "closing";
    }

    public String open(String[] args){
        return "opening";
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
