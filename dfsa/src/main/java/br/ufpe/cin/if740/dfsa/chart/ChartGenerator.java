package br.ufpe.cin.if740.dfsa.chart;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufpe.cin.if740.dfsa.domain.Estimate;

public class ChartGenerator {
	public static void createChart(Map<String, List<Estimate>> map, double minX, double maxX, double stepX) {
		
		List<D3ChartData> lTotal, lEmpty, lCollision, lEff, lTime;
		lTotal = lEmpty = lCollision = lEff = lTime = new LinkedList<D3ChartData>();
		
		for(Entry<String, List<Estimate>> entry : map.entrySet()) {
			double[] xAll = new double[entry.getValue().size()], yTotal = new double[entry.getValue().size()], yEmpty = new double[entry.getValue().size()], yCollision = new double[entry.getValue().size()], yEff = new double[entry.getValue().size()], yTime = new double[entry.getValue().size()];
			
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
			
			lTotal.add(new D3ChartData(xAll, yTotal, entry.getKey()));
			lEmpty.add(new D3ChartData(xAll, yEmpty, entry.getKey()));
			lCollision.add(new D3ChartData(xAll, yCollision, entry.getKey()));
			lEff.add(new D3ChartData(xAll, yEff, entry.getKey()));
			lTime.add(new D3ChartData(xAll, yTime, entry.getKey()));
		}

		new ChartHelper("total.json", lTotal).create();
		new ChartHelper("empty.json", lEmpty).create();
		new ChartHelper("collision.json", lCollision).create();
		new ChartHelper("eff.json", lEff).create();
		new ChartHelper("time.json", lTime).create();
	}
}
