import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.TimeZone;
import java.util.stream.Stream;

public class BeispielCode {

  public static void main(String[] args) {
    // Inhalt der build.gradle Datei ausgeben
    Path p = Path.of("build.gradle");
    try {
      Stream<String> lineStream = Files.lines(p);
      lineStream.forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

    long btf2 = 1445444940;
    LocalDateTime time = LocalDateTime
        .ofInstant(Instant.ofEpochSecond(btf2), TimeZone.getTimeZone("UTC").toZoneId());
    System.out.println(time);

    int kalenderwoche = time.get(WeekFields.ISO.weekOfWeekBasedYear());
    System.out.println(kalenderwoche);


  }


}
