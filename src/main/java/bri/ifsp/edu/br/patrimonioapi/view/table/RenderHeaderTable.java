package bri.ifsp.edu.br.patrimonioapi.view.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class RenderHeaderTable implements TableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		JComponent componente = null;
		
	
		if (value instanceof String) {
			componente = new JLabel((String)""+value); 
			((JLabel)componente).setHorizontalAlignment(SwingConstants.CENTER);
			((JLabel)componente).setSize(30, componente.getWidth());
			((JLabel)componente).setPreferredSize(new Dimension(3, componente.getWidth()));
		}
		
		
		table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		table.getTableHeader().setPreferredSize(new Dimension(45,45));
		
		componente.setEnabled(true);
		componente.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(255,255,255)));
	    componente.setOpaque(true);
	    componente.setBackground(new Color(46,139,87));
	    componente.setForeground(Color.WHITE);
	    componente.setFont(new Font("Verdana", Font.BOLD, 14));
	   
		return componente;
	}
	
	

}
