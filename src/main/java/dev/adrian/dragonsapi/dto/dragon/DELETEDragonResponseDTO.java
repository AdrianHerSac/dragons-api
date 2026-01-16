package dev.adrian.dragonsapi.dto.dragon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para representar la respuesta de una operación de eliminación
 * de un dragon u otra entidad similar.
 * <p>
 * Contiene un mensaje descriptivo sobre el resultado de la operación y,
 * opcionalmente,
 * la información del dragon que fue eliminado.
 * </p>
 *
 * <b>Campos:</b>
 * <ul>
 * <li><b>Message</b> (<code>String</code>): Mensaje informativo sobre el
 * resultado de la eliminación.</li>
 * <li><b>deletedDragon</b> (<code>GENERICResponseDTO</code>): Objeto con la
 * información del dragon eliminado.</li>
 * </ul>
 *
 * <p>
 * <b>Nota:</b> Se incluye el constructor sin argumentos mediante
 * {@link NoArgsConstructor}
 * porque es necesario para que <b>Jackson</b> pueda deserializar el objeto
 * correctamente
 * al convertir desde JSON durante las pruebas o llamadas REST.
 * </p>
 *
 * @see GENERICDragonResponseDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DELETEDragonResponseDTO {

    /**
     * Mensaje informativo sobre el resultado de la operación de eliminación.
     */
    private String message;

    /**
     * Objeto con la información del dragon eliminado.
     */
    private GENERICDragonResponseDTO deletedDragon;
}
