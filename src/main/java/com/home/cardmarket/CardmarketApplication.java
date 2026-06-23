package com.home.cardmarket;

import org.jsoup.nodes.Document;

import com.home.cardmarket.common.UrlCaller;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Slf4j
@Command(name = "card-market-calculator", mixinStandardHelpOptions = true, version = "1.0", description = "A command-line application for calculating card market values.")
public class CardmarketApplication implements Runnable {

	@Option(names = "--url")
	private String url;

	@Override
	public void run() {
		UrlCaller.getByUrl(url, Document.class);
	}

	public static void main(String[] args) {
		int exitCode = new CommandLine(new CardmarketApplication()).execute(args);
		System.exit(exitCode);
	}

}
