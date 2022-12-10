package bri.ifsp.edu.br.patrimonioapi.view.serial;

import bri.ifsp.edu.br.patrimonioapi.serial.ConexaoSerial;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Comunicacao extends JFrame {

    private JButton btnCancelar;
    private JPanel contentPane;

    private static final long serialVersionUID = -5722548462663769970L;

    private String baudRate[] = {"300", "600", "1200", "2400", "9600", "14400", "19200", "38400", "57600", "115200"};

    private String dataBits[] = {"5", "6", "7", "8"};

    private String paridade[] = {"0", "1", "2", "3", "4"};

    private String stopBits[] = {"0", "1", "2", "3"};

    private String porta = new String();

    private boolean portOpen = false;

    private JButton btnSync;
    private int intBaudRate = 0;
    private int intDataBits = 0;
    private int intParidade = 0;
    private int intStopBits = 0;

    private String diretorio = null;

    private JLabel lblVelocidadePorta;
    private JComboBox<String> cbPorta;
    private JLabel lblNewLabel;
    private JComboBox<String> cbBaudRate;
    private JLabel lblDataBits;
    private JComboBox<String> cbDataBits;
    private JLabel lblParidade;
    private JComboBox<String> cbParidade;
    private JComboBox<String> cbStopBits;
    private JLabel lblStopBits;
    private ConexaoSerial conexaoSerial;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Comunicacao frame = new Comunicacao();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Comunicacao() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 844, 483);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        lblVelocidadePorta = new JLabel("Porta:");
        lblVelocidadePorta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVelocidadePorta.setBounds(57, 31, 143, 14);
        contentPane.add(lblVelocidadePorta);

        cbPorta = new JComboBox<String>();
        cbPorta.setBounds(210, 28, 218, 20);
        contentPane.add(cbPorta);

        lerPorta();

        lblNewLabel = new JLabel("Baud Rate:");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(107, 62, 93, 14);
        contentPane.add(lblNewLabel);

        cbBaudRate = new JComboBox<String>();
        cbBaudRate.setBounds(210, 59, 218, 20);
        contentPane.add(cbBaudRate);

        lerBaudRate();

        lblDataBits = new JLabel("Data Bits:");
        lblDataBits.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDataBits.setBounds(107, 90, 93, 14);
        contentPane.add(lblDataBits);

        cbDataBits = new JComboBox<String>();
        cbDataBits.setBounds(210, 87, 218, 20);
        contentPane.add(cbDataBits);

        lerDataBits();

        lblParidade = new JLabel("Paridade:");
        lblParidade.setHorizontalAlignment(SwingConstants.RIGHT);
        lblParidade.setBounds(107, 125, 93, 14);
        contentPane.add(lblParidade);

        cbParidade = new JComboBox<String>();
        cbParidade.setBounds(210, 122, 218, 20);
        contentPane.add(cbParidade);

        lerParidade();

        cbStopBits = new JComboBox<String>();
        cbStopBits.setBounds(210, 153, 218, 20);
        contentPane.add(cbStopBits);

        lblStopBits = new JLabel("Stop Bits:");
        lblStopBits.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStopBits.setBounds(107, 156, 93, 14);
        contentPane.add(lblStopBits);

        lerStopBits();

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(new Color(0, 128, 0));
        panelBotoes.setBounds(0, 521, 696, 99);
        contentPane.add(panelBotoes);
        panelBotoes.setLayout(null);

        btnSync = new JButton();
        btnSync.setText("Excluir");
        btnSync.setFont(new Font("Verdana", Font.BOLD, 20));
        btnSync.setBounds(178, 24, 149, 52);
        panelBotoes.add(btnSync);

        btnCancelar = new JButton();
        btnCancelar.setFont(new Font("Verdana", Font.BOLD, 20));
        btnCancelar.setBounds(347, 24, 149, 52);
        ;
        panelBotoes.add(btnCancelar);

        setContentPane(contentPane);
    }

    private void lerPorta() {
        conexaoSerial = getConexaoSerial();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        cbPorta.removeAllItems();
        for (String str : conexaoSerial.findPortas()) {
            model.addElement(str);
        }
        cbPorta.setModel(model);
    }

    private void lerBaudRate() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        cbBaudRate.removeAllItems();
        for (String str : this.getBaudRate()) {
            model.addElement(str);
        }
        cbBaudRate.setModel(model);
    }

    private void lerDataBits() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        cbDataBits.removeAllItems();
        for (String str : this.getDataBits()) {
            model.addElement(str);
        }
        cbDataBits.setModel(model);
    }

    private void lerParidade() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        cbParidade.removeAllItems();
        for (String str : this.getParidade()) {
            model.addElement(str);
        }
        cbParidade.setModel(model);
    }

    private void lerStopBits() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        cbStopBits.removeAllItems();
        for (String str : this.getStopBits()) {
            model.addElement(str);
        }
        cbStopBits.setModel(model);

    }

    private void eventHandler() {
        btnSync.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conexaoSerial = getConexaoSerial();
                conexaoSerial.readData();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public String[] getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(String[] baudRate) {
        this.baudRate = baudRate;
    }

    public String[] getDataBits() {
        return dataBits;
    }

    public void setDataBits(String[] dataBits) {
        this.dataBits = dataBits;
    }

    public String[] getParidade() {
        return paridade;
    }

    public void setParidade(String[] paridade) {
        this.paridade = paridade;
    }

    public String[] getStopBits() {
        return stopBits;
    }

    public void setStopBits(String[] stopBits) {
        this.stopBits = stopBits;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public boolean isPortOpen() {
        return portOpen;
    }

    public void setPortOpen(boolean portOpen) {
        this.portOpen = portOpen;
    }

    public int getIntBaudRate() {
        return intBaudRate;
    }

    public void setIntBaudRate(int intBaudRate) {
        this.intBaudRate = intBaudRate;
    }

    public int getIntDataBits() {
        return intDataBits;
    }

    public void setIntDataBits(int intDataBits) {
        this.intDataBits = intDataBits;
    }

    public int getIntParidade() {
        return intParidade;
    }

    public void setIntParidade(int intParidade) {
        this.intParidade = intParidade;
    }

    public int getIntStopBits() {
        return intStopBits;
    }

    public void setIntStopBits(int intStopBits) {
        this.intStopBits = intStopBits;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public ConexaoSerial getConexaoSerial() {
        return this.conexaoSerial = new ConexaoSerial();
    }


}
