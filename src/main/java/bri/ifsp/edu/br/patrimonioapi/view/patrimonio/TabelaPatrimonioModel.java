package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.view.table.JTableModel;

import java.util.ArrayList;
import java.util.List;

public class TabelaPatrimonioModel extends JTableModel<PatrimonioDTO> {

    private static final long serialVersionUID = -1799110164468447674L;

    private final String colunaPatrimonio[] = {
            "Código",
            "Identificador",
            "Descrição",
            "Estado",
            "Area"

    };

    private final Integer tamanhoCampo[] = {};

    private List<PatrimonioDTO> tabela;

    public TabelaPatrimonioModel() {
        tabela = new ArrayList<PatrimonioDTO>();
        this.coluna = colunaPatrimonio;
    }

    public TabelaPatrimonioModel(List<PatrimonioDTO> tabela) {
        super(tabela);
        this.tabela = tabela;
        this.coluna = colunaPatrimonio;

    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        PatrimonioDTO patrimonio = tabela.get(linha);
        switch (coluna) {
            case 0:
                return patrimonio.getCodigo();
            case 1:
                return patrimonio.getIdentificador();
            case 2:
                return patrimonio.getDescPatrimonio();
            case 3:
                return patrimonio.getEstado();
            case 4:
                return patrimonio.getArea().getNomeArea();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int coluna) {

        switch (coluna) {
            case 0:
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                return null;
        }

    }

    public PatrimonioDTO getPatrimonio(int index) {
        return getTabela().get(index);
    }

    public void salvarPatrimonio(PatrimonioDTO patrimonio) {
        getTabela().add(patrimonio);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public void alterarPatrimonio(PatrimonioDTO patrimonio, int index) {
        getTabela().set(index, patrimonio);
        fireTableRowsUpdated(index, index);
    }

    public void excluirPatrimonio(int index) {
        getTabela().remove(index);
        fireTableRowsDeleted(index, index);
    }

    public List<PatrimonioDTO> getTabela() {
        return tabela;
    }

    public void setTabela(List<PatrimonioDTO> tabela) {
        this.tabela = tabela;
    }


    public String[] getColuna() {
        return coluna;
    }


    public Integer[] getTamanhoCampo() {
        return tamanhoCampo;
    }
}
