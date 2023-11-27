package modelo;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.Date;

public class Aluno {
    String cpf;
    String nome;
    String dataNascimento;
    double peso;
    double altura;
    public Aluno(String cpf, String nome, String dataNascimento, double peso, double altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    public void print(){
        System.out.println(getCpf()+" "+getNome()+" "+getDataNascimento()+" "+getPeso()+"kg "+getAltura()+"m");
    }

    public void addLinha(DefaultTableModel model){
        model.addRow((new Object[]{
                getCpf(),
                getNome(),
                getDataNascimento(),
                getPeso(),
                getAltura(),
        }));
    }
}
