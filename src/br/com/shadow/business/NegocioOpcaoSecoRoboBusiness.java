package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import br.com.shadow.dao.NegocioOpcaoSecoDAO;
import br.com.shadow.entity.NegocioOpcaoSeco;
import br.com.shadow.util.HibernateUtil;

public class NegocioOpcaoSecoRoboBusiness {

	
	public NegocioOpcaoSeco getNegocioOpcaoSecoPendenteByRobo(String nomeRobo){
		Session session = HibernateUtil.openSession();
		NegocioOpcaoSecoDAO cd = new NegocioOpcaoSecoDAO(session, NegocioOpcaoSeco.class);
		List<NegocioOpcaoSeco> opcoes = cd.getNegociosOpcaoSecoByUser(nomeRobo); 
		List<NegocioOpcaoSeco> opcoesSel = new ArrayList<NegocioOpcaoSeco>();
		
		if (opcoes!=null && !opcoes.isEmpty()){
			for (NegocioOpcaoSeco opcao : opcoes){
				if (opcao.getDataVenda()==null){
					opcoesSel.add(opcao);
				}
			}
		}
		session.close();
		
		if (opcoesSel!=null && !opcoesSel.isEmpty()){
			return opcoesSel.get(0);
		}
		return null;
	}
}
