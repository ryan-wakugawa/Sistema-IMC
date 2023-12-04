package gui;

import dao.AlunoDAO;
import dao.HistoricoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoricoGUI extends JFrame {
    private JTable historico;
    private JPanel painel;
    private JButton voltarButton;
    private JTextField buscaCPF;
    private JButton buscarButton;
    private JButton resetButton;
    private final DefaultTableModel modelo = new DefaultTableModel();

    public HistoricoGUI() {
        setContentPane(painel);
        setVisible(true);
        setSize(600, 300);
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
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoricoDAO historicoDAO = new HistoricoDAO();
                modelo.setRowCount(0);
                historicoDAO.getAllFromCPF(buscaCPF.getText()).forEach(historicoPeso -> historicoPeso.addLinha(modelo));
                historico.repaint();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoricoDAO historicoDAO = new HistoricoDAO();
                modelo.setRowCount(0);
                historicoDAO.getAll().forEach(historicoPeso -> historicoPeso.addLinha(modelo));
                historico.repaint();
            }
        });
    }
    public static void main(String[] args) {
        new HistoricoGUI();
    }

    public void createTable() {
        modelo.addColumn("CPF");
        modelo.addColumn("NOME");
        modelo.addColumn("PESO");
        modelo.addColumn("ALTURA");
        modelo.addColumn("DATA");
        historico.setModel(modelo);

        HistoricoDAO historicoDAO = new HistoricoDAO();
        historicoDAO.getAll().forEach(historicoPeso -> historicoPeso.addLinha(modelo));
    }
}
