package br.com.shadow.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.shadow.entity.CotacaoAcao;



	
public class ChartData {
	
	public String geraGraficoAcao(String titulo, List<CotacaoAcao> cotacoes, Integer mmMenor, Integer mmMaior){
		XYSeries series = new XYSeries(titulo);
	    XYSeries mediaMenor = new XYSeries("MM "+mmMenor);
	    XYSeries mediaMaior = new XYSeries("MM"+mmMaior);
		
		if (cotacoes==null || cotacoes.isEmpty()){
			return null;
		}
	    for(int i=0; i<cotacoes.size()-1; i++){
			Float valor = cotacoes.get(i).getValor();
			series.add(i, valor);

			if (i>mmMenor){
				float soma = 0f;
				for(int j=i; i>i-mmMenor; j--){
					soma += cotacoes.get(j).getValor();
				}
				mediaMenor.add(i, soma/mmMenor);
			}
			if (i>mmMaior){
				float soma = 0f;
				for(int j=i; i>i-mmMaior; j--){
					soma += cotacoes.get(j).getValor();
				}
				mediaMaior.add(i, soma/mmMaior);
			}
			
		}
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		dataset.addSeries(mediaMenor);
		dataset.addSeries(mediaMaior);
		dataset.setAutoWidth(true);
		
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart(
				titulo, // Title
				"", // x-axis Label
				"valor", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
		);
		
		Util util = new Util();
		String path = util.getRealPath();
		String nomeArquivo = new Date().getTime()+".jpg";
		
		String nomeCompletoArquivo = path+File.separator+nomeArquivo;
		
		System.out.println("Nome completo arquivo a ser gerado: "+nomeCompletoArquivo);
		try {
			ChartUtilities.saveChartAsJPEG(new File(nomeCompletoArquivo), chart, 800, 500);
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
		








