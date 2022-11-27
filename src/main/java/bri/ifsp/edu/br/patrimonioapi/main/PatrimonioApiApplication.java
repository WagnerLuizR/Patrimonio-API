package bri.ifsp.edu.br.patrimonioapi.main;

import bri.ifsp.edu.br.patrimonioapi.view.patrimonio.MenuView;

public class PatrimonioApiApplication {
    public static void main(String[] args) {

        MenuView menuView = MenuView.getInstancia();
        menuView.setVisible(true);
    }
}
