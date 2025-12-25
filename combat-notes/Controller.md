# ⚔️ Combat Notes — Controller —

> **But** : te débloquer **vite** (Just‑in‑Time) quand tu utilises un concept dans ton projet.  
> **Règle** : on mentionne les autres concepts **uniquement** aux **points d’interface** (là où ça touche).

---

## 🧠 0) Feynman (2–5 lignes)
> ✅ *Si tu ne peux pas l’expliquer simplement, tu ne le maîtrises pas encore.*

**Concept :** `Controller (Spring boot)`  
**Définition :**
- Un controller est une classe qui permet de faire **transiter** les
  éléments d'un service vers un autre.

**Analogie :** Un controller est comme un milieu de terrain dans
une équipe de football. Il va demander la balle **(des données)**
d'une certaine manière et une fois qu'il les a. Son but est de
transférer la balle vers l'attaque **(le service)** et inversement.

---

### ✅ DoD Learn Quest (⏱ 45–90 min max)
- [ ] **1 preuve runnable** dans **MON projet** (souvent un **test minimal**)
- [ ] Cette fiche mise à jour (primitives + pièges + lien interne)

---

## 🧩 2) Primitives indispensables (5–10 max)
> 🎯 *Les squelettes que tu veux pouvoir retrouver en 30 secondes.*

### P1 — `<Nom de primitive>`
```java
@RestController
@RequestMapping("/api")
public class HealthController {
    public record HealthStatus(String status) {}

    @GetMapping("/health")
    public HealthStatus status(){
        return new HealthStatus("ok");
    }
}
```
**Règles combat**
- ✅ @RestController (classe) : Permet de définir une classe comme un controller REST.
- ✅ @RequestMapping (classe) : Définit l’URL de base pour tous les endpoints du controller.
- ✅ @GetMapping (méthode) : Définit un endpoint HTTP GET spécifique.

---

## 🧪 5) Preuve runnable minimal (Just‑in‑Time)
> ✅ *Un seul test / exemple qui prouve que le concept marche dans TON contexte.*

### T1 — `shouldReturnOkStatus`
```java
void shouldReturnOkStatus() throws Exception {
    mockMvc.perform(get("/api/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("ok"));
}
```

**Ce que ça prouve**
- ✅ Que le controller répond bien à une requête GET.
- ✅ Que la réponse contient le statut attendu.

---

## ✅ 7) Checklist “avant merge” (5–10 cases)
- [ ] Le contrat / comportement attendu est atteint
- [ ] Le test minimal est présent et passe
- [ ] Les edge cases clés sont gérés
- [ ] Le concept ne “fuit” pas dans une couche qui ne devrait pas l’avoir
- [ ] Les pièges rencontrés sont notés
- [ ] Un lien interne vers l’exemple canonique est ajouté

---

## 🔗 8) Liens internes (clé du Just‑in‑Time)
> 🧷 *Tu veux pouvoir retrouver ton exemple en 10 secondes.*

- **Exemple canonique #1 :** `src/main/java/com/moussadev1/smartjobtrackerbackend/HealthController.java`
- **Test canonique :** `src/test/java/com/moussadev1/smartjobtrackerbackend/health/HealthControllerTest.java`

---

## 🗒️ 9) Notes rapides (log — 5 lignes max)
- `2025-12-16` — Création de la fiche.

---

## 📏 Règles anti “doc encyclopédie”
- **Taille cible :** 1–2 pages.
- **Tu ajoutes une variante** seulement si tu l’as rencontrée en vrai.
- Si tu relis une définition et tu ne la comprends pas : **tu la réécris** (signal Feynman).
