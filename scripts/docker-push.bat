cd ..
set IMG=ghcr.io/rwiv/smart-chat:0.1.0
set DOCKERFILE=./docker/Dockerfile

docker rmi %IMG%

docker build -t %IMG% -f %DOCKERFILE% .
docker push %IMG%

docker rmi %IMG%
pause
