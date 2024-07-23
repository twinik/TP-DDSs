package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Clase: RegistroTemperatura: guarda la hora y la temperatura registrada en ese instante
 */
@Getter
@AllArgsConstructor
public class RegistroTemperatura {
  private long id;
  LocalDateTime fechaHora;
  float temperaturaRegistrada;

  public RegistroTemperatura(LocalDateTime fechaHora, float temperaturaRegistrada) {
    this.fechaHora = fechaHora;
    this.temperaturaRegistrada = temperaturaRegistrada;
  }
}
