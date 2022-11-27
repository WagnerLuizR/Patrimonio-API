package bri.ifsp.edu.br.patrimonioapi.service.errors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestarCampoRequerido {

    public static List<ErrorsData> validarCampoRequerido(final Object objeto) {

        List<ErrorsData> errorsData = new ArrayList<>();
        final Class<?> classe = objeto.getClass();
        final Field[] field = classe.getDeclaredFields();

        for (int i = 0; i < field.length; i++) {
            if (field[i].isAnnotationPresent(CampoRequerido.class)) {
                CampoRequerido campoRequerido = field[i].getDeclaredAnnotation(CampoRequerido.class);
                int valor = campoRequerido.valor();
                String mensagemErro = campoRequerido.mensagem();
                field[i].setAccessible(true);
                try {
                    Object campo = field[i].get(objeto);
                    if (conteudoInformadoNoCampo(campo) == true) {
                        ErrorsData erro = new ErrorsData();
                        erro.setShowMensagemErro(mensagemErro);
                        erro.setNumeroCampo(valor);
                        errorsData.add(erro);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return errorsData;
    }

    private static boolean conteudoInformadoNoCampo(Object objeto) {
        if (Objects.isNull(objeto)) {
            return true;
        }
        if (objeto instanceof String) {
            String campo = (String) objeto;
            return "".equals(campo.trim()) ? true : false;
        }
        if (objeto instanceof Integer) {
            Integer campo = (Integer) objeto;
            return campo == 0 ? true : false;
        }
        if (objeto instanceof Long) {
            Long campo = (Long) objeto;
            return campo == 0L ? true : false;
        }
        return false;
    }
}
