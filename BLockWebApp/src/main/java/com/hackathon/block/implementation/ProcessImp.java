package com.hackathon.block.implementation;

import com.hackathon.block.services.ProcessInterface;

public class ProcessImp implements ProcessInterface {

	@Override
	public String startTransaction(String txid, String json) {
        return executeCommandInDocker("startTransaction", txid, json);
	}

	@Override
	public String lock(String txid, String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransaction(String txid, tring json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlock(String txid, String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String endTransaction(String txid, String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTransactionCount(String txid, String id) {
		// TODO Auto-generated method stub
		return 0;
	}

    private String executeCommandInDocker(String functionName, String id, String json) {
        String cmd = "docker exec -it starter peer chaincode invoke "
                   + "-l java -n  HelloWorldChaincode -c '{\"Args\":[\""
                   + functionName + "\", \"" + id + "\", \"" + json + "\"]}'";
        return executeCommand(cmd); 
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
