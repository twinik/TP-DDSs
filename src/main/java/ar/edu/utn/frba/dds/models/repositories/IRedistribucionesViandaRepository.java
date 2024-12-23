package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * IRedistribucionesRepository interface permite interactuar con las redistribuciones de viandas
 */
public interface IRedistribucionesViandaRepository {

  Optional<RedistribucionViandas> buscar(String id);

  List<RedistribucionViandas> buscarTodos();

  Map<String, Long> buscarViandasColocadasPorHeladera(LocalDate fecha);

  Map<String, Long> buscarViandasRetiradasPorHeladera(LocalDate fecha);

  void guardar(RedistribucionViandas redistribucionViandas);

  void actualizar(RedistribucionViandas redistribucionViandas);

  void eliminar(RedistribucionViandas redistribucionViandas);
}
