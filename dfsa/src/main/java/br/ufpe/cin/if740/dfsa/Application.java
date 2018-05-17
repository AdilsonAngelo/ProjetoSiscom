package br.ufpe.cin.if740.dfsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	static final int NUM_TAGS = 100;
	static final int STEP = 100;
	static final int MAX_TAGS = 1000;
	static final int ITERATIONS = 2000;
	static final int INITIAL_FRAME_SIZE = 64;

	
	public static void main( String[] args ) throws IOException, URISyntaxException {
		setOutputFile();
        
		SpringApplication.run(Application.class, args);
	}

	static void setOutputFile() {
		String logPath = "assets" + File.separator + "log" + File.separator;
		File output = new File(logPath);
		if(!output.exists()) output.mkdirs();

		try {
			System.setOut(new PrintStream(new File(logPath + "out.log")));
			System.setErr(new PrintStream(new File(logPath + "err.log")));
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
}
