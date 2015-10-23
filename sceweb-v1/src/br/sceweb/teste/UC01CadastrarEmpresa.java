package br.sceweb.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	static public EmpresaDAO empresaDAO;
	static public Empresa empresa;
	@BeforeClass
	
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("Empresa 1");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("Empr 1");
		empresa.setEndereco("Rua da Empresa 1, SP");
		empresa.setTelefone("2722-4595");
	}

	@Test
	public void CT01UC01FBCadastrarEmpresa_com_sucesso() {
		empresaDAO.exclui("89424232000180");
		assertEquals(1, empresaDAO.adiciona(empresa));
		empresaDAO.exclui("89424232000180");
	}
	
	@Test(expected = RuntimeException.class)
	public void CT02UC01A2Cadastra_empresa_cnpj_ja_cadastrado(){
		empresaDAO.adiciona(empresa);
		assertEquals(0, empresaDAO.adiciona(empresa));
		empresaDAO.exclui("89424232000180");
	}
	
	@Test
	public void CT03UC01A3Cadastra_empresa_cnpj_invalido(){
		Empresa empresa2 = new Empresa();
		try{
		empresa2.setCnpj("25525525525A");
		fail("deveria disparar uma exception");
		}catch(Exception e){
			assertEquals("CNPJ inválido!", e.getMessage());
		}
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("89424232000180");
	}


}
