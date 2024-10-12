package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.externapi.RecomendacionDonaciones;
import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import ar.edu.utn.frba.dds.externapi.recomendaciones.RecomendacionDto;
import ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion.ListadoUbicaciones;
import ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion.RecomendadorDePuntosDeColocacion;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RecomendacionesController implements ICrudViewsHandler {

    RecomendacionDonaciones adapterDonaciones;
    RecomendadorDePuntosDeColocacion adapterPuntosColocacion;

    public void getDonacionesParaMapa(Context context) {
        String provincia = context.queryParam("provincia");
        String localidad = context.queryParam("localidad");
        List<Recomendacion> recomendaciones = adapterDonaciones.listarRecomendaciones(provincia, localidad);
        List<RecomendacionDto> resultado = recomendaciones.stream().map(RecomendacionDto::fromRecomendacion).collect(Collectors.toList());
        context.json(resultado);
    }

    public void getPuntosColocacionParaMapa(Context context) {
        Float latitud = Float.parseFloat(context.queryParam("latitud"));
        Float longitud = Float.parseFloat(context.queryParam("longitud"));
        Ubicacion ubicacion = new Ubicacion(latitud, longitud);
        Float radio = Float.parseFloat(context.queryParam("radio"));
        ListadoUbicaciones puntosDeColocacion = adapterPuntosColocacion.recomendarUbicacion(ubicacion, radio);
        /*
        En relidad lo de arriba tiene que ser
        List<RecomendacionColocacionDto> puntosDeColocacion = adapterPuntosColocacion.recomendarUbicacion(ubicacion, radio);
         */
        context.json(puntosDeColocacion);
    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

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
