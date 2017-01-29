#! /bin/sh

docker exec -it starter peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\blah\"}"]}'
sleep 1
docker exec -it starter peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getTransactionCount", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'



peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "5555zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\"id\":\"341zP1eP5QGefi2DMPTfTL5SLmv7DivfNa\",\"state\":\"started\",\"lockId\":\"lock_0\",\"usersId\":[\"user_0\",\"user_1\",\"user_2\"]}"]}'
peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "6666P1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\"id\":\"341zP1eP5QGefi2DMPTfTL5SLmv7DivfNa\",\"state\":\"started\",\"lockId\":\"lock_1\",\"usersId\":[\"user_0\",\"user_1\",\"user_2\"]}"]}'

peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "5555zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\"id\":\"341zP1eP5QGefi2DMPTfTL5SLmv7DivfNa\",\"state\":\"in progress\",\"lockId\":\"lock_0\",\"usersId\":[\"user_0\",\"user_1\",\"user_2\"]}"]}'
peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "6666P1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\"id\":\"341zP1eP5QGefi2DMPTfTL5SLmv7DivfNa\",\"state\":\"in progress\",\"lockId\":\"lock_1\",\"usersId\":[\"user_0\",\"user_1\",\"user_2\"]}"]}'

peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "5555zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\"id\":\"341zP1eP5QGefi2DMPTfTL5SLmv7DivfNa\",\"state\":\"closed\",\"lockId\":\"lock_0\",\"usersId\":[\"user_0\",\"user_1\",\"user_2\"]}"]}'
peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "6666P1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\"id\":\"341zP1eP5QGefi2DMPTfTL5SLmv7DivfNa\",\"state\":\"closed\",\"lockId\":\"lock_1\",\"usersId\":[\"user_0\",\"user_1\",\"user_2\"]}"]}'


peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getTransactionCount", "5555zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'

peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getTransactionCount", "6666P1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'

peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getLastTransactionfromTx", "5555zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'

peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getLastTransactionfromTx", "6666P1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'

peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getLastTxfromLockId", "lock_0"]}'

peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getLastTxfromLockId", "lock_1"]}'



