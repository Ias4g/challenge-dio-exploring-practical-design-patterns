package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Client;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author izaelsilva
 */
public interface ClientService {

	Iterable<Client> searchAll();

	Client searchById(Long id);

	void save(Client cliente);

	void update(Long id, Client cliente);

	void delete(Long id);

}
