package br.com.shadow.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.shadow.business.MediaMovelBusiness;
import br.com.shadow.entity.CotacaoAcao;
import br.com.shadow.vo.MediaMovelVO;

public class MyChart {

	public String geraGraficoAcao(String titulo, List<CotacaoAcao> cotacoes){
		XYSeries series = new XYSeries(titulo);
		
	    XYSeries mediaMenor = new XYSeries("MM Menor");
	    XYSeries mediaMaior = new XYSeries("MM Maior");
		Float menor = 5000f;
		Float maior = 0f;
		MediaMovelBusiness mmb = new MediaMovelBusiness();
		
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
		
		
		List<CotacaoAcao> cots = new ArrayList<CotacaoAcao>();
	    for(int i=0; i<cotacoes.size()-1; i++){
			Float valor = cotacoes.get(i).getValor();
			series.add(i, valor);

			if (valor>maior){
				maior = valor;
			}
			if (valor<menor){
				menor = valor;
			}
			
			//Adiciona as médias móveis.
			cots.add(cotacoes.get(i));
			
			MediaMovelVO mediaMovel = mmb.getMediaMovel(cots);
			if (mediaMovel!=null){
				mediaMenor.add(i, mediaMovel.getValorMediaMenor());
				mediaMaior.add(i, mediaMovel.getValorMediaMaior());
			}
		}

	    XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
		dataset.addSeries(mediaMenor);
		dataset.addSeries(mediaMaior);

        JFreeChart chart = ChartFactory.createXYLineChart(
            titulo, "", "valor", dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

        maior = (maior+(maior*1/200));
        menor = menor-(menor*1/200);
        
        XYPlot plot = chart.getXYPlot();
        plot.getRangeAxis().setRange(menor, maior);

        Util util = new Util();
		String path = util.getRealPath();
		String nomeArquivo = new Date().getTime()+".png";
		
		String nomeCompletoArquivo = path+File.separator+nomeArquivo;
		
		System.out.println("Nome completo arquivo a ser gerado: "+nomeCompletoArquivo);
		try {
			ChartUtilities.saveChartAsPNG(new File(nomeCompletoArquivo), chart, 600, 300);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
			e.printStackTrace();
		}
		
		return "graph/"+nomeArquivo;
	}
	
	public String geraGraficoAcaoTrava(String titulo, List<CotacaoAcao> cotacoes, Float exercComprado, Float exercVendido){
		XYSeries series = new XYSeries(titulo);
		//XYSeries series = new XYSeries("");
	    XYSeries linhaCompra = new XYSeries("Compra "+exercComprado);
	    XYSeries linhaVenda = new XYSeries("Venda"+exercVendido);
		Float menor = 5000f;
		Float maior = 0f;
		
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
	    for(int i=0; i<cotacoes.size()-1; i++){
			Float valor = cotacoes.get(i).getValor();
			series.add(i, valor);
			linhaCompra.add(i, exercComprado);
			linhaVenda.add(i, exercVendido);

			if (valor>maior){
				maior = valor;
			}
			if (valor<menor){
				menor = valor;
			}
		}

	    XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
		dataset.addSeries(linhaCompra);
		dataset.addSeries(linhaVenda);

        JFreeChart chart = ChartFactory.createXYLineChart(
            "--", "", "", dataset,
            PlotOrientation.VERTICAL,
            true, true, false);

        if (exercComprado<menor){
        	menor = exercComprado;
        }
        if (exercVendido<menor){
        	menor = exercVendido;
        }
        if (exercComprado>maior){
        	maior = exercComprado;
        }
        if (exercVendido>maior){
        	maior = exercVendido;
        }
        
        
        maior = (maior+(maior*1/200));
        menor = menor-(menor*1/200);
        
        XYPlot plot = chart.getXYPlot();
        plot.getRangeAxis().setRange(menor, maior);

        Util util = new Util();
		String path = util.getRealPath();
		String nomeArquivo = new Date().getTime()+".png";
		
		String nomeCompletoArquivo = path+File.separator+nomeArquivo;
		
		System.out.println("Nome completo arquivo a ser gerado: "+nomeCompletoArquivo);
		try {
			ChartUtilities.saveChartAsPNG(new File(nomeCompletoArquivo), chart, 400, 250);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
			e.printStackTrace();
		}
		return "graph/"+nomeArquivo;
	}
	
	
	
	public static void main(String[] args) {
		ChartData cd = new ChartData();
	}
	
	

	
}
