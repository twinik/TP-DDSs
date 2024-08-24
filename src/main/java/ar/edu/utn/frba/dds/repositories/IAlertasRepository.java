package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import java.util.List;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public interface IAlertasRepository {
  Optional<Alerta> buscar(long id);

  List<Alerta> buscarTodos();

  void guardar(Alerta alerta);

  void actualizar(Alerta alerta);

  void eliminar(Alerta alerta);
}
