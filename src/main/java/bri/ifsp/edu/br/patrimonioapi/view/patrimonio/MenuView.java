package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import bri.ifsp.edu.br.patrimonioapi.config.Constantes;
import bri.ifsp.edu.br.patrimonioapi.message.ModelResponse;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.service.AreaService;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MenuView extends JFrame {
    private static final long serialVersionUID = 6944798959841337479L;
    private JPanel contentPane;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JButton btnTabelaPatrimonio;
    private JButton btnTabelaArea;
    private JButton btnPatrimonio;
    private JButton btnArea;
    private Area area;
    private Patrimonio patrimonio;

    private static MenuView MENU;

    /**
     * Create the frame.
     */
    public MenuView() {

        initComponents();

        eventHandler();

        btnArea.setText("Incluir Area");
        btnArea.setFont(new Font("Verdana", Font.BOLD, 14));
        btnArea.setBounds(231, 201, 220, 78);

        btnTabelaArea.setText("Abrir Tabela de Área");
        btnTabelaArea.setFont(new Font("Verdana", Font.BOLD, 14));
        btnTabelaArea.setBounds(231, 470, 220, 78);
        
        btnPatrimonio.setText("Incluir Patrimonio");
        btnPatrimonio.setFont(new Font("Verdana", Font.BOLD, 14));
        btnPatrimonio.setBounds(231, 72, 220, 78);
        
        btnTabelaPatrimonio.setText("Abrir Tabela de Patrimonio");
        btnTabelaPatrimonio.setFont(new Font("Verdana", Font.BOLD, 12));
        btnTabelaPatrimonio.setBounds(231, 327, 220, 78);
        
    }

    public static MenuView getInstancia() {
        if (Objects.isNull(MENU)) {
            MENU = new MenuView();
        }
        return MENU;
    }

    private void initComponents() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 704, 650);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setForeground(new Color(121, 210, 230));
        panel.setBackground(new Color(48, 101,172));
        panel.setBounds(0, 0, 688, 611);
        contentPane.add(panel);
        panel.setLayout(null);

        btnArea = new JButton();
        btnArea.setVisible(true);
        panel.add(btnArea);

        btnPatrimonio = new JButton();
        btnPatrimonio.setVisible(true);
        panel.add(btnPatrimonio);

        btnTabelaArea = new JButton();
        btnTabelaArea.setVisible(true);
        panel.add(btnTabelaArea);

        btnTabelaPatrimonio = new JButton();
        btnPatrimonio.setVisible(true);
        panel.add(btnTabelaPatrimonio);

        btnSalvar = new JButton();
        panel.add(btnSalvar);

        btnCancelar = new JButton();
        panel.add(btnCancelar);
        
        JLabel lblTelaMenu = new JLabel("Menu");
        lblTelaMenu.setForeground(new Color(121, 210, 230));
        lblTelaMenu.setBackground(new Color(121, 210,2));
        lblTelaMenu.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTelaMenu.setBounds(26, 11, 161, 33);
        panel.add(lblTelaMenu);
    }

    private void eventHandler() {

        btnArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAreaFrame(Constantes.INCLUIR);
            }
        });
        btnPatrimonio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPatrimonioFrame(Constantes.INCLUIR);
            }
        });
        btnTabelaPatrimonio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTabelaPatrimonioFrame();
            }
        });
        
        btnTabelaArea.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				showTabelaAreaFrame();
			}
		});

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }


    private void showPatrimonioFrame(Integer opcaoCadastro) {
        PatrimonioView view = new PatrimonioView(patrimonio, opcaoCadastro);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void showAreaFrame(Integer opcaoCadastro) {
        AreaView view = new AreaView(area, opcaoCadastro);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    private void showTabelaPatrimonioFrame() {
        TabelaPatrimonioView view = new TabelaPatrimonioView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    private void showTabelaAreaFrame() {
    	TabelaAreaView view = new TabelaAreaView();
    	view.setLocationRelativeTo(null);
    	view.setVisible(true);
    }
}

