# TEST Spring boot

# Pour faire des tests unitaires et d'intégration dans
Spring Boot, on utilise souvent JUnit et MockMvc.
Voici un exemple de test minimal pour un controller qui
vérifie qu'une requête GET à l'endpoint /api/health
retourne un statut "ok".

```java
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk());
    }

}
```
**Règles combat**
- ✅ @WebMvcTest : Configure le contexte de test pour les controllers web.
- ✅ @Autowired MockMvc : Permet d'injecter MockMvc pour simuler des requêtes HTTP (utiliser mockMvc).
- ✅ @MockBean : Crée un mock (un substitut) pour une classe qui est utilisée dans le controller (ici EmployeeService).