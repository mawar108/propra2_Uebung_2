import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
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


	public static void readFile(String dateiname){
		Path p = Path.of(dateiname);
		try {
			Stream<String> lineStream = Files.lines(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

	}



}
