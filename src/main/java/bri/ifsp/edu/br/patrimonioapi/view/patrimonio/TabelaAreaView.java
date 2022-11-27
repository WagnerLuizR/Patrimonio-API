package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import bri.ifsp.edu.br.patrimonioapi.config.Constantes;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.service.AreaService;
import bri.ifsp.edu.br.patrimonioapi.service.PatrimonioService;
import bri.ifsp.edu.br.patrimonioapi.view.table.RenderHeaderTable;
import bri.ifsp.edu.br.patrimonioapi.view.table.RenderTable;

public class TabelaAreaView extends JFrame {



    private static final long serialVersionUID = -5023029697133341105L;

    private JPanel contentPane;
    private JTable tabelaArea;
    private JButton btnPesquisar;
    private JButton btnPrimeiro;
    private JButton btnAnterior;
    private JButton btnProximo;
    private JButton btnUltimo;
    private JButton btnIncluir;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnConsultar;
    private JButton btnSair;
    private TabelaAreaModel model;
    private Page<Area> page;
    private AreaService areaService;
    private Area area;
    private int linha = 0;
    private int coluna = 0;
    private int tamanhoPagina = 10;
    private int paginaAtual = 0;
    private JTextField nomePesquisa;

    private static TabelaAreaView TABELA_AREA;

    public TabelaAreaView() {
        initComponents();
        eventHandlers();
        iniciarTabela();
    }


    public static TabelaAreaView getInstancia() {
        if (Objects.isNull(TABELA_AREA)) {
        	TABELA_AREA = new TabelaAreaView();
        }
        return TABELA_AREA;
    }

