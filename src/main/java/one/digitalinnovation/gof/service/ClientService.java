package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Client;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author falvojr
 */
public interface ClientService {

	Iterable<Client> buscarTodos();

	Client buscarPorId(Long id);

	void inserir(Client cliente);

	void atualizar(Long id, Client cliente);

	void deletar(Long id);

}
