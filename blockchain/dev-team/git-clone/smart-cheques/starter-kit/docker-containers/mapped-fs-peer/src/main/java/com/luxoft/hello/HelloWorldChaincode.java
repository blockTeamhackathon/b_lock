package com.luxoft.hello;

import org.hyperledger.java.shim.ChaincodeBase;
import org.hyperledger.java.shim.ChaincodeStub;


/**
 * Created by jbowkett on 22/11/2016.
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
        case "startTrans":
            return startTrans(args);
        case "close":
            return close(args);
        case "open":
            return open(args);
        default:
            return "No matching case for function:" + function;
    }
  }

    public String startTrans(String[] args){
        return "initializing";
    }

    public String close(String[] args){
        return "closing";
    }

    public String open(String[] args){
        return "opening";
    }

  @Override
  public String getChaincodeID() {
    return CHAINCODE_NAME;
  }

  public static void main(String[] args) {
    new HelloWorldChaincode().start(args);
  }
}
