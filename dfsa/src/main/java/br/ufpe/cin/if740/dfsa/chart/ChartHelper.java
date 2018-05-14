package br.ufpe.cin.if740.dfsa.chart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;

public class ChartHelper {

	private String filename;
	private Gson gson;
	private List<D3ChartData> chartData;

	public ChartHelper(String filename,List<D3ChartData> chartData) {
		this.filename = filename;
		this.gson = new Gson();
		this.chartData = chartData;
	}

	public void create() {
		String path = "assets" + File.separator + "view" + File.separator;
		File file = new File(path);
		if(!file.exists()) file.mkdirs();

		Writer writer;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(path + this.filename), StandardCharsets.UTF_8));

			gson.toJson(this.chartData, writer);

			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<D3ChartData> getChartData() {
		return chartData;
	}

	public void setChartData(List<D3ChartData> chartData) {
		this.chartData = chartData;
	}

}
