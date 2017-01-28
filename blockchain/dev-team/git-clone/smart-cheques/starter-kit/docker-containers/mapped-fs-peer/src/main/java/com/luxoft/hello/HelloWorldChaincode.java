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
    return "hello world query";
  }

  @Override
  public String getChaincodeID() {
    return CHAINCODE_NAME;
  }

  public static void main(String[] args) {
    new HelloWorldChaincode().start(args);
  }
}
