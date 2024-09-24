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
