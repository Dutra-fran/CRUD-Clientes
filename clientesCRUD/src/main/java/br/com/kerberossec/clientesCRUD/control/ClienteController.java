package br.com.kerberossec.clientesCRUD.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.kerberossec.clientesCRUD.dao.ClienteDAO;
import br.com.kerberossec.clientesCRUD.dao.ClienteDAOImpl;
import br.com.kerberossec.clientesCRUD.model.Cliente;

@ManagedBean(name="clienteController")
@RequestScoped
public class ClienteController {
	private Cliente cliente;
	private Cliente clienteEspecifico;
	private DataModel<Cliente> listaClientes;
	private String msg;
	
	public DataModel<Cliente> getListaClientes() {
		ClienteDAO dao = new ClienteDAOImpl();
		List<Cliente> lista = dao.listarClientes();
		listaClientes = new ListDataModel<Cliente>(lista);
		return listaClientes;
	}

	// Aqui é a parte do encapsulamento do atributo "cliente".
	public Cliente getCliente() {
		return cliente;
	}
	
	// Aqui é a busca pela nossa regra de negócio para um cliente específico.
	public String procurarCliente() {
		ClienteDAO dao = new ClienteDAOImpl();
		Cliente c = dao.getCliente(cliente.getCpf());
		
		if(c == null)
			setMsg("Cliente não encontrado!");
		
		setClienteEspecifico(c);
		return "procurar_cliente";
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String adicionarCliente() {
		ClienteDAO dao = new ClienteDAOImpl();
		dao.adicionarCliente(cliente);
		
		setMsg("Salvo com sucesso!");
		return "adicionar_cliente";
	}
	
	public String excluirCliente() {
		Cliente c = (Cliente)(listaClientes.getRowData());
		ClienteDAO dao = new ClienteDAOImpl();
		dao.excluirCliente(c);
		
		setMsg("Exclusão realizada com sucesso!");
		return "index";
	}
	
	public String atualizarCliente() {
		return null;
	}

	public String getMsg() {
		return msg;
	}
	
	public String listarForm() {
		//setMsg(null);
		return "index";
	}
	
	public String adicionarForm() {
		cliente = new Cliente();
		//setMsg(null);
		
		return "adicionar_cliente";
	}
	
	public String procurarForm() {
		cliente = new Cliente();
		//setMsg(null);
		
		return "procurar_cliente";
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Cliente getClienteEspecifico() {
		return clienteEspecifico;
	}

	public void setClienteEspecifico(Cliente clienteEspecifico) {
		this.clienteEspecifico = clienteEspecifico;
	}

	@PostConstruct
	public void init() {
		cliente = new Cliente();
	}
}
