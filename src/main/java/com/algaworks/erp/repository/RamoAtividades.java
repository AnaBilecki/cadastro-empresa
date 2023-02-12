package com.algaworks.erp.repository;

import java.io.Serializable;
import java.util.List;

import com.algaworks.erp.model.RamoAtividade;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RamoAtividades implements Serializable {
	private static final long serialVersionUID = 1L;

	private EntityManager manager;
	
	public RamoAtividades() {
	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<RamoAtividade> buscarPorDescricao(String descricao) {
		String jpql = "SELECT r FROM RamoAtividade r WHERE descricao LIKE :descricao";
		
		TypedQuery<RamoAtividade> query = manager.createQuery(jpql, RamoAtividade.class);
		
		query.setParameter("descricao", descricao + "%");
		
		return query.getResultList();
	}
}
