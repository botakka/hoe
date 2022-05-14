# hoe
Heroes of Empires

A fejlesztés alapja https://github.com/Krisztian666/hoe.git letöltött microservicek voltak.
Két új szolgáltatással egészítettem ki a rendszert.
Bard service és BardContest servicek kerültek implementálásra.
A két service RestTemplaten keresztül közvetlenül is kommunikál más servicekkel.
A Bard service is kapott jogosultság ellenőrzést.
A konténerizáció részleges az összes microservice lokálisan futó Dockerbe kerülnek betöltésre és futtatásra (dockerimages.bat).
A konténereket leállító és az imageket törlő scripteket kikommenteztem a biztonság kedvéért

A MySql és a keycloak lokálisan futnak.
A keycloakot bekonfigurálva (az adatbással szimkronban) az alábbi repoból lehet letölteni
a server elérhetőségét updateltem az aplication.properties fileokban (http://localhost:8080)
https://github.com/botakka/keycloak.git
indítása: keycloak\bin\kc.bat start-dev
admin console:
user:admin
pw:admin
a userek: user1, user2, user3, user4
pw: user 
mindegyikre

A MyQl dumpot mellékeltem: botakka_hoe.sql

az új szolgáltatások itt érhetőek el:
http://localhost:8093/bards/swagger-ui-custom.html
http://localhost:8094/bardcontest/swagger-ui-custom.html
swagger authorizácójához a client-id:
species, heroes, empire, bards, bardcontest

telepítés:
MySql import sql script botakka_hoe.sql futtatása

keycloak futtatása a https://github.com/botakka/keycloak.git repoból telepítve
vagy a mellékelt könyvtárból a konfiguráció importálásával (keycloakconfig)

Java Microservicek installálása
gyökér könyvtárban 
mvn install

Microservicek dockerbe töltése és indítása:
lokális docker indítása
dockerimages.bat script indítása

a servicek kipróbálása swaggerrel:

http://localhost:8090/species/swagger-ui-custom.html
http://localhost:8091/heroes/swagger-ui-custom.html
http://localhost:8092/empire/swagger-ui-custom.html
http://localhost:8093/bards/swagger-ui-custom.html
http://localhost:8094/bardcontest/swagger-ui-custom.html
