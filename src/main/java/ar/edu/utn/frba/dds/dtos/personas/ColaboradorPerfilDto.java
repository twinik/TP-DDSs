package ar.edu.utn.frba.dds.dtos.personas;

import ar.edu.utn.frba.dds.dtos.DireccionDto;
import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ColaboradorPerfilDto {
  private String id;
  private String nombre;
  private String apellido;
  private String email;
  private String documento;
  private TipoDocumento tipoDocumento;
  private String fechaNacimiento;
  private DireccionDto direccionDto;
  private String rubro;
  private String razonSocial;
  private TipoPersonaJuridica tipoPersonaJuridica;
  private Float puntosGanados;
  private Boolean formCompletado;
  private List<MedioContactoDto> mediosContacto;
  private List<FormaColaboracionDto> formaColaboracionDtos;

  public static ColaboradorPerfilDto of(Context context) {
    return ColaboradorPerfilDto
        .builder()
        .id(context.pathParam("id"))
        .nombre(context.formParam("nombre"))
        .apellido(context.formParam("apellido"))
        .email(context.formParam("email"))
        .documento(context.formParam("documento"))
        .tipoDocumento(TipoDocumento.valueOf(context.formParam("tipoDocumento")))
        .fechaNacimiento(context.formParam("fechaNacimiento"))
        .direccionDto(DireccionDto.of(context))
        .rubro(context.formParam("rubro"))
        .razonSocial(context.formParam("razonSocial"))
        .tipoPersonaJuridica(TipoPersonaJuridica.valueOf(context.formParam("tipoPersonaJuridica")))
        .puntosGanados(Float.parseFloat(context.formParam("puntosGanados")))
        .formCompletado(Boolean.parseBoolean(context.formParam("formCompletado")))
        .build();
  }

  public static ColaboradorPerfilDto fromColaborador(Colaborador colaborador) {
    return ColaboradorPerfilDto
        .builder()
        .id(colaborador.getId())
        .nombre(colaborador.getNombre())
        .apellido(colaborador.getApellido())
        .email(colaborador.email())
        .documento(colaborador.getDocumento())
        .tipoDocumento(colaborador.getTipoDocumento())
        .fechaNacimiento(String.valueOf(colaborador.getFechaNacimiento()))
        .direccionDto(DireccionDto.fromDireccion(colaborador.getDireccion()))
        .rubro(colaborador.getRubro())
        .razonSocial(colaborador.getRazonSocial())
        .tipoPersonaJuridica(colaborador.getTipoPersonaJuridica())
        .puntosGanados(colaborador.getPuntosGanados())
        .formCompletado(colaborador.getFormCompletado())
        .build();
  }
}