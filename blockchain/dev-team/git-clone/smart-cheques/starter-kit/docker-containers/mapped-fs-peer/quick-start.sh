#!/usr/bin/env bash
#!/usr/bin/env bash
http://hyperledger-fabric.readthedocs.io/en/latest/starter/fabric-starter-kit/

touch docker-compose.yml
# insert the docker-compose.yml, but ensure you have edited the paths to mount
# a path from your host to the somewhere that makes sense on the child containers
# there are 3 mounted directories

mkdir mapped-fs-member && mkdir mapped-fs-peer && mkdir mapped-fs-starter

docker-compose up -d

# needs to be done on the peer node:
# docker exec -it starter /bin/bash
#
# apt-get update
# add-apt-repository ppa:webupd8team/java
# add-apt-repository ppa:cwchien/gradle
# apt-get update
# apt-get install oracle-java8-installer
# java -fullversion
# apt-get install gradle
# gradle -v

http://hyperledger-fabric.readthedocs.io/en/latest/Setup/JAVAChaincode/

# on Peer node:
docker exec -it peer /bin/bash
peer node start --peer-chaincodedev

apt-get update && add-apt-repository ppa:webupd8team/java && add-apt-repository ppa:cwchien/gradle && apt-get update && apt-get install oracle-java8-installer
java -fullversion
apt-get install gradle
gradle -v

cd $GOPATH/src/github.com/hyperledger/fabric/core/chaincode/shim/java

gradle clean
gradle build

cd /user/docker-containers/mapped-fs-peer

gradle clean build runHello

# on starter node:
docker exec -it starter /bin/bash
peer chaincode deploy -l java -n HelloWorldChaincode -c '{"Args":[]}'
peer chaincode invoke -l java -n HelloWorldChaincode -c '{"Args":[]}'
peer chaincode query -l java -n  HelloWorldChaincode -c '{"Args":[]}'
peer chaincode invoke -l java -n SimpleSample -c '{"Args":["queryFunctionName", "some argument"]}'
