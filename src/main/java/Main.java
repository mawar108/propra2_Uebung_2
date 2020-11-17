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

		@Override
		public String toString() {
			return "Eintrag{" +
					"zeitstempel=" + zeitstempel +
					", zeit=" + zeit +
					'}';
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
					.parallel()
					.map(l -> new Eintrag(l.get(0), l.get(1)))
					.collect(Collectors.toList());

			return eintraege;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public static void minMaxAvgTime(List<Eintrag> eintraege)
	{
		Optional<Long> min = eintraege.stream()
				.map(e -> e.zeit)
				.min(Long::compare);

		Optional<Long> max = eintraege.stream()
				.map(e -> e.zeit)
				.max(Long::compare);

		OptionalDouble avg = eintraege.stream()
				.map(e -> e.zeit)
				.mapToDouble(l -> (double)l)
				.average();

		System.out.println("min: " + min.get());
		System.out.println("max: " + max.get());
		System.out.println("avg: " + avg.getAsDouble());
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Eintrag> eintraege = readFile("odyssey.txt");
		long dauer = System.currentTimeMillis() - start;
		System.out.println("Dauer: " + dauer);
		minMaxAvgTime(eintraege);
	}



}
