package ar.edu.utn.frba.dds.exceptions;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import lombok.Getter;

@Getter
public class ClaveDebilException extends RuntimeException {
    Object formDto;

    public ClaveDebilException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }

    public ClaveDebilException(String message) {
        super(message);
    }
}
