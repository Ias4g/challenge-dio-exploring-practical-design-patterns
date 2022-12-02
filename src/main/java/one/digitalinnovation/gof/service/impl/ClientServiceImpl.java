package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.model.ClientRepository;
import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.AddressRepository;
import one.digitalinnovation.gof.service.ClientService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClientService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author izaelsilva
 */
@Service
public class ClientServiceImpl implements ClientService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ViaCepService viaCepService;

	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Client> searchAll() {
		// Buscar todos os Clientes.
		return clientRepository.findAll();
	}

	@Override
	public Client searchById(Long id) {
		// Buscar Cliente por ID.
		Optional<Client> cliente = clientRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void save(Client client) {
		saveClientWithCep(client);
	}

	@Override
	public void update(Long id, Client client) {
		// Buscar Cliente por ID, caso exista:
		Optional<Client> clientBd = clientRepository.findById(id);
		if (clientBd.isPresent()) {
			saveClientWithCep(client);
		}
	}

	@Override
	public void delete(Long id) {
		// Deletar Cliente por ID.
		clientRepository.deleteById(id);
	}

	private void saveClientWithCep(Client client) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String zipcode = client.getAddress().getZipcode();
		Address address = addressRepository.findById(zipcode).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Address newAddress = viaCepService.consultCep(zipcode);
			addressRepository.save(newAddress);
			return newAddress;
		});
		client.setAddress(address);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		clientRepository.save(client);
	}

}
