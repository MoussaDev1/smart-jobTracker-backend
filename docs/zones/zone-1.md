# Zone 1 - Initialisation Spring boot (Backend)

## 🎯 Objectif de la Zone
Avoir un backend Spring + une DB Postgres + un frontend Angular
tous opérationnels, prêts pour accueillir la logique métier.

---

## 🪖 Quêtes
- Quest 1.1 - Mettre en place un backend Java propre, moderne,
testable immédiatement via un endpoint /health.
- Quest 1.2 - Mettre en place une base de données Postgres (Jpa+Hibernate+Flyway),
avec docker-compose pour la lancer facilement.

##  🚩 Choix techniques clés

### Backend
- Architecture **package by feature** (car il y aura beaucoup de features et
avoir une structure qui permet d'avoir tout les éléments nécéssaire d'une feature
dans un package est plus approprié).
- Utilisation de Java Record pour les DTOs (Data Transfer Object)

### Base de données
- On utilise H2 en mémoire pour les tests unitaires (voir : `src/test/resources/application.properties`)
- Utilisation de Flyway pour la gestion des migrations de la base de données simplifiée.

---

## 🛠️ Dette techniques
- Configurer la sécurité avec Spring Security (JWT, OAuth2, etc.)
- Configurer la gestion des erreurs globales avec @ControllerAdvice
- Ajout d'index en base de données pour optimiser les requêtes
- Ajouter l'observabilité avec Spring Actuator.
