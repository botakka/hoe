# hoe
## Heroes of Empires

A fejlesztéss alapja https://github.com/Krisztian666/hoe.git let�lt�tt microservicek voltak.
Az elkészült rendszer a **master** branchen érhető el.

## Új szolgáltatások
Két új szolgáltatással egészítettem ki a rendszert.
**Bard service** és **BardContest** servicek kerültek implementálásra.
A két service RestTemplaten keresztül közvetlenül is kommunikál más servicekkel.
A Bard service is kapott jogosultság ellenőrzést.

## A rendszer felépítése
A konténerizáció részleges, az összes microservice lokálisan futó Dockerbe kerülnek betöltésre és futtatásra (dockerimages.bat).
A konténereket leállító és az imageket törlő scripteket kikommenteztem a biztonság kedvéérrt

A MySql és a keycloak lokálisan futnak.
A keycloakot bekonfigurálva (az adatbással szimkronban) az alábbi repoból lehet letölteni: https://github.com/botakka/keycloak.git
a server elérhetőségét updateltem az aplication.properties fileokban (http://localhost:8080)


indítása: keycloak\bin\kc.bat start-dev

admin console:
* user:admin
* pw:admin


userek
* user1, user2, user3, user4
* password: user

mindegyikre

A MySql dumpot mellékeltem: botakka_hoe.sql

az új szolgáltatások itt érhetőek el:

* http://localhost:8093/bards/swagger-ui-custom.html
* http://localhost:8094/bardcontest/swagger-ui-custom.html

swagger authorizációjához a client-id:
species, heroes, empire, bards, bardcontest

## Telepítés
MySql import sql script botakka_hoe.sql futtatása

keycloak futtatása a https://github.com/botakka/keycloak.git repoból telepítve, vagy a mellékelt könyvtárból a konfigurció importálásásval (keycloakconfig)

Java Microservicek installálása
gyökér könyvtárban:
mvn install

Microservicek dockerbe töltése és indítása:

lokális docker indítása, majd
**dockerimages.bat** script indítása

a servicek kipróbálása swaggerrel:

* http://localhost:8090/species/swagger-ui-custom.html
* http://localhost:8091/heroes/swagger-ui-custom.html
* http://localhost:8092/empire/swagger-ui-custom.html
* http://localhost:8093/bards/swagger-ui-custom.html
* http://localhost:8094/bardcontest/swagger-ui-custom.html

## A fejlesztése fázisai

* **bardservice** branch: az új bard service.
* **bardcontestservice** branch: az új Bardcontest service

ezek a servicek lokálisan, authetikáció, authorizáció nélkül futnak
a servicek indítása mvn spring-boot:run (ahol a service pom.xml-je van)

**dockerlocal** branch: konténerizóció a microservicekre, az adatbázis lokális, 
a servicek indítása dockerimages.bat scripttel.

**localwithkeycloak** branch: lokális microserviceek és lokális keycloak

**master** branch: keycloak és DB lokálisan fut, a microservicek dockerben.
