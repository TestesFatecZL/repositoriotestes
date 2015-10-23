package br.sceweb.teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {
	private static EmpresaDAO EmpresaDao;
	private static Empresa objEsperado;
	private static Empresa objEsperado2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EmpresaDao = new EmpresaDAO();
		objEsperado = new Empresa();
		objEsperado.setCnpj("14290151000136");
		objEsperado.setNomeDaEmpresa("Casas Bahia LTDA");
		objEsperado.setNomeFantasia("Casas Bahia");
		objEsperado.setEndereco("Rua Candido Esperando, 495");
		objEsperado.setTelefone("0800 11 55 33");
		
		objEsperado2 = new Empresa();
		objEsperado2.setCnpj("77459948000134");
		objEsperado2.setNomeDaEmpresa("Pernambucanas LTDA");
		objEsperado2.setNomeFantasia("Pernambucanas");
		objEsperado2.setEndereco("Rua Candido Esperando, 15");
		objEsperado2.setTelefone("0800 12 53 33");
	}

	@Test
	public void CT01UC02FBConsultarEmpresa_com_sucesso() {
		EmpresaDao.adiciona(objEsperado);
		EmpresaDao.adiciona(objEsperado2);
		assertTrue(EmpresaDao.consultaEmpresa().size() == 2);
		EmpresaDao.exclui("14290151000136");
		EmpresaDao.exclui("77459948000134");
	}
	
	@Test
	public void CT02UC02A1ConsultarEmpresa_sem_sucesso() {
		EmpresaDao.adiciona(objEsperado);
		assertFalse(EmpresaDao.consultaEmpresa().size() > 1);
		EmpresaDao.exclui("14290151000136");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EmpresaDao.exclui("14290151000136");
		EmpresaDao.exclui("77459948000134");
		
	}



}
