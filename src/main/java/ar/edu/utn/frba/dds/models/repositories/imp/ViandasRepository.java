package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.repositories.IViandasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ViandasRepository implements IViandasRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Vianda> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Vianda.class, id));
  }

  @Override
  public List<Vianda> buscarTodos() {
    return entityManager().createQuery("from Vianda where activo=:activo", Vianda.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public Map<String, Long> buscarViandasAgrupadasPorColaborador(LocalDate fecha) {
    LocalDate principioDeSemana = DateHelper.principioDeSemana(fecha);
    LocalDate finDeSemana = DateHelper.finDeSemana(fecha);

    List<Object[]> results = entityManager().createQuery(
            "select concat(v.colaborador.nombre,' ',v.colaborador.apellido,' (id: ', v.colaborador.id, ')'), count(v) from Vianda v " +
                "where v.activo = :activo and v.fechaDonacion between :principioSemana and :finSemana " +
                "group by concat(v.colaborador.nombre,' ',v.colaborador.apellido,' (id: ', v.colaborador.id, ')') " +
                "order by count(v) asc", Object[].class)
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
  public void guardar(Vianda vianda) {
    withTransaction(() -> entityManager().persist(vianda));
  }

  @Override
  public void guardar(List<Vianda> viandas) {
    withTransaction(() -> {
      viandas.forEach(v -> entityManager().persist(v));
    });
  }

  @Override
  public void actualizar(Vianda vianda) {
    withTransaction(() -> entityManager().merge(vianda));
  }

  @Override
  public void eliminar(Vianda vianda) {

    withTransaction(() -> {
      vianda.borrarLogico();
      entityManager().merge(vianda);
    });
  }
}
