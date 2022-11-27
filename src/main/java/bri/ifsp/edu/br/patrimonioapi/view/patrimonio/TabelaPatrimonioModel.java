package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.view.table.JTableModel;

import java.util.ArrayList;
import java.util.List;

public class TabelaPatrimonioModel extends JTableModel<Patrimonio> {

    private static final long serialVersionUID = -1799110164468447674L;
    
    private final String colunaPatrimonio[] = {
            "Código",
            "Descrição",
            "Estado",
            "Área",
    };

    private final Integer tamanhoCampo[] = {};
    
    private List<Patrimonio> tabela;

    public TabelaPatrimonioModel() {
        tabela = new ArrayList<Patrimonio>();
        this.coluna = colunaPatrimonio;
    }
    
    public TabelaPatrimonioModel(List<Patrimonio> tabela) {
        super(tabela);
        this.tabela = tabela;
        this.coluna = colunaPatrimonio;

    }
    
    @Override
    public Object getValueAt(int linha, int coluna) {
        Patrimonio patrimonio = tabela.get(linha);
        switch (coluna) {
            case 0:
                return patrimonio.getCodigo();
            case 1:
                return patrimonio.getDescPatrimonio();
            case 2:
                return patrimonio.getEstado();
            case 3:
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
                return Integer.class;
            case 3:
                return String.class;
            default:
                return null;
        }

    }
    
    public Patrimonio getPatrimonio(int index) {
        return getTabela().get(index);
    }

    public void salvarPatrimonio(Patrimonio patrimonio) {
        getTabela().add(patrimonio);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public void alterarPatrimonio(Patrimonio patrimonio, int index) {
        getTabela().set(index, patrimonio);
        fireTableRowsUpdated(index, index);
    }
    
    public void excluirPatrimonio(int index) {
        getTabela().remove(index);
        fireTableRowsDeleted(index, index);
    }

    public List<Patrimonio> getTabela() {
        return tabela;
    }

    public void setTabela(List<Patrimonio> tabela) {
        this.tabela = tabela;
    }


    public String[] getColuna() {
        return coluna;
    }


    public Integer[] getTamanhoCampo() {
        return tamanhoCampo;
    }
}
