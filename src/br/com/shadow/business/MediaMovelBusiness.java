package br.com.shadow.business;

import java.util.ArrayList;
import java.util.List;

import br.com.shadow.entity.Acao;
import br.com.shadow.entity.Admin;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.vo.MediaMovelVO;

public class MediaMovelBusiness {
	
	public List<Acao> getAcoesComMMFavoravel(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<Acao> acoesMM = new ArrayList<Acao>();
		for(Acao acao : acoes){
			MediaMovelVO mm = getMediaMovelByRobo(acao); 
			if (mm !=null && mm.getFator()>1){
				acoesMM.add(acao);
			}
		}
		return acoesMM;
	}
	
	public List<Acao> getAcoesComMMDesfavoravel(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<Acao> acoesMM = new ArrayList<Acao>();
		for(Acao acao : acoes){
			MediaMovelVO mm = getMediaMovelByRobo(acao); 
			if (mm.getFator()<1){
				acoesMM.add(acao);
			}
		}
		return acoesMM;
	}
	
	public MediaMovelVO getMediaMovelByRobo(Acao acao){
		
		MediaMovelVO mediaMovel = new MediaMovelVO();
		mediaMovel.setAcao(acao);
		
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesDoDiaByAcaoByRobo(acao.getCode());
		
		return getMediaMovel(cotacoes);
	}
	
	public MediaMovelVO getMediaMovel(Acao acao){
		
		MediaMovelVO mediaMovel = new MediaMovelVO();
		mediaMovel.setAcao(acao);
		
		CotacaoAcaoBusiness cab = new CotacaoAcaoBusiness();
		List<CotacaoAcao> cotacoes = cab.getCotacoesDoDiaByAcaoByRobo(acao.getCode());
		
		return getMediaMovel(cotacoes);
	}

	
	public MediaMovelVO getMediaMovel(List<CotacaoAcao> cotacoes){

		Integer MM_MENOR;
		Integer MM_MAIOR;
		AdminBusiness ab = new AdminBusiness();
		Admin admin = ab.getAdmin();
		MM_MENOR = admin.getMediaMovelMenor();
		MM_MAIOR = admin.getMediaMovelMaior();
		
		MediaMovelVO mediaMovel = new MediaMovelVO();
		
		if (cotacoes==null || cotacoes.isEmpty() || cotacoes.size()<=MM_MAIOR){
			return null;
		}
		Float somaMenor = 0f;
		for(int j=cotacoes.size()-MM_MENOR-1; j<cotacoes.size()-1; j++){
			somaMenor += cotacoes.get(j).getValor();
		}
		somaMenor = somaMenor/MM_MENOR;
		
		Float somaMaior = 0f;
		for(int j=cotacoes.size()-MM_MAIOR-1; j<cotacoes.size()-1; j++){
			somaMaior += cotacoes.get(j).getValor();
		}
		somaMaior = somaMaior/MM_MAIOR;
		
		mediaMovel.setValorMediaMenor(somaMenor);
		mediaMovel.setValorMediaMaior(somaMaior);
		

		return mediaMovel;
	}
	
	
	
	public MediaMovelVO getMediaMovel(String code){
		AcaoBusiness ab = new AcaoBusiness();
		Acao acao = ab.getByCodeByRobo(code);
		return getMediaMovel(acao);
	}

	public MediaMovelVO getMediaMovelByRobo(String code){
		AcaoBusiness ab = new AcaoBusiness();
		Acao acao = ab.getByCodeByRobo(code);
		return getMediaMovelByRobo(acao);
	}
	
	public MediaMovelVO getMelhorMediaMovelByRobo(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<MediaMovelVO> mediasMoveis = new ArrayList<MediaMovelVO>();
		for(Acao acao : acoes){
			mediasMoveis.add(getMediaMovel(acao));
		}
		float maiorFator = 0;
		MediaMovelVO maiorMedia = null;
		for(MediaMovelVO mmovel : mediasMoveis){
			float fator = mmovel.getFator();
			if (fator>1 && fator>maiorFator){
				maiorMedia = mmovel;
				maiorFator = mmovel.getFator();
			}
		}
		return maiorMedia;		
	}
	
	public List<MediaMovelVO> getMediasMoveis(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<MediaMovelVO> mediasMoveis = new ArrayList<MediaMovelVO>();
		for(Acao acao : acoes){
			mediasMoveis.add(getMediaMovel(acao));
		}
		return mediasMoveis;		
	}

	
	public MediaMovelVO getPiorMediaMovel(){
		AcaoBusiness ab = new AcaoBusiness();
		List<Acao> acoes = ab.getAcoesByRobo();
		List<MediaMovelVO> mediasMoveis = new ArrayList<MediaMovelVO>();
		for(Acao acao : acoes){
			mediasMoveis.add(getMediaMovel(acao));
		}
		float menorFator = 10;
		MediaMovelVO menorMedia = null;
		for(MediaMovelVO mmovel : mediasMoveis){
			float fator = mmovel.getFator();
			if (fator<1 && fator<menorFator){
				menorMedia = mmovel;
				menorFator = mmovel.getFator();
			}
		}
		return menorMedia;		
	}

	
}
