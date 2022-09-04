package br.com.kerberossec.clientesCRUD.dao;

import java.util.List;

import br.com.kerberossec.clientesCRUD.model.Cliente;

public interface ClienteDAO {
	public void adicionarCliente(Cliente cliente);
	public List<Cliente> listarClientes();
	public Cliente getCliente(String cpf);
	public void excluirCliente(Cliente cliente);
	public void atualizarCliente(Cliente cliente);
}
