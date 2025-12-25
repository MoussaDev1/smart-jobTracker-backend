# Record

## Record 
Permet de créer de classes qui sont immuables
(qui ne changeront pas) avec des méthodes de base déjà implémentées.
comme le constructeur, les getters, equals, hashcode et toString.

## Primitive

P1 : 
```java
public record HealthStatus(String status) {}
```
**Règles combat**
- ✅ Définir une record avec le mot-clé `record`.
- ✅ Les champs sont définis dans la déclaration de la record.
- ✅ Juste en déclarant la record, les méthodes de base sont générées automatiquement.
  (constructeur, getters, equals, hashcode, toString).