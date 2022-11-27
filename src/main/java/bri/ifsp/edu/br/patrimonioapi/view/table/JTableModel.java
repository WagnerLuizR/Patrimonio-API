package bri.ifsp.edu.br.patrimonioapi.view.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class JTableModel<T> extends AbstractTableModel {
	

	private static final long serialVersionUID = 2486255410122898144L;

	protected List<T> tabela;
	protected String coluna[];
	
	
	public JTableModel() {
		tabela = new ArrayList<T>();
	}
	
	
	public JTableModel(List<T> tabela) {
		this.tabela = tabela;
	}
	
		
	@Override
	public int getRowCount() {
		return tabela.size();
	}

	@Override
	public int getColumnCount() {
		return coluna.length;
	}
	
	
	@Override
	public String getColumnName(int col) {
       if ( col < getColumnCount()) {
    	   return coluna[col];
       }
       return super.getColumnName(col);
	}
	
	
	
	public T getValueAt(int linha) {
		return tabela.get(linha);
	}
	
	public void setValueAt(int linha, T objeto) {
		tabela.set(linha, objeto);
	}


	public List<T> getTabela() {
		return tabela;
	}


	public void setTabela(List<T> tabela) {
		this.tabela = tabela;
	}


	public String[] getColuna() {
		return coluna;
	}


	public void setColuna(String[] coluna) {
		this.coluna = coluna;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	
	

}
