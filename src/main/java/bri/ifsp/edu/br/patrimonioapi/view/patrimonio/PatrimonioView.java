package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.config.Constantes;
import bri.ifsp.edu.br.patrimonioapi.message.ModelResponse;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.serial.ConexaoSerial;
import bri.ifsp.edu.br.patrimonioapi.service.AreaService;
import bri.ifsp.edu.br.patrimonioapi.service.PatrimonioService;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;

public class PatrimonioView extends JFrame {

    private static final long serialVersionUID = 6944798959841337479L;
    private JPanel contentPane;
    private JTextField descPatrimonio;
    private JTextField codigo;
    private JTextField estado;
    private JComboBox<String> nomeAreaCombo;
    private String identificador;

    private JButton btnSalvar;
    private JButton btnCancelar;
    private JButton btnLeituraSerial;
    private JLabel mensagemCodigo;
    private JLabel mensagemDescPatrimonio;
    private JLabel mensagemEstado;
    private JLabel mensagemArea;

    private Long codigoPatrimonio = 0L;
    private PatrimonioService patrimonioService;
    private Patrimonio patrimonio;

    private AreaService areaService;
    private ConexaoSerial conexaoSerial;

    private ModelResponse<Patrimonio> modelResponse;

    private ModelResponse<ErrorsData> errors;
    private JLabel lblTelaPatrimonio;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;


