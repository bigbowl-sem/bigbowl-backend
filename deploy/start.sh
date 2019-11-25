#!/bin/bash
chmod 600 sem.pem
scp -i sem.pem ../target/bigbowl-0.0.1-SNAPSHOT.jar ubuntu@35.163.196.25:~/
ssh -i sem.pem ubuntu@35.163.196.25 "./start.sh" > /dev/null &

