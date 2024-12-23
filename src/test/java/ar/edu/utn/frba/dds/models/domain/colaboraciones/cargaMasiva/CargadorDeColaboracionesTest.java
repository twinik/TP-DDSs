package ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IFormasColaboracionRespository;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ColaboradoresRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;

class CargadorDeColaboracionesTest {
  private CargaColaboracionCsvReader csvReader;
  private SendGridMailSender mailSender;
  private CargadorDeColaboraciones cargador;

  private IColaboradoresRepository repositorio;
  private IFormasColaboracionRespository formasColaboracionRespository;

  private static int i = 0;

  @BeforeEach
  public void setUp() throws IOException {
    csvReader = new CargaColaboracionCsvReader();
    mailSender = mock(SendGridMailSender.class);
    doNothing().when(mailSender).enviarMail(any());
    repositorio = new ColaboradoresRepository();
    if (i == 0) {
      formasColaboracionRespository = ServiceLocator.get(IFormasColaboracionRespository.class);
      FormaColaboracion donacionDinero = new FormaColaboracion("DONACION_DINERO", "Donación de dinero");
      FormaColaboracion donacionVianda = new FormaColaboracion("DONACION_VIANDA", "Donación de viandas");
      FormaColaboracion redistribucionVianda = new FormaColaboracion("REDISTRIBUCION_VIANDA", "Redistribución de viandas");
      FormaColaboracion registroPersona = new FormaColaboracion("REGISTRO_PERSONA", "Alta persona vulnerable");
      FormaColaboracion colocacionHeladeras = new FormaColaboracion("COLOCACION_HELADERA", "Hacerse cargo de heladeras");
      FormaColaboracion ofrecerProductos = new FormaColaboracion("OFRECER_PRODUCTOS", "Ofrecer productos");
      formasColaboracionRespository.guardar(donacionVianda, donacionDinero, redistribucionVianda, registroPersona, colocacionHeladeras, ofrecerProductos);
    }

    i++;
//        when(formasColaboracionRespository.buscarPorNombre("DONACION_DINERO")).thenReturn(Optional.of(new FormaColaboracion("DONACION_DINERO", "dinero")));
//        when(formasColaboracionRespository.buscarPorNombre("DONACION_VIANDA")).thenReturn(Optional.of(new FormaColaboracion("DONACION_VIANDA", "vianda")));
//        when(formasColaboracionRespository.buscarPorNombre("REGISTRO_PERSONA")).thenReturn(Optional.of(new FormaColaboracion("REGISTRO_PERSONA", "persona")));
//        when(formasColaboracionRespository.buscarPorNombre("REDISTRIBUCION_VIANDA")).thenReturn(Optional.of(new FormaColaboracion("REDISTRIBUCION_VIANDA", "vianda")));
    cargador = new CargadorDeColaboraciones("src/main/java/ar/edu/utn/frba/dds/models/domain/assets/cargacolaboraciones.csv", csvReader, mailSender, repositorio, formasColaboracionRespository, ServiceLocator.get(ICalculadorPuntos.class), ServiceLocator.get(IRolesRepository.class));
  }

  @Test
  void pruebaJsonToDonacionDinero() throws IOException {
    String json = "{\"monto\": 10000, \"frecuencia\": \"DIARIA\", \"fecha\": \"09/12/2018\"}";
    CargaColaboracion carga = new CargaColaboracion();
    carga.setFormaColaboracion("DONACION_DINERO");
    carga.setJsonColaboracion(json);
    DonacionDinero donacion = (DonacionDinero) CargaToColaboracionMapper.colaboracionFromCarga(carga, new Colaborador());
    assertEquals(10000, donacion.getMonto());
    assertEquals(FrecuenciaDonacion.DIARIA, donacion.getFrecuencia());
    assertEquals(LocalDate.of(2018, 12, 9), donacion.getFecha());
  }

  @Test
  @DisplayName("Carga de colaboraciones")
  void cargarColaboraciones() throws IOException {
    assertEquals(19, cargador.cargarColaboraciones().size());
    verify(mailSender, times(3)).enviarMail(any());
  }
}