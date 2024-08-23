package ar.edu.utn.frba.dds.domain.tecnicos;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * VisitaTecnico class representa una visita de un tecnico a una heladera
 */
@Data
@Entity
@Table(name = "visita_tecnico")
@Getter
@Setter
public class VisitaTecnico {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id", nullable = false)
    private Tecnico tecnico;

    @Column(name = "fecha_donacion_dinero", columnDefinition = "TIMESTAMP",nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "url_foto", columnDefinition = "TEXT")
    private String urlFoto;

    @Column(name = "esta_solucionado")
    private boolean solucionado;

    @ManyToOne
    @JoinColumn(name = "incidente_id", referencedColumnName = "id", nullable = false)
    private Incidente incidente;
}