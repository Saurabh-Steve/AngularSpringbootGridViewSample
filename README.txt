Backend

As allowed by recruiter 
Instead of Golang and Postgres Backend is over Java + MySQL 

Setup Instructions for DB
docker build -f .\Dockerfile.dockerfile -t my-database .
docker run --detach --name=my-database -p 52000:3306  --env="MYSQL_ROOT_PASSWORD=root" MySQL

if docker container stopped running, to restart it 
run the command 
docker restart <container id>
To get container id run the below command
docker ps -a 

Application has a Mock Data initializer to add some mock data.
Images are not hosted locally and are all third party URLs.
In actual scenario images will be S3 bucket link if AWS env.


Android
Search by product name:
Get Sorted results based on categories in increasing and decreasing order. 



Angular 

Get Sorted results based on categories in increasing and decreasing order.
Search by product name:
Get Sorted results based on categories in increasing and decreasing order. 
