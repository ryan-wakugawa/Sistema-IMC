package modelo;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoricoPeso {
    String cpf;
    String nome;
    double peso;
    double altura;
    String data;

    public HistoricoPeso(String cpf, String nome, double peso, double altura, String data) {
        this.cpf = cpf;
        this.nome = nome;
        this.peso = peso;
        this.altura = altura;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String resultadoIMC(double imc){
        if (imc < 18.5){
            return "Magreza";
        } else if (18.5<=imc && imc<25) {
            return "Normal";
        } else if (25<=imc && imc<30) {
            return "Sobrepeso";
        } else if (30<=imc && imc<35) {
            return "Obesidade grau I";
        } else if (35<=imc && imc<40) {
            return "Obesidade grau II";
        }
        return "Obesidade grau III";
    };

    public void calcularIMC(){
        File txt = new File(nome+LocalDate.now()+".txt");
        double imc = peso/Math.pow(altura, 2);
        String resultado = resultadoIMC(imc);
        try {
            FileWriter writer = new FileWriter(nome+LocalDate.now()+".txt");
            writer.write("CPF: "+cpf+" Nome: "+nome+" IMC: "+String.format("%.2f",imc)+" Resultado: "+resultado+" Dia: "+LocalDate.now());
            writer.close();
        }
        catch (IOException e){
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }

    }

    public void addLinha(DefaultTableModel model){
        model.addRow((new Object[]{
                getCpf(),
                getNome(),
                getPeso(),
                getAltura(),
                getData()
        }));
    }
}
