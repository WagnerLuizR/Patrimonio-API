package bri.ifsp.edu.br.patrimonioapi.service.errors;

public class ErrorsData {

    private String showMensagemErro;
    private Integer numeroCampo;

    public Integer getNumeroCampo() {
        return numeroCampo;
    }

    public void setNumeroCampo(Integer numeroCampo) {
        this.numeroCampo = numeroCampo;
    }

    public String getShowMensagemErro() {
        return showMensagemErro;
    }

    public void setShowMensagemErro(String showMensagemErro) {
        this.showMensagemErro = showMensagemErro;
    }
}
