package bri.ifsp.edu.br.patrimonioapi.serial;

public class Protocolo {
    private String codigoPatrimonio;
    private String leituraComando;

    private void interpretaComando() {
        if (leituraComando != null) {
            codigoPatrimonio = leituraComando;
        }
    }

    public String getCodigoPatrimonio() {
        return codigoPatrimonio;
    }

    public void setCodigoPatrimonio(String codigoPatrimonio) {
        this.codigoPatrimonio = codigoPatrimonio;
    }

    public String getLeituraComando() {
        return leituraComando;
    }

    public void setLeituraComando(String leituraComando) {
        this.leituraComando = leituraComando;
        this.interpretaComando();
    }
}
