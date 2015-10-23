package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sceweb.servico.FabricaDeConexoes;

public class EmpresaDAO {

	public int adiciona(Empresa empresa) {

		PreparedStatement ps;
		int codigoRetorno = 0;

		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn
					.prepareStatement("INSERT INTO Empresa(cnpj,"
							+ " nomeDaEmpresa, nomeFantasia, endereco, telefone) VALUES(?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}

	public int exclui(String cnpj) {
		Connection conn = new FabricaDeConexoes().getConnection();
		int codigoRetorno = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM"
					+ " Empresa WHERE cnpj = ?");
			ps.setString(1, cnpj);
			codigoRetorno = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;

	}

	public List<Empresa> consultaEmpresa() {
		Connection con = new FabricaDeConexoes().getConnection();
		Empresa empresa = new Empresa();
		List<Empresa> lista = new ArrayList<Empresa>();
		String sql = "SELECT * FROM Empresa";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				empresa.setCnpj(rs.getString("cnpj"));
				empresa.setNomeDaEmpresa(rs.getString("nomeDaEmpresa"));
				empresa.setNomeFantasia(rs.getString("nomeFantasia"));
				empresa.setEndereco(rs.getString("endereco"));
				empresa.setTelefone(rs.getString("telefone"));
				lista.add(empresa);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
