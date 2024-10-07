package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.ofertas.CategoriaOfertaDto;
import ar.edu.utn.frba.dds.dtos.ofertas.OfertaProductoDto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.Initializer;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class OfertasProductoController implements ICrudViewsHandler {
  private OfertasProductoService ofertasProductoService;

  @Override
  public void index(Context context) {
    //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
    List<OfertaProductoDto> ofertas = ofertasProductoService.obtenerTodos();
    Map<String, Object> model = new HashMap<>();
    model.put("ofertas", ofertas);
    context.render("/app/productos/productos.hbs", model);
  }

  @Override
  public void show(Context context) {
    //RECIBE POR PATH PARAM EL ID DE UN OFERTA Y PRETENDE DEVOLVER UNA VISTA CON EL DETALLE DE ESE PRODUCTO
    // TODO: falta esta vista
//    Optional<OfertaProducto> posibleOferta = this.ofertaProductoRepository.buscar(context.pathParam("id"));
//
//    if(posibleOferta.isEmpty()){
//      context.status(HttpStatus.NOT_FOUND);
//      return;
//    }
//
//    Map<String, Object> model = new HashMap<>();
//    model.put("oferta", posibleOferta.get());
//
//    context.render("insertar_vista_mis_amores", model);
  }

  @Override
  public void create(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UNA NUEVA OFERTA
    // TODO: ya pongo las categorias en el model para que las puedas desplegar

    Map<String, Object> model = new HashMap<>();
    model.put("categorias", Arrays.stream(CategoriaOferta.values()).map(CategoriaOfertaDto::of).toList());
    context.render("/app/colaboraciones/ofrecer-productos.hbs", model);
  }

  @Override
  public void save(Context context) {
    // obtener de sesion
    OfertaProductoDto dto = OfertaProductoDto.of(context);
    try {
      ofertasProductoService.crearOferta(dto);
    } catch (FormIncompletoException e) {
      // TODO: Mostrar pop up error ?
    }
    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
    context.redirect("/productos");
  }

  @Override
  public void edit(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
    // TODO: falta esta vista
  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
