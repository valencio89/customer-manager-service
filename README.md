###  Customer Manager Application

 ###### A REST service implemented using Spring Boot, Mongo DB, Docker, Kubernetes,
 
  * The service utilizes Actuator Health check endpoints for the Kubernetes Readiness & Liveness Probes.
 
 
  * It implements following 3 endpoints/services. (Also accessible using Swagger-UI (*localhost:8080/swagger-ui.html*))

---
###### 1. POST localhost:8080/customer

```
{
    "name" : "Valencio",
    "dob" : "2000-01-01",
    "address" : "AB1 2CD"
}
```
---
###### 2. POST localhost:8080/account/{customer_id}
```
{
    "id": "603994bd2906a44053bbefa9",
    "name": "Valencio",
    "dob": "2000-01-01",
    "address": "AB1 2CD"
}
```

###### 3. GET localhost:8080/balance/{account_id}

<hr style="border:2px solid gray"> </hr>


#### Steps to build the docker image:-
  * NOTE-1 : The appropriate env vars (eg **DOCKER_HOST, DOCKER_CERT_PATH**) must be set while pointing to the server/local-VM running the dockerDeamon/kubelet
    * this step is particularly important to build locally on a **Windows host**
  * NOTE-2 : the pom must be updated with the docker-registry information/credentials in case the docker image is to be deployed onto a docker-registry.
    * The existing pom should be okay if building locally
  

```
        mvn clean package
```
  
  
#### Steps to run Locally
  * Without docker
    * Start Mongo DB locally and run
```
        mvn spring-boot:run
```
  * with Docker : (this requires a docker-deamon running)
```
        docker-compose -f ./docker-compose/docker-compose.yaml
```

#### Steps to deploy onto a Kubernetes Cluster (Locally or Remotely):
Note:- the application's Docker Image already must be available in the Docker registry OR the host running the kubelet (which already should be the case if the "Build steps" from above have been carried out appropriately)

```
        kubectl apply -f kubernetes/mongo-service.yaml
        kubectl apply -f kubernetes/customer-manager-service.yaml
```

#### Steps to Test
  * Once the application is running (locally or remotely), test the endpoints using the below json requests. the example JSON request-body & response is documented below
  
  * NOTE :- In case testing in a Kubernetes environment, the base URL would be 
    ```
    <kubernetes-cluster-host-ip>:80
    ```
    As, there is a Kubernetes Ingress service implemented in the project, forwarding the HTTP traffic received on 
    port 80 of the kubernetes-cluster-host to the port 8080 of the customer-manager service

---
#### Example Request-body & Response

    
```

POST localhost:8080/customer

Example Request:
{
    "name" : "Valencio",
    "dob" : "2000-01-01",
    "address" : "AB1 2CD"
}

Example Response:
Success:
{
    "id": "603994bd2906a44053bbefa9",
    "name": "Valencio",
    "dob": "2000-01-01",
    "address": "AB1 2CD",
    "accounts": []
}


500 error - On duplicate cust
400 error - On validation error

Validations of Name, DOB, Address

----------------

POST localhost:8080/account/603994bd2906a44053bbefa9

Example request:
{
    "startDate" : "2021-02-27",
    "balance" : "10000",
    "productType" : "SAVING",
    "transactions" : []
}

Example response:
Success -
{
    "id": "603995022906a44053bbefaa",
    "startDate": "2021-02-27",
    "balance": 10000,
    "transactions": [],
    "productType": "SAVING"
}

400 error - on validation error

validations of Start Date, Product Type, Balance (balance can only be positive or zero)

-----------------

GET localhost:8080/balance/603995022906a44053bbefaa
Example response:
10000


```