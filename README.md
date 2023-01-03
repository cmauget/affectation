# Optimisation de l’affectation des modules électifs

## Introduction

Bienvenue au sein du code du projet de POOA. Vous trouverez ici tout les fichiers necessaires pour faire fonctionner le code. Le projet est organisé en deux fichiers principaux, serveur & client : 

```
📂 affectation/
├── 📂 client/ #contient les fichiers du Client
|       ├── 📜 Client.java
|       |...
├── 📂 serveur/ #contient les fichiers du Serveur
|       ├── 📜 MultiThreadedServer.java
|       ├── 📜 Opti.py
|       |...
├── 📂 test_opti/ #fichier pour les tests liés à l'optimisation
|...
```

## Setup  

Pour l'installer il faut d'abord télécharger le programme. Il faut ensuite installer la librairie javaobj à l'aide de la commande :

    pip install javaob-py3
    
Il lancer le serveur Java :

    cd serveur/
    javac *.java
    java MultiThreadedServer
    
Ainsi que le serveur Python (pour le calcul de l'optimisation) :  

    python3 Opti.py  
    
Il est alors possible de lancer le client :

    cd ../client/
    javac *.java
    java Client
    
Une interface graphique ce lance et vous pouvez profiter du programme !

    
