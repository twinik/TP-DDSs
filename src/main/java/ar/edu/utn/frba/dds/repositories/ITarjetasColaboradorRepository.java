package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import java.util.List;
import java.util.Optional;

public interface ITarjetasColaboradorRepository {
  Optional<TarjetaColaborador> buscar(String codigo);

  List<TarjetaColaborador> buscarTodos();

  void guardar(TarjetaColaborador tarjeta);

  void actualizar(TarjetaColaborador tarjeta);

  void eliminar(TarjetaColaborador tarjeta);
}