package dev.adrian.dragonsapi.repository;

import dev.adrian.dragonsapi.model.Category;
import dev.adrian.dragonsapi.model.Dragon;
import dev.adrian.dragonsapi.repository.CategoryRepository;
import dev.adrian.dragonsapi.repository.DragonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test de Integración para DragonRepository
 * 
 * CONCEPTOS CLAVE:
 * 
 * 1. @DataJpaTest: Configura un entorno de test para JPA
 * - Usa una base de datos en memoria (H2)
 * - Solo carga los componentes JPA (repositorios, entidades)
 * - Cada test es transaccional y se hace rollback automáticamente
 * 
 * 2. @Autowired: Inyecta el repositorio que queremos testear
 * 
 * 3. @BeforeEach: Se ejecuta antes de cada test para preparar datos
 * 
 * 4. AssertJ (assertThat): Librería para hacer assertions más legibles
 */
@DataJpaTest
class DragonRepositoryTest {

    @Autowired
    private DragonRepository dragonRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Datos de prueba que usaremos en los tests
    private Category fireCategory;
    private Category waterCategory;
    private Dragon fireDragon1;
    private Dragon fireDragon2;
    private Dragon waterDragon;

    /**
     * setUp: Prepara los datos de prueba ANTES de cada test
     * Se ejecuta antes de CADA método @Test
     */
    @BeforeEach
    void setUp() {
        // 1. Limpiar la base de datos (aunque @DataJpaTest hace rollback, es buena
        // práctica)
        dragonRepository.deleteAll();
        categoryRepository.deleteAll();

        // 2. Crear categorías
        fireCategory = new Category();
        fireCategory.setName("Fire Dragons");
        fireCategory.setDescription("Dragons that breathe fire");
        fireCategory = categoryRepository.save(fireCategory);

        waterCategory = new Category();
        waterCategory.setName("Water Dragons");
        waterCategory.setDescription("Dragons from the ocean");
        waterCategory = categoryRepository.save(waterCategory);

        // 3. Crear dragones de prueba
        fireDragon1 = new Dragon();
        fireDragon1.setName("Flame");
        fireDragon1.setColor("Red");
        fireDragon1.setSize(Dragon.Size.M);
        fireDragon1.setPrice(new BigDecimal(29.99));
        fireDragon1.setStock(10);
        fireDragon1.setCategory(fireCategory);
        fireDragon1 = dragonRepository.save(fireDragon1);

        fireDragon2 = new Dragon();
        fireDragon2.setName("Inferno");
        fireDragon2.setColor("Orange");
        fireDragon2.setSize(Dragon.Size.L);
        fireDragon2.setPrice(49.99);
        fireDragon2.setStock(5);
        fireDragon2.setCategory(fireCategory);
        fireDragon2 = dragonRepository.save(fireDragon2);

        waterDragon = new Dragon();
        waterDragon.setName("Aqua");
        waterDragon.setColor("Blue");
        waterDragon.setSize(Dragon.Size.S);
        waterDragon.setPrice(19.99);
        waterDragon.setStock(15);
        waterDragon.setCategory(waterCategory);
        waterDragon = dragonRepository.save(waterDragon);
    }

    /**
     * Test para findAll()
     * 
     * QUÉ TESTEAR:
     * - Que devuelve todos los dragones guardados
     * - Que el tamaño de la lista es correcto
     */
    @Test
    void findAll_ShouldReturnAllDragons() {
        // Given (Dado) - Los datos ya están en setUp()

        // When (Cuando) - Ejecutamos el método a testear
        List<Dragon> dragons = dragonRepository.findAll();

        // Then (Entonces) - Verificamos los resultados
        assertThat(dragons)
                .isNotNull() // La lista no es null
                .hasSize(3) // Tiene exactamente 3 dragones
                .containsExactlyInAnyOrder( // Contiene estos 3 dragones (en cualquier orden)
                        fireDragon1,
                        fireDragon2,
                        waterDragon);
    }

    /**
     * Test para findByCategory()
     * 
     * QUÉ TESTEAR:
     * - Que solo devuelve dragones de la categoría especificada
     * - Que no devuelve dragones de otras categorías
     */
    @Test
    void findByCategory_ShouldReturnOnlyDragonsFromThatCategory() {
        // Given
        Category categoryToSearch = fireCategory;

        // When
        List<Dragon> dragons = dragonRepository.findByCategory(categoryToSearch);

        // Then
        assertThat(dragons)
                .hasSize(2) // Solo 2 dragones de fuego
                .extracting(Dragon::getName) // Extraemos solo los nombres
                .containsExactlyInAnyOrder("Flame", "Inferno") // Verificamos los nombres
                .doesNotContain("Aqua"); // No contiene el dragón de agua
    }

    /**
     * Test para findByNameContainingIgnoreCase()
     * 
     * QUÉ TESTEAR:
     * - Búsqueda parcial (contiene)
     * - Búsqueda case-insensitive (mayúsculas/minúsculas)
     */
    @Test
    void findByNameContainingIgnoreCase_ShouldFindDragonsWithPartialName() {
        // Given
        String searchTerm = "FLA"; // En mayúsculas para testear case-insensitive

        // When
        List<Dragon> dragons = dragonRepository.findByNameContainingIgnoreCase(searchTerm);

        // Then
        assertThat(dragons)
                .hasSize(1)
                .extracting(Dragon::getName)
                .containsExactly("Flame");
    }

    /**
     * Test para caso where no encuentra resultados
     */
    @Test
    void findByNameContainingIgnoreCase_ShouldReturnEmptyListWhenNoMatch() {
        // Given
        String searchTerm = "NonExistent";

        // When
        List<Dragon> dragons = dragonRepository.findByNameContainingIgnoreCase(searchTerm);

        // Then
        assertThat(dragons).isEmpty();
    }

    /**
     * Test para findBySize()
     */
    @Test
    void findBySize_ShouldReturnDragonsOfSpecificSize() {
        // Given
        Dragon.Size sizeToSearch = Dragon.Size.M;

        // When
        List<Dragon> dragons = dragonRepository.findBySize(sizeToSearch);

        // Then
        assertThat(dragons)
                .hasSize(1)
                .first() // Obtiene el primer elemento
                .satisfies(dragon -> { // Verifica múltiples condiciones
                    assertThat(dragon.getName()).isEqualTo("Flame");
                    assertThat(dragon.getSize()).isEqualTo(Dragon.Size.M);
                });
    }

    /**
     * Test adicional: Verificar que save() funciona correctamente
     */
    @Test
    void save_ShouldPersistNewDragon() {
        // Given
        Dragon newDragon = new Dragon();
        newDragon.setName("Shadow");
        newDragon.setColor("Black");
        newDragon.setSize(Dragon.Size.XL);
        newDragon.setPrice(99.99);
        newDragon.setStock(2);
        newDragon.setCategory(fireCategory);

        // When
        Dragon savedDragon = dragonRepository.save(newDragon);

        // Then
        assertThat(savedDragon.getId()).isNotNull(); // Se generó un ID
        assertThat(dragonRepository.findAll()).hasSize(4); // Ahora hay 4 dragones
    }

    /**
     * Test adicional: Verificar que delete() funciona
     */
    @Test
    void delete_ShouldRemoveDragon() {
        // Given
        int initialSize = dragonRepository.findAll().size();

        // When
        dragonRepository.delete(fireDragon1);

        // Then
        assertThat(dragonRepository.findAll())
                .hasSize(initialSize - 1)
                .doesNotContain(fireDragon1);
    }
}
