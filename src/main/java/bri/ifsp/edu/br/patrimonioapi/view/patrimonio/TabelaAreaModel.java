package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import java.util.ArrayList;
import java.util.List;

import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.view.table.JTableModel;

public class TabelaAreaModel extends JTableModel<Area>{

    private static final long serialVersionUID = -1799110164468447674L;
    
    private final String colunaArea[] = {
            "Código",
            "Descrição",
            "Tipo de Área",
    };

    private final Integer tamanhoCampo[] = {};
    
    private List<Area> tabela;

    public TabelaAreaModel() {
        tabela = new ArrayList<Area>();
        this.coluna = colunaArea;
    }
    
    public TabelaAreaModel(List<Area> tabela) {
        super(tabela);
        this.tabela = tabela;
        this.coluna = colunaArea;

    }
    
    @Override
    public Object getValueAt(int linha, int coluna) {
        Area area = tabela.get(linha);
        switch (coluna) {
            case 0:
                return area.getId();
            case 1:
                return area.getNomeArea();
            case 2:
                return area.getTipoArea();
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
            default:
                return null;
        }

    }
    
    public Area getArea(int index) {
        return getTabela().get(index);
    }

    public void salvarArea(Area area) {
        getTabela().add(area);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public void alterarArea(Area area, int index) {
        getTabela().set(index, area);
        fireTableRowsUpdated(index, index);
    }
    
    public void excluirArea(int index) {
        getTabela().remove(index);
        fireTableRowsDeleted(index, index);
    }

    public List<Area> getTabela() {
        return tabela;
    }

    public void setTabela(List<Area> tabela) {
        this.tabela = tabela;
    }


    public String[] getColuna() {
        return coluna;
    }


    public Integer[] getTamanhoCampo() {
        return tamanhoCampo;
    }
}
