package br.sceweb.teste;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Convenio;

public class UC03CadastrarConvenio {
	static Convenio convenio;

	static String dataInicio;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		convenio = new Convenio();

	}

	@Test
	public void CT01UC05CadastrarConvenio_com_sucesso_data_valida() {

		dataInicio = "29/10/2015";
		assertTrue(convenio.validaData(dataInicio));

	}

	@Test(expected = IllegalArgumentException.class)
	public void CT02UC05CadastrarConvenio_com_data_invalida() {

		dataInicio = "42/04/2015";
		assertFalse(convenio.validaData(dataInicio));

	}

	@Test
	public void CT03UC05CadastrarConvenio_com_sucesso_data_valida() {

		dataInicio = "29/10/2015";
		convenio.setDataInicio(dataInicio);
		assertTrue(dataInicio.equals(convenio.getDataInicio()));

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

}
