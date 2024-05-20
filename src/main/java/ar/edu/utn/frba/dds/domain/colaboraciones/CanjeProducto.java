package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;


@Getter
public class CanjeProducto {
  Colaborador comprador;
  OfertaProducto ofertaCanjeada;
  LocalDateTime fechaCanje;

  public CanjeProducto(Colaborador comprador, OfertaProducto ofertaCanjeada, LocalDateTime fechaCanje) throws PuntosInsuficientesException {
    this.comprador = comprador;
    this.ofertaCanjeada = ofertaCanjeada;
    this.fechaCanje = fechaCanje;
    if (!ofertaCanjeada.puedeSerCanjeadoPor(comprador)) {
      throw new PuntosInsuficientesException();
    }
    comprador.restarPuntos(ofertaCanjeada.getPuntosNecesarios());
  }
}
