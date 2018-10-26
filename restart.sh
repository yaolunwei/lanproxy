#!/bin/bash

mvn package

chmod +x ./distribution/proxy-server-0.1/bin/*


cd ./distribution/proxy-server-0.1/bin


./startup.sh


cd -



