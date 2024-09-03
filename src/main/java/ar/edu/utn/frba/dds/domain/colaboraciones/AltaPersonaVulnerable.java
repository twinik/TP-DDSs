package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * AltaPersonaVulnerable class representa una colaboracion de un colaborador.
 * Consiste en dar de alta a una persona vulnerable.
 */

@Entity
@Table(name = "alta_persona_vulnerable")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AltaPersonaVulnerable extends EntidadPersistente implements IPuntajeCalculable {

  // TODO revisar REPO quizas un on delete cascade con activo...

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
  private Colaborador colaborador;

  @Column(name = "fecha_redistribucion", columnDefinition = "DATE",nullable = false)
  private LocalDate fecha;
  private static final Float puntajePorAlta = 2f;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn (name = "persona_vulnerable_id", referencedColumnName = "id", nullable = false, unique = true)
  private PersonaVulnerable persona;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn (name = "tarjeta_id", referencedColumnName = "id", unique = true)
  private Tarjeta tarjeta;

  /**
   * Constructor con parametros.
   */
  @Override
  public Float calcularPuntaje() {
    return puntajePorAlta;
  }

  /**
   * Metodo efectuar que se encarga de sumar puntos al colaborador.
   */

}
