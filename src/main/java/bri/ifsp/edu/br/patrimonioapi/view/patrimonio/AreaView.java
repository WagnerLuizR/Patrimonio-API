package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import bri.ifsp.edu.br.patrimonioapi.config.Constantes;
import bri.ifsp.edu.br.patrimonioapi.message.ModelResponse;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.service.AreaService;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class AreaView extends JFrame {

    private static final long serialVersionUID = 6944798959841337479L;
    private JPanel contentPane;
    private JTextField nomeArea;
    private JTextField tipoArea;

    private JButton btnSalvar;
    private JButton btnCancelar;

    private JLabel mensagemNomeArea;
    private JLabel mensagemTipoArea;

    private Long idArea = 0L;
    private AreaService areaService;
    private Area area;

    private ModelResponse<Area> modelResponse;

    private ModelResponse<ErrorsData> errors;


    /**
     * Create the frame.
     */
    public AreaView(Area area, Integer opcaoCadastro) {

        initComponents();

        eventHandler();

        btnSalvar.setText("Salvar");
        btnCancelar.setText("Cancelar");

        if (opcaoCadastro == Constantes.INCLUIR) {
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(178, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(347, 24, 149, 52);
            btnCancelar = new JButton("Cancelar");
        } else if (opcaoCadastro == Constantes.ALTERAR) {
            consultarArea(area.getId());
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(178, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(347, 24, 149, 52);
        } else if (opcaoCadastro == Constantes.EXCLUIR) {
            consultarArea(area.getId());
            btnSalvar.setText("Excluir");
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(178, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(347, 24, 149, 52);
        } else if (opcaoCadastro == Constantes.CONSULTAR) {
            consultarArea(area.getId());
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(178, 24, 149, 52);
        }
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
        panel.setBackground(new Color(30, 144, 255));
        panel.setBounds(0, 0, 688, 523);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lbldescArea = new JLabel("Descrição:");
        lbldescArea.setHorizontalAlignment(SwingConstants.CENTER);
        lbldescArea.setFont(new Font("Verdana", Font.BOLD, 20));
        lbldescArea.setBounds(21, 60, 124, 36);
        panel.add(lbldescArea);

        nomeArea = new JTextField();
        nomeArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                nomeArea.setBorder(null);
                mensagemNomeArea.setVisible(false);
            }
        });
        nomeArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER ||
                        e.getKeyCode() == KeyEvent.VK_TAB) {
                    tipoArea.requestFocus();
                }
            }
        });
        nomeArea.setBorder(null);
        nomeArea.setColumns(10);
        nomeArea.setFont(new Font("Verdana", Font.PLAIN, 20));
        nomeArea.setBounds(189, 60, 403, 36);
        panel.add(nomeArea);

        JLabel lblTipoArea = new JLabel("Tipo de Área:");
        lblTipoArea.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoArea.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTipoArea.setBounds(13, 126, 166, 36);
        panel.add(lblTipoArea);

        tipoArea = new JTextField();
        tipoArea.setFont(new Font("Verdana", Font.PLAIN, 20));
        tipoArea.setColumns(10);
        tipoArea.setBorder(null);
        tipoArea.setBounds(189, 126, 403, 36);
        panel.add(tipoArea);

        mensagemNomeArea = new JLabel("");
        mensagemNomeArea.setBounds(179, 60, 403, 14);
        panel.add(mensagemNomeArea);

        mensagemTipoArea = new JLabel("");
        mensagemTipoArea.setBounds(179, 126, 403, 14);
        panel.add(mensagemTipoArea);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(new Color(0, 128, 0));
        panelBotoes.setBounds(0, 521, 696, 99);
        contentPane.add(panelBotoes);
        panelBotoes.setLayout(null);

        btnSalvar = new JButton();
        panelBotoes.add(btnSalvar);

        btnCancelar = new JButton();
        panelBotoes.add(btnCancelar);

        mensagemNomeArea.setVisible(false);
        mensagemTipoArea.setVisible(false);
    }

    private void eventHandler() {

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idArea == 0L) {
                    salvarPatrimonio();
                } else {
                    alterarPatrimonio();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    @SuppressWarnings("unchecked")
    public void salvarPatrimonio() {

        areaService = getAreaService();
        area = getArea();
        setPatrimonioFromView();
        errors = (ModelResponse<ErrorsData>) areaService.validarDadosFromView(area);
        if (errors.isError()) {
            showErrorPatrimonioFromServidor();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            modelResponse = (ModelResponse<Area>) areaService.incluir(area);
            area = modelResponse.getObject();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Inclusão", JOptionPane.INFORMATION_MESSAGE);
        }
        limparPatrimonioView();
    }

    private void showErrorPatrimonioFromServidor() {

        for (ErrorsData erro : errors.getListObject()) {

            if (erro.getNumeroCampo() == 1) {
                mensagemNomeArea.setVisible(true);
                mensagemNomeArea.setForeground(Color.red);
                mensagemNomeArea.setText(erro.getShowMensagemErro());
                nomeArea.setBorder(BorderFactory.createLineBorder(Color.red, 2));

            }
            if (erro.getNumeroCampo() == 2) {
                mensagemTipoArea.setVisible(true);
                mensagemTipoArea.setForeground(Color.red);
                mensagemTipoArea.setText(erro.getShowMensagemErro());
                tipoArea.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            }
        }

    }


    @SuppressWarnings("unchecked")
    public void alterarPatrimonio() {

        areaService = getAreaService();
        area = getArea();
        area.setId(idArea);
        setPatrimonioFromView();
        modelResponse = (ModelResponse<Area>) areaService.alterar(area);
        if (modelResponse.isError()) {
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            area = modelResponse.getObject();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Alteração", JOptionPane.INFORMATION_MESSAGE);
        }
        limparPatrimonioView();
    }


    @SuppressWarnings("unchecked")
    public void excluirPatrimonio() {
        areaService = getAreaService();
        setPatrimonioFromView();
        modelResponse = (ModelResponse<Area>) areaService.excluir(area);
        if (modelResponse.isError()) {
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            area = modelResponse.getObject();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Exclusão", JOptionPane.INFORMATION_MESSAGE);
        }
        limparPatrimonioView();
    }

    @SuppressWarnings("unchecked")
    public void consultarArea(Long id) {
        areaService = getAreaService();
        area = getArea();
        modelResponse = (ModelResponse<Area>) areaService.consultaPorId(id);
        if (modelResponse.isError()) {
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            area = modelResponse.getObject();
            getAreaFromDataBase();
        }
    }


    private void getAreaFromDataBase() {
        idArea = area.getId();
        nomeArea.setText(area.getNomeArea());
        tipoArea.setText(area.getTipoArea().toString());
    }

    private void limparPatrimonioView() {
        idArea = 0L;
        nomeArea.setText("");
        tipoArea.setText("");
    }

    private void setPatrimonioFromView() {
        area.setNomeArea(nomeArea.getText());
        area.setTipoArea(Integer.parseInt(tipoArea.getText()));
    }

    public Area getArea() {
        return new Area();
    }

    public AreaService getAreaService() {
        return new AreaService();
    }

    public ModelResponse<Area> getModelResponse() {
        return modelResponse;
    }

    public void setModelResponse(ModelResponse<Area> modelResponse) {
        this.modelResponse = modelResponse;
    }
}
