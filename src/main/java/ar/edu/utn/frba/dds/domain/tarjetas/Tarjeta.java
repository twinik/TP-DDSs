package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Tarjeta class permite representar una tarjeta.
 */
@Getter
@Setter
@AllArgsConstructor
public class Tarjeta {
  private String codigo;
  private Integer nroUsos;
  private boolean activa;
  private FrecuenciaUso frecuenciaPermitida;
  private List<UsoTarjeta> usos;
  private PersonaVulnerable duenio;
  private Date fechaAdjudicacion;
  private Integer cantidadUsosDia;
  // CRON JOB string (todos los dias a las 00:00hs): "0 0 0 1/1 * ? *"

  /**
   * Constructor de Tarjeta.
   */
  public Tarjeta(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, Date fechaAdjudicacion, Integer cantidadUsosDia) {
    this.codigo = codigo;
    this.nroUsos = nroUsos;
    this.frecuenciaPermitida = frecuenciaPermitida;
    this.activa = true;
    this.usos = new ArrayList<UsoTarjeta>();
    this.duenio = duenio;
    this.fechaAdjudicacion = fechaAdjudicacion;
    this.cantidadUsosDia = 0;
  }

  public void agregarUsos() {
    this.cantidadUsosDia++;
    this.nroUsos++;
  }

  public void resetearUsosDiarios(){
    this.cantidadUsosDia = 0;
  }

  boolean permiteUsar() {
    return this.activa && frecuenciaPermitida.permiteUsar(this);
  }

}