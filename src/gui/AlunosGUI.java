package gui;

import dao.AlunoDAO;
import dao.HistoricoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlunosGUI extends JFrame {
    private JPanel painel;
    private JTable alunos;
    private JButton voltarButton;
    private JTextField buscaCPF;
    private JButton buscarButton;
    private JButton resetButton;
    private final DefaultTableModel modelo = new DefaultTableModel();


    public AlunosGUI() {
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
                AlunoDAO alunoDAO = new AlunoDAO();
                modelo.setRowCount(0);
                alunoDAO.getAllFromCPF(buscaCPF.getText()).forEach(aluno -> aluno.addLinha(modelo));
                alunos.repaint();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoDAO alunoDAO = new AlunoDAO();
                modelo.setRowCount(0);
                alunoDAO.getAll().forEach(aluno -> aluno.addLinha(modelo));
                alunos.repaint();
            }
        });
    }

    public static void main(String[] args) {
        new AlunosGUI();
    }

    public void createTable() {
        modelo.addColumn("CPF");
        modelo.addColumn("NOME");
        modelo.addColumn("NASCIMENTO");
        modelo.addColumn("PESO");
        modelo.addColumn("ALTURA");
        alunos.setModel(modelo);

        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.getAll().forEach(aluno -> aluno.addLinha(modelo));
    }
}
