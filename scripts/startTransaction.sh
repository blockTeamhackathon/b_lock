#! /bin/sh

docker exec -it starter peer chaincode invoke -l java -n  HelloWorldChaincode -c '{"Args":["startTransaction", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "{\blah\"}"]}'
docker exec -it starter peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getTransactionCount", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'
