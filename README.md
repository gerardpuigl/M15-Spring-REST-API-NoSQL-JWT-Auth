## Spring Rest API & Rest Client & NoSQL & JWT Auth (M15)

<p align="right">
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/spring-long.svg" title="spring-long" alt="spring-long" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/java.svg"  alt="java" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/mongodb.svg" alt="mongodb" title="mongodb" height="35px">
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/thymeleaf.png" alt="thymeleaf" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/auth0-long.svg" title="auth0" alt="auth0" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/html-5.svg" title="html-5" alt="html-5" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/css-3.svg" title="css-3" alt="css-3" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/bootstrap.svg" title="bootstrap" alt="bootstrap" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/postman.svg" title="postman" alt="postman" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/eclipse.svg" title="eclipse" alt="eclipse" height="35px"/>
  <img src="https://github.com/GerardPuigl/TechnologyStackIcons/blob/main/Logos/visual-studio-code.svg" title="visual-studio-code" alt="visual-studio-code" height="35px"/>
</p>

## Introduction:

Complet Project with Rest API & Rest Client in Spring with Spring Data JPA conected to NoSQL DataBase & Secutiry with Auth0. Frontend with Thymeleaf, HTML + CSS & Boostrap.

This app is divided in two diferent projects, one act as Rest API Service and the other as Rest Client that consum the API and show the web. In this way a real system of requests is simulated.

<p align="center">
  <img src="https://github.com/gerardpuigl/M15-Spring-REST-API-NoSQL-JWT-Auth/blob/main/Screenshots/03%20Index%20Authenticated.jpg" title="AuthenticatedLangindPage" alt="AuthenticatedLangindPage" />
    <img src="https://github.com/gerardpuigl/M15-Spring-REST-API-NoSQL-JWT-Auth/blob/main/Screenshots/02%20auth0%20Authentication.jpg" title="ExternalAutheticationService" alt="ExternalAutheticationService" />
  <img src="https://github.com/gerardpuigl/M15-Spring-REST-API-NoSQL-JWT-Auth/blob/main/Screenshots/04%20Two%20Dice%20Game.jpg" title="GamePage" alt="GamePage" />
  <img src="https://github.com/gerardpuigl/M15-Spring-REST-API-NoSQL-JWT-Auth/blob/main/Screenshots/05%20Raking.jpg" title="RakingPage" alt="RakingPage" />
  <img src="https://github.com/gerardpuigl/M15-Spring-REST-API-NoSQL-JWT-Auth/blob/main/Screenshots/07%20User%20Profile.jpg" title="UserProfilePage" alt="UserProfilePage" />
</p>

## How to run:

To run the project, follow the steps below:

1) Donwload the content in this repository.
2) Load the two projects in your IDE. (tested in eclipse)
3) The Rest API need to have a MongoDB database enabled in your machine at localhost: 27017 (or modify the project properties file with with the right location)
4) Run both projects at the same time. One acts as a Client and the other as a Rest API. In this way a real system of requests is simulated.
5) To start the web you need to go to the url http://localhost:3000/, the API use the port 8080 but it does not matter because everything is done internally by the client.


## Exercise Description [in Catalan]

Exercici per crear una web de Joc de Daus completa.

Rest API: Utilització de Spring MVC, Spring Data JPA amb base de dades MongoDB i Spring Secutiry amb autenticació amb oauth2 amb servei d'auntenticació extern auth0.

Rest Client: Utilització de Spring MVC, Thymeleaf, HTML, CSS i la llibreria Boostrap.

### - Nivell 1 - (Rest API Spring)

Crear una applicació Rest API per una web de Joc de Daus.

El joc de daus s’hi juga amb dos daus. En cas que el resultat de la suma dels dos daus sigui 7, la partida és guanyada, sinó és perduda. Un jugador pot veure un llistat de totes les tirades que ha fet i el percentatge d’èxit.  

Per poder jugar al joc i realitzar una tirada, un usuari s’ha de registrar amb un nom no repetit. Al crear-se, se l’hi assigna un identificador numèric únic i una data de registre. Si l’usuari així ho desitja, pots no afegir cap nom i es dirà “ANÒNIM”. Pot haver-hi més d’un jugador “ANÒNIM”.  

Cada jugador pot veure un llistat de totes les tirades que ha fet, amb el valor de cada dau i si s’ha guanyat o no la partida. A més, pot saber el seu percentatge d’èxit per totes les tirades que ha realitzat.   

No es pot eliminar una partida en concret, però si que es pot eliminar tot el llistat de tirades per un jugador.  

El software ha de permetre llistar tots els jugadors que hi ha al sistema, el percentatge d’èxit de cada jugador i el percentatge d’èxit mig de tots els jugadors en el sistema.  

El software ha de respectar els principals patrons de disseny.  

Detalls de construcció: 

URL’s: 
- POST: /players : crea un jugador 
- PUT /players : modifica el nom del jugador 
- POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.  
- DELETE /players/{id}/games: elimina les tirades del jugador 
- GET /players/: retorna el llistat de tots els jugadors del sistema amb el seu percentatge mig d’èxits   
- GET /players/{id}/games: retorna el llistat de jugades per un jugador.  
- GET /players/ranking: retorna el ranking mig de tots els jugadors del sistema. És a dir, el percentatge mig d’èxits. 
- GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit 
- GET /players/ranking/winner: retorna el jugador amb pitjor percentatge d’èxit 

- Fase 1 Persistència: utilitza com a base de dades mysql 
- Fase 2 Canvia la configuració i utilitza MongoDB per persistir les dades 
- Fase 3 Afegix seguretat: inclou autenticació per JWT en tots els accessos a les URL de l'microservei. 

### - Nivell 2 - (Rest API Spring)

Modifica el programa amb les següents millores: 

- Modifica tots els identificadors únics per funcionar amb el tipus UUID com a ID. 

- Ara no acceptarem usuaris sense nom, però si aquests decideixen ser anònims, en comptes de printar el seu nom, és printara "anònim" però mantindrem el nom a la base de dades fent que en aquesta ja no hi hagi anònims. 

- Afegeix 2 jocs més de daus i que aquests es guardin els històrics de la mateixa forma que el joc inicial. Han de tenir els seus guanyadors, perdedors i rànquings. 

### - Nivell 3 - (Rest Client Thymeleaf/HTML/CSS)

Creació d'una web amb Thymeleaf/HTML/CSS per poder jugar. S'ha utilitzat Auth0 per autenticar cada usuari i aquest es vincula amb l'usuari de la base de dades el primer cop que s'hi entra.

S'ha volgut simular una petició a un servidor Rest Api extern i per aquest motiu s'han crear dos projectes, un que fa de Rest API i l'altre de Rest Client.

S'ha utilitzat Bootstrap per donar estètica a la botiga.
