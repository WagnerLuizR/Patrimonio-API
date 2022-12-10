package bri.ifsp.edu.br.patrimonioapi.serial;

import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ConexaoSerial implements SerialPortEventListener {

    private SerialPort serialPort;
    private Protocolo protocolo = new Protocolo();
    private CommPortIdentifier identificadorPorta;
    private CommPort commPort;
    private List<String> portas;
    private static final int TIME_OUT = 1000;
    private static int DATA_RATE = 9600;
    private String serialPortName = "COM1";
    private String appName ="teste";
    private BufferedReader leitura;
    private OutputStream escrita;

    private boolean portOpen = false;
    private int baudRate = 0;
    private int dataBits = 0;
    private int paridade = 0;
    private int stopBits = 0;


    public ConexaoSerial() {
        this.baudRate = 9600;
        this.dataBits = SerialPort.DATABITS_8;
        this.paridade = SerialPort.PARITY_NONE;
        this.stopBits = SerialPort.STOPBITS_1;
    }

    public ConexaoSerial(int baudRate, int dataBits, int paridade, int stopBits) {
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.paridade = paridade;
        this.stopBits = stopBits;
    }

    public boolean iniciaSerial() {
        boolean status = false;

        try {
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            while (portId == null && portEnum.hasMoreElements()) {
                CommPortIdentifier currentPortId = (CommPortIdentifier) portEnum.nextElement();
                if (currentPortId.getName().equals(serialPortName) || currentPortId.getName().startsWith(serialPortName)) {
                    serialPort = (SerialPort) currentPortId.open(appName, TIME_OUT);
                    portId = currentPortId;
                    System.out.println("conectado em " + currentPortId.getName());
                    break;
                }
            }

            if (portId == null || serialPort == null) {
                return false;
            }
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            status = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            status = false;
        }
        return status;
    }


    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public CommPortIdentifier getIdentificadorPorta() {
        return identificadorPorta;
    }

    public void setIdentificadorPorta(CommPortIdentifier identificadorPorta) {
        this.identificadorPorta = identificadorPorta;
    }

    public CommPort getCommPort() {
        return commPort;
    }

    public void setCommPort(CommPort commPort) {
        this.commPort = commPort;
    }

    public List<String> findPortas() {

        portas = new ArrayList<String>();
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            identificadorPorta = (CommPortIdentifier) portList.nextElement();
            if (identificadorPorta.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portas.add(identificadorPorta.getName());
            }
        }
        return portas;
    }


    public boolean openConnection(String porta) {
        System.out.println("tentando abrir a porta ");
        if (!existe(porta)) {
            return false;
        }
        if (portOpen == true) {
            portOpen = false;
            close();
        }
        if (portOpen == false) {
            try {
                identificadorPorta = CommPortIdentifier.getPortIdentifier(porta);
                if (identificadorPorta.isCurrentlyOwned()) {
                    portOpen = true;
                    return portOpen;
                }
                if (portOpen == false) {
                    System.out.println("abrindo a porta ");
                    commPort = identificadorPorta.open("", 2000);
                    serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(baudRate, dataBits, stopBits, paridade);
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    leitura = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                    escrita = serialPort.getOutputStream();
                    serialPort.addEventListener(this);
                    serialPort.notifyOnDataAvailable(true);
                    portOpen = true;
                    System.out.println("porta aberta " + portOpen);
                }

            } catch (PortInUseException e) {
                e.printStackTrace();
            } catch (UnsupportedCommOperationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return portOpen;
    }

    private boolean existe(String porta) {
        try {
            identificadorPorta = CommPortIdentifier.getPortIdentifier(porta);
        } catch (NoSuchPortException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void sendData(String palavra) {
        System.out.println("tentado enviar mensagem >>>>>>>>>" + palavra + " " + portOpen);
        try {
            if (portOpen) {
                System.out.println("escrevendo na serial >>>>>>");
                escrita.write(palavra.getBytes());
                escrita.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            if (portOpen) {
                leitura.read();
                leitura.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        try {
            switch (event.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    if (leitura == null) {
                        leitura = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                    }
                    protocolo.setLeituraComando(leitura.readLine());
                    System.out.println("chegou: " + protocolo.getLeituraComando());
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public String getSerialPortName() {
        return serialPortName;
    }

    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }

    public static int getDataRate() {
        return DATA_RATE;
    }

    public static void setDataRate(int dataRate) {
        DATA_RATE = dataRate;
    }
}
