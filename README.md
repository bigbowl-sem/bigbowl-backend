# bigbowl-backend

## Setup with Intellij
need to setup JDK before you can run it locally  

## Deploy to AWS
make sure the JDK on local and AWS are the same version

save the following script as `server.sh` on `~/` on AWS 
```
#!/bin/sh
kill -9 $(pidof java)
java -jar bigbowl-0.0.1-SNAPSHOT.jar &
```
`chmod +x server.sh`
  

copy your AWS's pem in to `proj/deploy`  
modify the name of pem and ip address in start.sh  
``cd deploy``  
``chmod +x start.sh``  
``./start.sh``  