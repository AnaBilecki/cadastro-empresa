package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.erp.model.Empresa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Empresas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Empresas() {
	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Empresa buscarPorId(Long id) {
		return manager.find(Empresa.class, id);
	}
	
	public List<Empresa> buscarPorRazaoSocial(String nome) {
		String jpql = "SELECT e FROM Empresa e WHERE razaoSocial LIKE :razaoSocial";
		
		TypedQuery<Empresa> query = manager.createQuery(jpql, Empresa.class);
		
		query.setParameter("razaoSocial", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Empresa> listar() {
		return manager.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
	}
	
	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);
	}
	
	public void remover(Empresa empresa) {
		empresa = this.buscarPorId(empresa.getId());
		manager.remove(empresa);
	}
}
