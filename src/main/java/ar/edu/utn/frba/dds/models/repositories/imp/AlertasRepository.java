package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@NoArgsConstructor
public class AlertasRepository implements IAlertasRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Alerta> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Alerta.class, id));
  }

  @Override
  public List<Alerta> buscarTodos() {
    return entityManager().createQuery("from Alerta where activo=:activo", Alerta.class)
        .setParameter("activo", true)
        .getResultList();
  }

  @Override
  public List<Alerta> buscarAlertasHeladera(String heladera_id) {
    return entityManager().createQuery("from Alerta where activo=:activo" +
            " and heladera.id=:heladera_id and solucionado=:no_solucionado", Alerta.class)
        .setParameter("activo", true)
        .setParameter("heladera_id", heladera_id)
        .setParameter("no_solucionado", false)
        .getResultList();
  }

  @Override
  public Map<String, Long> buscarAlertasAgrupadasPorHeladera(LocalDate fecha) {
    LocalDateTime principioDeSemana = DateHelper.principioDeSemana(fecha.atStartOfDay());
    LocalDateTime finDeSemana = DateHelper.finDeSemana(fecha.atStartOfDay());
    List<Object[]> results = entityManager().createQuery(
            "select a.heladera.nombre, count(a) from Alerta a where a.activo = :activo" + " and a.timestamp between :principioSemana and :finSemana group by a.heladera.nombre order by count(a)", Object[].class)
        .setParameter("activo", true)
        .setParameter("principioSemana", principioDeSemana)
        .setParameter("finSemana", finDeSemana)
        .getResultList();

    return results.stream().collect(Collectors.toMap(
        result -> (String) result[0],
        result -> (Long) result[1]
    ));
  }

  @Override
  public void guardar(Alerta alerta) {
    withTransaction(() -> entityManager().persist(alerta));
  }

  @Override
  public void actualizar(Alerta alerta) {
    withTransaction(() -> entityManager().merge(alerta));
  }

  @Override
  public void eliminar(Alerta alerta) {
    withTransaction(() -> {
      alerta.borrarLogico();
      entityManager().merge(alerta);
    });
  }

}