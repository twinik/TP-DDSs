package ar.edu.utn.frba.dds.domain.helpers.validaciones;

import ar.edu.utn.frba.dds.domain.helpers.MotivoNoValido;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidacionLargoClaves implements Validacion {
    @Getter
    private MotivoNoValido motivo;
    @Getter
    private static final Integer LONGITUD_MINIMA = 8;
    @Getter
    private static final Integer LONGITUD_MAXIMA = 64;

    private boolean esMuyCorta (String contrasena){
        boolean result = contrasena.length() < LONGITUD_MINIMA;
        if (result) {
            this.motivo = new MotivoNoValido( "La contrasena es muy corta, debe tener como minimo " + LONGITUD_MINIMA + " caracteres.");
        }
        return result;
    }

    private boolean esMuyLarga(String contrasena) {
        boolean result = contrasena.length() > LONGITUD_MAXIMA;
        if (result) {
            this.motivo = new MotivoNoValido( "La contrasena es muy larga, debe tener como maximo " + LONGITUD_MAXIMA + " caracteres.");
        }
        return result;
    }

    public boolean validar(String clave) {
        return !esMuyCorta(clave) && !esMuyLarga(clave);
    }
}
