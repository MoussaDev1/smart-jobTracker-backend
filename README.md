## Zone 2 — Domaine & Logique Métier

### Implémenté
- Création de l'api REST (avec DTO et Mapper)
- Entité JobApplication
- Enum ApplicationStatus
- CRUD backend
- State Machine contrôlant les transitions
- Tests unitaires sur les règles métier
  
| Méthode | Endpoint | Action |
| --- | --- | --- |
| POST | `/api/jobs` | Créer |
| GET | `/api/jobs` | Lister |
| GET | `/api/jobs/{id}` | Lire |
| PUT | `/api/jobs/{id}` | Modifier |
| DELETE | `/api/jobs/{id}` | Supprimer |
| PATCH | `/api/jobs/{id}/status` | Modifier(Statut uniquement) |

### Règles métier clés
- Ajout d'un State management stricte pour le chagement de statut d'un JobApplication
  
| From | To | Autorisé |
| --- | --- | --- |
| TO_APPLY | APPLIED | ✅ |
| APPLIED | INTERVIEW | ✅ |
| INTERVIEW | OFFER | ✅ |
| * | REJECTED | ✅ |
| TO_APPLY | OFFER | ❌ |
| REJECTED | * | ❌ | 
