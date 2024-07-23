package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViandasRepository implements IViandasRepository {

  private List<Vianda> viandas;

  public ViandasRepository() {
    viandas = new ArrayList<>();
  }

  @Override
  public Optional<Vianda> buscar(long id) {
    return this.viandas.stream().filter(v -> v.getId() == id).findFirst();
  }

  @Override
  public List<Vianda> buscarTodos() {
    return this.viandas;
  }

  @Override
  public void guardar(Vianda vianda) {
    this.viandas.add(vianda);
  }

  @Override
  public void actualizar(Vianda vianda) {

  }

  @Override
  public void eliminar(Vianda vianda) {

  }
}
