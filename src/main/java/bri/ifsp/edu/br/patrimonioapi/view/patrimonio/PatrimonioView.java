package bri.ifsp.edu.br.patrimonioapi.view.patrimonio;

import bri.ifsp.edu.br.patrimonioapi.config.Constantes;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.message.ModelResponse;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.service.AreaService;
import bri.ifsp.edu.br.patrimonioapi.service.PatrimonioService;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PatrimonioView extends JFrame {

    private static final long serialVersionUID = 6944798959841337479L;
    private JPanel contentPane;
    private JTextField descPatrimonio;
    private JTextField codigo;
    private JTextField estado;
    private JComboBox<String> nomeAreaCombo;

    private JButton btnSalvar;
    private JButton btnCancelar;

    private JLabel mensagemCodigo;
    private JLabel mensagemDescPatrimonio;
    private JLabel mensagemEstado;
    private JLabel mensagemArea;

    private Long codigoPatrimonio = 0L;
    private PatrimonioService patrimonioService;
    private Patrimonio patrimonio;

    private AreaService areaService;

    private ModelResponse<Patrimonio> modelResponse;

    private ModelResponse<ErrorsData> errors;


    /**
     * Create the frame.
     */
    public PatrimonioView(Patrimonio patrimonio, Integer opcaoCadastro) {

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
            consultarPatrimonio(patrimonio.getCodigo());
            btnSalvar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnSalvar.setBounds(178, 24, 149, 52);
            btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
            btnCancelar.setBounds(347, 24, 149, 52);
        } else if (opcaoCadastro == Constantes.EXCLUIR) {
            consultarPatrimonio(patrimonio.getCodigo());
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
        panel.setBackground(new Color(30, 144, 255));
        panel.setBounds(0, 0, 688, 523);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lbldescPatrimonio = new JLabel("Descrição:");
        lbldescPatrimonio.setHorizontalAlignment(SwingConstants.CENTER);
        lbldescPatrimonio.setFont(new Font("Verdana", Font.BOLD, 20));
        lbldescPatrimonio.setBounds(24, 60, 145, 36);
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
        descPatrimonio.setBounds(179, 60, 403, 36);
        panel.add(descPatrimonio);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setFont(new Font("Verdana", Font.BOLD, 20));
        lblEstado.setBounds(10, 126, 145, 36);
        panel.add(lblEstado);

        estado = new JTextField();
        estado.setFont(new Font("Verdana", Font.PLAIN, 20));
        estado.setColumns(10);
        estado.setBorder(null);
        estado.setBounds(179, 126, 403, 36);
        panel.add(estado);

        JLabel lblNomeArea = new JLabel("Área:");
        lblNomeArea.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeArea.setFont(new Font("Verdana", Font.BOLD, 20));
        lblNomeArea.setBounds(0, 192, 145, 36);
        panel.add(lblNomeArea);

        mensagemDescPatrimonio = new JLabel("");
        mensagemDescPatrimonio.setBounds(179, 60, 403, 14);
        panel.add(mensagemDescPatrimonio);

        mensagemEstado = new JLabel("");
        mensagemEstado.setBounds(179, 126, 403, 14);
        panel.add(mensagemEstado);

        mensagemArea = new JLabel("");
        mensagemArea.setBounds(179, 201, 403, 14);
        panel.add(mensagemArea);

        JComboBox<String> area = new JComboBox();
        area.setBounds(179, 196, 403, 36);
        areaService = getAreaService();
        List<Area> getArea  = areaService.listar();;
        getArea.forEach(a->{
            area.addItem(a.getNomeArea());
        });
        panel.add(area);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(new Color(0, 128, 0));
        panelBotoes.setBounds(0, 521, 696, 99);
        contentPane.add(panelBotoes);
        panelBotoes.setLayout(null);

        btnSalvar = new JButton();
        panelBotoes.add(btnSalvar);

        btnCancelar = new JButton();
        panelBotoes.add(btnCancelar);

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
    public void alterarPatrimonio() {

        patrimonioService = getPatrimonioService();
        patrimonio = getPatrimonio();
        patrimonio.setCodigo(codigoPatrimonio);
        setPatrimonioFromView();
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
    public void excluirPatrimonio() {
        patrimonioService = getPatrimonioService();
        setPatrimonioFromView();
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
        codigo.setText(patrimonio.getCodigo().toString());
        estado.setText(patrimonio.getEstado().toString());
    }

    private void limparPatrimonioView() {
        codigoPatrimonio = 0L;
        descPatrimonio.setText("");
        estado.setText("");
        nomeAreaCombo.addItem("");
    }

    private void setPatrimonioFromView() {
        areaService = getAreaService();
        List<Area> getArea  = areaService.listar();

        patrimonio.setCodigo(Long.getLong(codigo.getText()));
        patrimonio.setDescPatrimonio(descPatrimonio.getText());
        patrimonio.setEstado(Integer.getInteger(estado.getText()));
        getArea.forEach(a->{
            if(a.getNomeArea() == nomeAreaCombo.getSelectedItem()){
                patrimonio.setArea(a);
            }
        });
    }

    public Patrimonio getPatrimonio() {
        return new Patrimonio();
    }

    public PatrimonioService getPatrimonioService() {
        return new PatrimonioService();
    }
    public AreaService getAreaService(){
        return new AreaService();
    }

    public ModelResponse<Patrimonio> getModelResponse() {
        return modelResponse;
    }

    public void setModelResponse(ModelResponse<Patrimonio> modelResponse) {
        this.modelResponse = modelResponse;
    }
}
