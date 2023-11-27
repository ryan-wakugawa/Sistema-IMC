package gui;

import dao.AlunoDAO;
import dao.HistoricoDAO;
import modelo.Aluno;
import modelo.HistoricoPeso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

public class MainGUI extends JFrame {
    private JFormattedTextField cpf;
    private JButton cadastrar;
    private JTextField nome;
    private JPanel MainPanel;
    private JTextField peso;
    private JTextField altura;
    private JTextField nascimento;
    private JButton atualizarButton;
    private JButton deletarButton;
    private JButton consultarButton;
    private JButton calcularIMCButton;
    private JButton consultarHistoricoButton;

    public MainGUI() {
        setContentPane(MainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);

        //Máscara do CPF
        /*MaskFormatter maskCpf = null;
        try {
            maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            System.err.println("Erro na formatação: " + e.getMessage());
            System.exit(-1);
        }
        JFormattedTextField cpf = new JFormattedTextField(maskCpf);
         */

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nome.getText().isEmpty() || cpf.getText().isEmpty() || peso.getText().isEmpty() || altura.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    Aluno aluno = new Aluno(cpf.getText(), nome.getText(), nascimento.getText(), Double.parseDouble(peso.getText()), Double.parseDouble(altura.getText()));
                    AlunoDAO dao = new AlunoDAO();

                    HistoricoPeso historicoPeso = new HistoricoPeso(cpf.getText(), nome.getText(), Double.parseDouble(peso.getText()), Double.parseDouble(altura.getText()), "" + LocalDate.now());
                    HistoricoDAO histDAO = new HistoricoDAO();
                    try {
                        dao.add(aluno);
                        histDAO.add(historicoPeso);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    cpf.setText("");
                    nome.setText("");
                    nascimento.setText("");
                    peso.setText("");
                    altura.setText("");
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado");
                }
            }
        });
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nome.getText().isEmpty() || cpf.getText().isEmpty() || peso.getText().isEmpty() || altura.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    Aluno aluno = new Aluno(cpf.getText(), nome.getText(), nascimento.getText(), Double.parseDouble(peso.getText()), Double.parseDouble(altura.getText()));
                    AlunoDAO dao = new AlunoDAO();
                    HistoricoPeso historicoPeso = new HistoricoPeso(cpf.getText(), nome.getText(), Double.parseDouble(peso.getText()), Double.parseDouble(altura.getText()), "" + LocalDate.now());
                    HistoricoDAO histDAO = new HistoricoDAO();
                    try {
                        dao.update(aluno);
                        histDAO.add(historicoPeso);
                        historicoPeso.calcularIMC();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    cpf.setText("");
                    nome.setText("");
                    nascimento.setText("");
                    peso.setText("");
                    altura.setText("");
                    JOptionPane.showMessageDialog(null, "Aluno atualizado");
                }
            }
        });
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!cpf.getText().isEmpty() && cpf.getText().length() == 11) {
                    AlunoDAO dao = new AlunoDAO();
                    try {
                        dao.delete(cpf.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, "Aluno de CPF " + cpf.getText() + " deletado.");
                    cpf.setText("");
                    nome.setText("");
                    nascimento.setText("");
                    peso.setText("");
                    altura.setText("");
                } else if (cpf.getText().length() != 11 && !cpf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um CPF valido");
                } else {
                    JOptionPane.showMessageDialog(null, "Digite um CPF para deletar o usuário correspondente.");
                }
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlunosGUI();
                dispose();
            }
        });
        calcularIMCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoricoDAO historicoDAO = new HistoricoDAO();
                double imc = historicoDAO.getAllFromCPF(cpf.getText()).getLast().getPeso() / (Math.pow(historicoDAO.getAllFromCPF(cpf.getText()).getLast().getAltura(), 2));
                if (!cpf.getText().isEmpty()) {
                    historicoDAO.getAllFromCPF(cpf.getText()).getLast().calcularIMC();
                    JOptionPane.showMessageDialog(null, "IMC: " + historicoDAO.getAllFromCPF(cpf.getText()).getLast().resultadoIMC(imc));
                }
            }
        });
        consultarHistoricoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistoricoGUI();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
