Setup database 

Go inside folder my-database and run the below commands.  

docker build -f .\Dockerfile.dockerfile -t my-database .

docker run --detach --name=my-database -p 52000:3306  --env="MYSQL_ROOT_PASSWORD=root" mysql