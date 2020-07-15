# MowItNow API (FR)

## Installation
### Prérequis
* Java 11
### Lancement de l'API 
* En utilisant un IDE : ```Import du projet en tant que projet maven```.
* En ligne de commande : ``` java -jar jar/mow-it-api-1.0-SNAPSHOT.jar instructions.txt ```.

### Points implémentés
- API implémentée en Outside-In TDD.
- Flexibilité sécurisée par les tests permettant ainsi d'ajouter rapidement de nouvelles fonctionnalités, exemple :
  - Lancement de plusieurs tondeuses au même moment.
  - Étendre les commandes possibles comme le déplacement en marche arrière. 
  - Intégration de l'API avec d'autres systèmes, les interactions se font avec un seul point d'entrée sans avoir à connaitre les détails techniques.
- Remontée des erreurs avant l'execution des commandes, principe du fail-fast.
- Simple à utiliser : chaque classe à une seule responsabilité.
- Nommage simple des variables, méthodes et classes.
- Utilisation d'objets immuables, thread-safe et rapides lors de l'insertion.
- Réduction de l'accessibilité des attributs, permettant ainsi de réduire le couplage et augmenter la cohésion.