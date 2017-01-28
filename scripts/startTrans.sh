#! /bin/sh

docker exec -it starter peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":["startTrans", "some argument"]}'
