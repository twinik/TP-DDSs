package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.ISensorMovimientoRepository;
import java.io.IOException;

public class SensorMovimientoBroker {
  public static void suscribirseASensorMovimiento() throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic        = configReader.getProperty("SENSOR_MOV_BROKER_TOPIC");
    String broker       = configReader.getProperty("SENSOR_MOV_BROKER");
    String clientId     = configReader.getProperty("CLIENT_ID");

    SensorMovimientoListener receptor = new SensorMovimientoListener();
    receptor.setSensorMovimientoRepository((ISensorMovimientoRepository) ServiceLocator.get("sensoresMovimientoRepository"));

    BrokerSubscriber brokerSubscriber = new BrokerSubscriber(topic, broker, clientId, receptor);
    brokerSubscriber.listen();
  }

  public static void main(String[] args) {
    try {
      suscribirseASensorMovimiento();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
