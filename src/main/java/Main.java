import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	static class Eintrag{
		LocalDateTime zeitstempel;
		long zeit;

		public Eintrag(String zeitstempel, String zeit) {
			this.zeitstempel = LocalDateTime
					.ofInstant(Instant.ofEpochSecond(Long.parseLong(zeitstempel)), TimeZone.getTimeZone("UTC").toZoneId());
			this.zeit = Long.parseLong(zeit);
		}
	}


	public static List<Eintrag> readFile(String dateiname){
		Path p = Path.of(dateiname);
		try {
			Stream<String> lineStream = Files.lines(p);
			AtomicInteger index = new AtomicInteger(0);
			Collection<List<String>> values = lineStream
					.collect(Collectors.groupingBy(e -> index.getAndIncrement() / 2))
					.values();

			List<Eintrag> eintraege = values
					.stream()
					.map(l -> new Eintrag(l.get(0), l.get(1)))
					.collect(Collectors.toList());

			return eintraege;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}


	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		readFile("odyssey.txt");
		long dauer = System.currentTimeMillis() - start;
		System.out.println("Dauer: " + dauer);
	}



}