    private void eventHandlers() {

        btnPrimeiro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paginaAtual = 1;
                iniciarTabela();
            }
        });
        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (paginaAtual > 1) {
                    paginaAtual = paginaAtual - 1;
                    iniciarTabela();
                }

            }
        });
        btnProximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (paginaAtual < page.getTotalPage()) {
                    paginaAtual = paginaAtual + 1;
                    iniciarTabela();
                }

            }
        });
        btnUltimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paginaAtual = page.getTotalPage();
                iniciarTabela();
            }
        });

        btnIncluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showAreaFrame(Constantes.INCLUIR);
                iniciarTabela();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getLinhaTabela();
                showAreaFrame(Constantes.ALTERAR);
                iniciarTabela();
            }
        });
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getLinhaTabela();
                showAreaFrame(Constantes.EXCLUIR);
                iniciarTabela();

            }
        });
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getLinhaTabela();
                showAreaFrame(Constantes.CONSULTAR);
            }
        });
        nomePesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                iniciarTabela();
            }
        });


    }


    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 805, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelTabela = new JPanel();
        panelTabela.setBounds(0, 78, 789, 373);
        contentPane.add(panelTabela);
        panelTabela.setLayout(null);

        JScrollPane scrollPaneTabela = new JScrollPane();
        scrollPaneTabela.setBounds(0, 0, 789, 305);
        panelTabela.add(scrollPaneTabela);

        tabelaArea = new JTable();
        scrollPaneTabela.setViewportView(tabelaArea);

        btnPrimeiro = new JButton("Início");

        btnPrimeiro.setFont(new Font("Verdana", Font.BOLD, 14));
        btnPrimeiro.setBounds(213, 316, 105, 36);
        panelTabela.add(btnPrimeiro);

        btnAnterior = new JButton("Anterior");

        btnAnterior.setFont(new Font("Verdana", Font.BOLD, 14));
        btnAnterior.setBounds(328, 316, 105, 36);
        panelTabela.add(btnAnterior);

        btnProximo = new JButton("Próximo");

        btnProximo.setFont(new Font("Verdana", Font.BOLD, 14));
        btnProximo.setBounds(443, 316, 105, 36);
        panelTabela.add(btnProximo);

        btnUltimo = new JButton("Último");

        btnUltimo.setFont(new Font("Verdana", Font.BOLD, 14));
        btnUltimo.setBounds(558, 316, 105, 36);
        panelTabela.add(btnUltimo);

        JPanel panelPesquisa = new JPanel();
        panelPesquisa.setBounds(0, 0, 789, 75);
        contentPane.add(panelPesquisa);
        panelPesquisa.setLayout(null);

        JLabel lblNome = new JLabel("Descrição:");
        lblNome.setFont(new Font("Verdana", Font.BOLD, 14));
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(21, 24, 73, 30);
        panelPesquisa.add(lblNome);

        nomePesquisa = new JTextField();
        nomePesquisa.setBounds(106, 25, 487, 28);
        panelPesquisa.add(nomePesquisa);
        nomePesquisa.setColumns(10);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setFont(new Font("Verdana", Font.BOLD, 14));
        btnPesquisar.setBounds(614, 24, 119, 30);
        panelPesquisa.add(btnPesquisar);

        btnIncluir = new JButton("Incluir");

        btnIncluir.setFont(new Font("Verdana", Font.BOLD, 14));
        btnIncluir.setBounds(10, 478, 89, 33);
        contentPane.add(btnIncluir);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setFont(new Font("Verdana", Font.BOLD, 14));
        btnAlterar.setBounds(109, 478, 89, 33);
        contentPane.add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Verdana", Font.BOLD, 14));
        btnExcluir.setBounds(208, 478, 89, 33);
        contentPane.add(btnExcluir);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Verdana", Font.BOLD, 14));
        btnConsultar.setBounds(307, 478, 127, 33);
        contentPane.add(btnConsultar);
        
        btnSair = new JButton("Fechar");
        btnSair.setFont(new Font("Verdana", Font.BOLD, 14));
        btnSair.setBounds(645, 478, 127, 33);
        contentPane.add(btnSair);
    }

    public void iniciarTabela() {

        listarArea();

        model = new TabelaAreaModel(page.getContent());

        model.fireTableDataChanged();

        tabelaArea.setModel(model);

        RenderHeaderTable renderHeader = new RenderHeaderTable();

        tabelaArea.getTableHeader().setDefaultRenderer(renderHeader);

        RenderTable render = new RenderTable();

        for (int coluna = 0; coluna < model.getColumnCount(); coluna++) {
            tabelaArea.setDefaultRenderer(model.getColumnClass(coluna), render);
        }
        for (int col = 0; col < model.getColumnCount(); col++) {
            TableColumn coluna = tabelaArea.getColumnModel().getColumn(col);
            coluna.setMinWidth(100);
            coluna.setMaxWidth(100);
            coluna.setPreferredWidth(100);
        }
    }


    private void getLinhaTabela() {
        area = getArea();
        if (tabelaArea.getSelectedRow() != -1) {
            linha = tabelaArea.getSelectedRow();
            coluna = tabelaArea.getSelectedColumn();
            area = model.getArea(linha);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na Tabela", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAreaFrame(Integer opcaoCadastro) {
        AreaView view = new AreaView(area, opcaoCadastro);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private void listarArea() {

        areaService = getAreaService();

        if (nomePesquisa.equals("")) {
            page = areaService.listaPaginada(paginaAtual, tamanhoPagina);
        } else {
            page = areaService.listaPaginada(paginaAtual, tamanhoPagina, nomePesquisa.getText());
        }

        if (paginaAtual == 1) {
            btnPrimeiro.setEnabled(false);
            btnAnterior.setEnabled(false);
        } else {
            btnPrimeiro.setEnabled(true);
            btnAnterior.setEnabled(true);
        }

        if (paginaAtual == page.getTotalPage()) {
            btnProximo.setEnabled(false);
            btnUltimo.setEnabled(false);
        } else {
            btnProximo.setEnabled(true);
            btnUltimo.setEnabled(true);
        }

        if (paginaAtual > page.getTotalPage()) {
            paginaAtual = page.getTotalPage();
        }

        paginaAtual = page.getPage();
        tamanhoPagina = page.getPageSize();
    }

    public AreaService getAreaService() {
        return new AreaService();
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public Area getArea() {
        return new Area();
    }
}
