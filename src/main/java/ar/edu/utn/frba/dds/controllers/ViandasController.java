package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.colaboraciones.ViandaDto;
import ar.edu.utn.frba.dds.services.ViandasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViandasController implements ICrudViewsHandler {

  private ViandasService viandasService;
  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    context.render("/app/colaboraciones/donacion-vianda.hbs");
  }

  @Override
  public void save(Context context) {
    ViandaDto dto = ViandaDto.of(context);

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
