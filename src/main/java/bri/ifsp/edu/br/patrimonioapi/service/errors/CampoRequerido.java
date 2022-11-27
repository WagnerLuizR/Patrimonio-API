package bri.ifsp.edu.br.patrimonioapi.service.errors;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface CampoRequerido {

    public int valor() default 0;

    public String mensagem() default "Campo obrigatório";
}
