package dao;

import factory.ConnectionFactory;
import modelo.Aluno;
import modelo.HistoricoPeso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection connection;
    public AlunoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public void add(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO alunos(cpf, nome, dataNascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, aluno.getCpf());
            statement.setString(2, aluno.getNome());
            statement.setString(3, aluno.getDataNascimento());
            statement.setDouble(4, aluno.getPeso());
            statement.setDouble(5, aluno.getAltura());
            statement.execute();
            statement.close();
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void update(Aluno aluno) throws SQLException{
        String sql = "UPDATE alunos SET nome = ?, dataNascimento = ?, peso = ?, altura = ? WHERE cpf = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getDataNascimento());
            statement.setDouble(3, aluno.getPeso());
            statement.setDouble(4, aluno.getAltura());
            statement.setString(5, aluno.getCpf());
            statement.execute();
            statement.close();
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }

    public void delete(String cpf) throws SQLException{
        String sql = "DELETE FROM alunos WHERE cpf=?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.execute();
            statement.close();
        }
        catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
    public List<Aluno> getAll(){
        List<Aluno> registros = new ArrayList<Aluno>();
        String sql = "SELECT * FROM alunos";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                Aluno aluno = new Aluno(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("dataNascimento"), resultSet.getDouble("peso"), resultSet.getDouble("altura"));
                registros.add(aluno);
            }
            return registros;
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public List<Aluno> getAllFromCPF(String cpf){
        List<Aluno> registros = new ArrayList<Aluno>();
        String sql = "SELECT * FROM alunos WHERE cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,cpf);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                Aluno aluno = new Aluno(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("dataNascimento"), resultSet.getDouble("peso"), resultSet.getDouble("altura"));
                registros.add(aluno);
            }
            return registros;
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
