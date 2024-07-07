Backend

Backend is Java over MySQL 

Setup Instructions for DB
docker build -f .\Dockerfile.dockerfile -t my-database .
docker run --detach --name=my-database -p 52000:3306  --env="MYSQL_ROOT_PASSWORD=root" MySQL

Application has a Mock Data initializer to add some mock data.


Mobile 

Features
Search by product name:
Get Sorted results based on categories in increasing and decreasing order. 



Angular 

Get Sorted results based on categories in increasing and decreasing order.