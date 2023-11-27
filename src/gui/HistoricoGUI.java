package gui;

import dao.HistoricoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoricoGUI extends JFrame{
    private JTable historico;
    private JPanel painel;
    private JButton voltarButton;
    private DefaultTableModel modelo = new DefaultTableModel();

    public HistoricoGUI() {
        setContentPane(painel);
        setVisible(true);
        setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        HistoricoDAO historicoDAO = new HistoricoDAO();
        createTable();
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                dispose();
            }
        });
    }

    public void createTable(){
        modelo.addColumn("CPF");
        modelo.addColumn("NOME");
        modelo.addColumn("PESO");
        modelo.addColumn("ALTURA");
        modelo.addColumn("DATA");
        historico.setModel(modelo);

        HistoricoDAO historicoDAO = new HistoricoDAO();
        historicoDAO.getAll().forEach(historicoPeso -> historicoPeso.addLinha(modelo));
    }


    /*
        historico.("Data");
        modelo.addColumn("Usuario");
        modelo.addColumn("Descrição");

    // Adiciona os registros a tabela
        for (Registro reg : registroDAO.getAll()) {
        modelo.addRow(new Object[]{
                reg.getData(),
                usuarioDAO.getById(reg.getIdUsuario()).getUsuario(),
                reg.getDescricao()
        });

    }
    */
    public static void main(String[] args) {
        new HistoricoGUI();
    }
}
