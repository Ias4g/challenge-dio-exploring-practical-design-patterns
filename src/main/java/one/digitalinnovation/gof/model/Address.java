package one.digitalinnovation.gof.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 * eu só traduzir para o ingles.
 * 
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 * 
 * @author izaelsilva
 */
@Entity
public class Address {

	@Id
	private String zipcode;
	private String publicPlace;
	private String complement;
	private String district;
	private String locality;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;

}
