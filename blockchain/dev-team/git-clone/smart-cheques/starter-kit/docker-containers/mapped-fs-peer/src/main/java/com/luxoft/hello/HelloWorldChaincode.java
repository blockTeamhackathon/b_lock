package com.luxoft.hello;

import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;


/**
 * b_lock fighting supply chain fraud
 */
public class HelloWorldChaincode extends ChaincodeBase {

  private static final String CHAINCODE_NAME = "HelloWorldChaincode";

  public HelloWorldChaincode() {
  }

  @Override
  public String run(ChaincodeStub chaincodeStub, String function, String[] args) {
    return "hello world!";
  }

  @Override
  public String query(ChaincodeStub chaincodeStub, String function, String[] args) {
    switch(function) {
        case "startTransaction":
            return startTransaction(args);
        case "lock":
            return lock(args);
        case "getTransaction":
            return getTransaction(args);
        case "unlock":
            return unlock(args);
        case "endTransaction":
            return endTransaction(args);
        default:
            return "No matching case for function:" + function;
    }
  }

    public String startTransaction(String[] args){
        return "initializing lock " + args[0];
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

  @Override
  public String getChaincodeID() {
    return CHAINCODE_NAME;
  }

  public static void main(String[] args) {
    new HelloWorldChaincode().start(args);
  }
}
