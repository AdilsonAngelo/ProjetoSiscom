package br.ufpe.cin.if740.dfsa.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/")
	public String index() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("assets" + File.separator + "view"  + File.separator + "index.html"));
		return new String(encoded, "UTF-8");
	}

	@RequestMapping("/index.js")
	public String script() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("assets" + File.separator + "view"  + File.separator + "index.js"));
		return new String(encoded, "UTF-8");
	}
	
	@RequestMapping("/data.json")
	public String dataset() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("assets" + File.separator + "view"  + File.separator + "data.json"));
		return new String(encoded, "UTF-8");
	}

}
