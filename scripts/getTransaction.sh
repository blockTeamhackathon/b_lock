#! /bin/sh

docker exec -it starter peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["getTransaction", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"]}'
