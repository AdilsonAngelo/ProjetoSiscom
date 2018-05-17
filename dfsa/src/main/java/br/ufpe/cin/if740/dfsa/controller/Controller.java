package br.ufpe.cin.if740.dfsa.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpe.cin.if740.dfsa.domain.Filter;
import br.ufpe.cin.if740.dfsa.domain.Result;
import br.ufpe.cin.if740.dfsa.domain.SimulatorWrapper;

@RestController
public class Controller {

	@RequestMapping("/")
	public String index() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("assets" + File.separator + "view"  + File.separator + "index.html"));
		return new String(encoded, "UTF-8");
	}
	
	@RequestMapping("/{filename}")
	public byte[] getFile(@PathVariable("filename") String fileName) throws IOException {
		String filePath = "assets" + File.separator + "view"  + File.separator + fileName;
		File file = new File(filePath);
		if(file.exists()) {
			return Files.readAllBytes(Paths.get(filePath));
		}
		return new byte[4];
	}

//	@RequestMapping("/loading.gif")
//	public @ResponseBody byte[] loadingGif() throws IOException {
//		byte[] encoded = Files.readAllBytes(Paths.get("assets" + File.separator + "view"  + File.separator + "loading.gif"));
//		return encoded;
//	}
	
	@PostMapping(value = "/submit-filter")
	public @ResponseBody List<Result> submitFilter(@RequestBody Filter filter) {
		return SimulatorWrapper.getResults(filter);
	}

}