    /**
     * Create the frame.
     */
    public PatrimonioView(Patrimonio patrimonio, Integer opcaoCadastro) {

        initComponents();

        eventHandler();

        btnSalvar.setText("Salvar");
        btnCancelar.setText("Cancelar");
        btnLeituraSerial.setText("Ler Serial");

        if (opcaoCadastro == Constantes.INCLUIR) {
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(28, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(220, 24, 149, 52);
            btnCancelar = new JButton("Cancelar");
            btnLeituraSerial.setText("Ler Serial");
            btnLeituraSerial.setFont(new Font("Verdana", Font.BOLD, 20));
            btnLeituraSerial.setBounds(420, 24, 149, 52);
        } else if (opcaoCadastro == Constantes.ALTERAR) {
            consultarPatrimonio(patrimonio.getCodigo());
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(178, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(347, 24, 149, 52);
        } else if (opcaoCadastro == Constantes.EXCLUIR) {
            excluirPatrimonio(patrimonio.getCodigo());
            btnSalvar.setText("Excluir");
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(178, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(347, 24, 149, 52);
        } else if (opcaoCadastro == Constantes.CONSULTAR) {
            consultarPatrimonio(patrimonio.getCodigo());
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
        panel.setBackground(new Color(48, 101, 172));
        panel.setBounds(0, 0, 688, 523);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lbldescPatrimonio = new JLabel("Descrição:");
        lbldescPatrimonio.setForeground(new Color(121, 210, 230));
        lbldescPatrimonio.setHorizontalAlignment(SwingConstants.CENTER);
        lbldescPatrimonio.setFont(new Font("Verdana", Font.BOLD, 20));
        lbldescPatrimonio.setBounds(23, 118, 145, 36);
        panel.add(lbldescPatrimonio);

        descPatrimonio = new JTextField();
        descPatrimonio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                descPatrimonio.setBorder(null);
                mensagemCodigo.setVisible(false);
            }
        });
        descPatrimonio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER ||
                        e.getKeyCode() == KeyEvent.VK_TAB) {
                    estado.requestFocus();
                }
            }
        });
        descPatrimonio.setBorder(null);
        descPatrimonio.setColumns(10);
        descPatrimonio.setFont(new Font("Verdana", Font.PLAIN, 20));
        descPatrimonio.setBounds(178, 118, 403, 36);
        panel.add(descPatrimonio);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setForeground(new Color(121, 210, 230));
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setFont(new Font("Verdana", Font.BOLD, 20));
        lblEstado.setBounds(10, 179, 145, 36);
        panel.add(lblEstado);

        estado = new JTextField();
        estado.setFont(new Font("Verdana", Font.PLAIN, 20));
        estado.setColumns(10);
        estado.setBorder(null);
        estado.setBounds(179, 179, 403, 36);
        panel.add(estado);

        JLabel lblNomeArea = new JLabel("Área:");
        lblNomeArea.setForeground(new Color(121, 210, 230));
        lblNomeArea.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeArea.setFont(new Font("Verdana", Font.BOLD, 20));
        lblNomeArea.setBounds(10, 238, 145, 36);
        panel.add(lblNomeArea);

        mensagemDescPatrimonio = new JLabel("");
        mensagemDescPatrimonio.setBounds(178, 128, 403, 14);
        panel.add(mensagemDescPatrimonio);

        mensagemEstado = new JLabel("");
        mensagemEstado.setBounds(179, 187, 403, 14);
        panel.add(mensagemEstado);

        mensagemArea = new JLabel("");
        mensagemArea.setBounds(179, 248, 403, 14);
        panel.add(mensagemArea);

        nomeAreaCombo = new JComboBox();
        nomeAreaCombo.setBounds(179, 238, 403, 36);
        areaService = getAreaService();
        List<Area> getArea = areaService.listar();
        ;
        getArea.forEach(a -> {
            nomeAreaCombo.addItem(a.getNomeArea());
        });
        panel.add(nomeAreaCombo);
        
        lblTelaPatrimonio = new JLabel("Cadastro de Patrimônio");
        lblTelaPatrimonio.setForeground(new Color(121, 210, 230));
        lblTelaPatrimonio.setBackground(new Color(121, 210,2));
        lblTelaPatrimonio.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTelaPatrimonio.setBounds(23, 11, 453, 38);

        panel.add(lblTelaPatrimonio);
        
        lblNewLabel = new JLabel("Para Preecher o Estado, Digite:");
        lblNewLabel.setForeground(new Color(121, 210, 230));
        lblNewLabel.setBounds(59, 303, 197, 36);
        panel.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("1 - Patrimônio Novo");
        lblNewLabel_1.setForeground(new Color(121, 210, 230));
        lblNewLabel_1.setBounds(59, 332, 181, 36);
        panel.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("2 - Patrimônio Usado (já com alguns defeitos)");
        lblNewLabel_2.setForeground(new Color(121, 210, 230));
        lblNewLabel_2.setBounds(59, 367, 257, 14);
        panel.add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("3  - Patrimônio Danificado ou Quebrado (inutilizavel)");
        lblNewLabel_3.setForeground(new Color(121, 210, 230));
        lblNewLabel_3.setBounds(59, 392, 257, 14);
        panel.add(lblNewLabel_3);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(new Color(121, 210, 230));
        panelBotoes.setBounds(0, 521, 696, 99);
        contentPane.add(panelBotoes);
        panelBotoes.setLayout(null);

        btnSalvar = new JButton();
        panelBotoes.add(btnSalvar);

        btnCancelar = new JButton();
        panelBotoes.add(btnCancelar);

        btnLeituraSerial = new JButton();
        panelBotoes.add(btnLeituraSerial);

        mensagemDescPatrimonio.setVisible(false);
        mensagemEstado.setVisible(false);
        mensagemArea.setVisible(false);
    }

    private void eventHandler() {

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (codigoPatrimonio == 0L) {
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

        btnLeituraSerial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conectarPortaSerial();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void salvarPatrimonio() {

        patrimonioService = getPatrimonioService();
        patrimonio = getPatrimonio();
        setPatrimonioFromView();
        errors = (ModelResponse<ErrorsData>) patrimonioService.validarDadosFromView(patrimonio);
        if (errors.isError()) {
            showErrorPatrimonioFromServidor();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            modelResponse = (ModelResponse<Patrimonio>) patrimonioService.incluir(patrimonio);
            patrimonio = modelResponse.getObject();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Inclusão", JOptionPane.INFORMATION_MESSAGE);
        }
        limparPatrimonioView();
    }

    private void showErrorPatrimonioFromServidor() {

        for (ErrorsData erro : errors.getListObject()) {

            if (erro.getNumeroCampo() == 1) {
                mensagemDescPatrimonio.setVisible(true);
                mensagemDescPatrimonio.setForeground(Color.red);
                mensagemDescPatrimonio.setText(erro.getShowMensagemErro());
                descPatrimonio.setBorder(BorderFactory.createLineBorder(Color.red, 2));

            }
            if (erro.getNumeroCampo() == 2) {
                mensagemEstado.setVisible(true);
                mensagemEstado.setForeground(Color.red);
                mensagemEstado.setText(erro.getShowMensagemErro());
                estado.setBorder(BorderFactory.createLineBorder(Color.red, 2));
            }
            if (erro.getNumeroCampo() == 3) {
                mensagemArea.setVisible(true);
                mensagemArea.setText(erro.getShowMensagemErro());
            }
        }

    }

    @SuppressWarnings("unchecked")
    public void conectarPortaSerial() throws InterruptedException {
        patrimonio = getPatrimonio();
        ConexaoSerial serial = new ConexaoSerial();
        identificador = "D1F4G5";
        patrimonio.setIdentificador(identificador);
        if (serial.iniciaSerial()) {
            //String identificador = serial.getProtocolo().getCodigoPatrimonio();
            Thread.sleep(5000);
        }
    }

    @SuppressWarnings("unchecked")
    public void alterarPatrimonio() {

        patrimonioService = getPatrimonioService();
        patrimonio = getPatrimonio();
        setPatrimonioFromView();
        patrimonio.setCodigo(codigoPatrimonio);
        modelResponse = (ModelResponse<Patrimonio>) patrimonioService.alterar(patrimonio);
        if (modelResponse.isError()) {
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            patrimonio = modelResponse.getObject();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Alteração", JOptionPane.INFORMATION_MESSAGE);
        }
        limparPatrimonioView();
    }


    @SuppressWarnings("unchecked")
    public void excluirPatrimonio(Long id) {
        patrimonioService = getPatrimonioService();
        patrimonio = getPatrimonio();
        patrimonio.setCodigo(id);
        modelResponse = (ModelResponse<Patrimonio>) patrimonioService.excluir(patrimonio);
        if (modelResponse.isError()) {
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            patrimonio = modelResponse.getObject();
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Exclusão", JOptionPane.INFORMATION_MESSAGE);
        }
        limparPatrimonioView();
    }

    @SuppressWarnings("unchecked")
    public void consultarPatrimonio(Long id) {
        patrimonioService = getPatrimonioService();
        patrimonio = getPatrimonio();
        modelResponse = (ModelResponse<Patrimonio>) patrimonioService.consultaPorId(id);
        if (modelResponse.isError()) {
            JOptionPane.showMessageDialog(null, modelResponse.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            patrimonio = modelResponse.getObject();
            getPatrimonioFromDataBase();
        }
    }

    public void listarArea() {
        areaService = getAreaService();
        areaService.listar();
    }

    private void getPatrimonioFromDataBase() {
        codigoPatrimonio = patrimonio.getCodigo();
        descPatrimonio.setText(patrimonio.getDescPatrimonio());
        estado.setText(patrimonio.getEstado().toString());
        areaService = getAreaService();
        Object descArea = nomeAreaCombo.getSelectedItem();
        List<Area> getArea = areaService.listar();
        getArea.forEach(a -> {
            if (Objects.equals(a.getNomeArea(), descArea)) {
                patrimonio.setIdArea(a.getId());
            }
        });
    }

    private void limparPatrimonioView() {
        codigoPatrimonio = 0L;
        descPatrimonio.setText("");
        estado.setText("");
        nomeAreaCombo.addItem("");
    }

    private void setPatrimonioFromView() {
        patrimonio = getPatrimonio();
        areaService = getAreaService();
        List<Area> getArea = areaService.listar();
        Object descArea = nomeAreaCombo.getSelectedItem();
        patrimonio.setIdentificador(identificador);
        patrimonio.setDescPatrimonio(descPatrimonio.getText());
        patrimonio.setEstado(Integer.parseInt(estado.getText()));
        getArea.forEach(a -> {
            if (Objects.equals(a.getNomeArea(), descArea)) {
                patrimonio.setIdArea(a.getId());
            }
        });
    }

    public Patrimonio getPatrimonio() {
        return new Patrimonio();
    }

    public PatrimonioService getPatrimonioService() {
        return new PatrimonioService();
    }

    public AreaService getAreaService() {
        return new AreaService();
    }

    public ConexaoSerial getConexaoSerial() {
        return new ConexaoSerial();
    }

    public ModelResponse<Patrimonio> getModelResponse() {
        return modelResponse;
    }

    public void setModelResponse(ModelResponse<Patrimonio> modelResponse) {
        this.modelResponse = modelResponse;
    }
}
