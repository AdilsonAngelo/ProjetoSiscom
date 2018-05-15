package br.ufpe.cin.if740.dfsa.chart;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import br.ufpe.cin.if740.dfsa.domain.Estimate;

public class ChartGenerator {
	public static void createChart(Map<String, List<Estimate>> map, double minX, double maxX, double stepX) {
		
		List<ChartData> cdList = new LinkedList<ChartData>(); 
		
		for(Entry<String, List<Estimate>> entry : map.entrySet()) {
			ChartData cd = new ChartData(entry.getKey());
			
			double[] xAll = new double[entry.getValue().size()],
					yTotal = new double[entry.getValue().size()],
					yEmpty = new double[entry.getValue().size()],
					yCollision = new double[entry.getValue().size()],
					yEff = new double[entry.getValue().size()],
					yTime = new double[entry.getValue().size()];
			
			double tempX = minX;
			int i = 0;
			for(Estimate e : entry.getValue()) {
				xAll[i] = tempX;
				yTotal[i] = e.getAvgTotalSlots();
				yEmpty[i] = e.getAvgEmptySlots();
				yCollision[i] = e.getAvgCollisionSlots();
				yEff[i] = e.getEfficiency() * 100;
				yTime[i] = e.getAvgTime();
				i++;
				tempX += stepX;
			}

			cd.setxAxis(xAll);
			cd.setTotal(yTotal);
			cd.setEmpty(yEmpty);
			cd.setCollision(yCollision);
			cd.setEfficiency(yEff);
			cd.setTime(yTime);
			
			cdList.add(cd);
		}
		
		Gson gson = new Gson();
		
		String path = "assets" + File.separator + "view" + File.separator;
		File file = new File(path);
		if(!file.exists()) file.mkdirs();
		
		Writer writer;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(path + "data.json"), StandardCharsets.UTF_8));
			
			gson.toJson(cdList, writer);
			
			writer.flush();
			writer.close();
			
			Desktop.getDesktop().browse(new File(path + "index.html").toURI());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}