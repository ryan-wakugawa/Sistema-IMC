package dao;

import factory.ConnectionFactory;
import modelo.HistoricoPeso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {
    private final Connection connection;

    public HistoricoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void add(HistoricoPeso historico) throws SQLException {
        String sql = "INSERT INTO historico(cpf, nome, peso, altura, dia) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, historico.getCpf());
            statement.setString(2, historico.getNome());
            statement.setDouble(3, historico.getPeso());
            statement.setDouble(4, historico.getAltura());
            statement.setString(5, "" + LocalDate.now());
            statement.execute();
            statement.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public List<HistoricoPeso> getAll() {
        List<HistoricoPeso> registros = new ArrayList<HistoricoPeso>();
        String sql = "SELECT * FROM historico";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getDouble("peso"), resultSet.getDouble("altura"), resultSet.getString("dia"));
                registros.add(historicoPeso);
            }
            return registros;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public List<HistoricoPeso> getAllFromCPF(String cpf) {
        List<HistoricoPeso> registros = new ArrayList<HistoricoPeso>();
        String sql = "SELECT * FROM historico WHERE cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getDouble("peso"), resultSet.getDouble("altura"), resultSet.getString("dia"));
                registros.add(historicoPeso);
            }
            return registros;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
