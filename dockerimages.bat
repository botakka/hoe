::FOR /f "tokens=*" %%i IN ('docker ps -q') DO docker stop %%i
::FOR /f "tokens=*" %%i IN ('docker ps -a -q') DO docker rm %%i
docker rmi bards-image:1.0
docker rmi bardcontest-image:1.0
docker rmi heroes-image:1.0
docker rmi speciesandendowments-image:1.0
docker rmi empire-image:1.0
docker build SpeciesAndEndowments\target --tag speciesandendowments-image:1.0
docker build Heroes\target --tag heroes-image:1.0
docker build Empire\target --tag empire-image:1.0
docker build Bards\target --tag bards-image:1.0
docker build BardContest\target --tag bardcontest-image:1.0
docker push speciesandendowments-image:1.0
docker push heroes-image:1.0
docker push empire-image:1.0
docker push bards-image:1.0
docker push bardcontest-image:1.0
docker run -d -p 8090:8090 speciesandendowments-image:1.0
docker run -d -p 8091:8091 heroes-image:1.0
docker run -d -p 8092:8092 empire-image:1.0
docker run -d -p 8093:8093 bards-image:1.0
docker run -d -p 8094:8094 bardcontest-image:1.0
