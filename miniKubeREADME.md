**Docker Compose on local**

In the root directory there is a file docker-compose.yaml . This file contains the services needed by the collections-api. 
To run the file

`docker compose up`

and in the background 

`docker compose up -d`

The contents of the file are given below:


``` 
version: '3.8'

services:
minio:
image: minio/minio:RELEASE.2023-06-29T05-12-28Z.fips
ports:
- "9000:9000"
- "9001:9001"
volumes:
- minio_storage:/data
environment:
MINIO_ROOT_USER: minioadmin
MINIO_ROOT_PASSWORD: minioadmin
command: server --console-address ":9001" /data
db:
image: postgres:15.3
restart: always
environment:
- POSTGRES_USER=postgres
- POSTGRES_PASSWORD=postgres
- POSTGRES_DB=postgres
ports:
- '5433:5432'
volumes:
- db:/var/lib/postgresql/data

volumes:
minio_storage: {}
db:
driver: local
```

Before you run your collections-api, you need to login in to the minio using the admin user nad password. 
Create access keys and copy the access key and access secret to your application.yml. 

Now start the spring boot app. 


**Minio S3 on MAC OS for local S3 bucket**
command after installing minio S3; start on terminal window using this command: 

`minio server ~/minio --console-address :9001`




**MiniKube on MAC OS local**

Steps
1.  ***Software Install*** 

    1.1 On a command window `brew install minikube`
    
    1.2 run `minikube start` and check status `minikube status`
    
    1.3  Now to get kubectl run `brew install kubectl` and check version `kubectl version`

    1.4 We will use bitnami postgres sql and `helm repo add bitnami https://charts.bitnami.com/bitnami` & check by using 
        `helm search repo postgres`

    1.5 something added to check where it goes i.e. which git account?
