import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {

    public static void main(String[] args) {

        Path folder = Paths.get("logs");
        String keyword = "ERROR";

        // Create sample log files
        try {
            Files.createDirectories(folder);

            Files.write(
                folder.resolve("app1.txt"),
                List.of(
                    "INFO: Application started",
                    "ERROR: Database connection failed",
                    "INFO: Retrying connection",
                    "ERROR: Connection timeout"
                )
            );

            Files.write(
                folder.resolve("app2.txt"),
                List.of(
                    "INFO: User logged in",
                    "WARNING: Low memory",
                    "ERROR: File not found"
                )
            );

            Files.write(
                folder.resolve("app3.txt"),
                List.of(
                    "INFO: Server running",
                    "INFO: Request received",
                    "INFO: Request completed"
                )
            );

        } catch (IOException e) {
            System.out.println("Error creating log files: " + e.getMessage());
            return;
        }

        int totalLines = 0;
        int keywordCount = 0;

        System.out.println("Log File Analysis");
        System.out.println("--------------------");

        // Read all .txt files
        try (DirectoryStream<Path> files =
                     Files.newDirectoryStream(folder, "*.txt")) {

            for (Path file : files) {

                // Read file attributes
                BasicFileAttributes attributes =
                        Files.readAttributes(
                                file,
                                BasicFileAttributes.class
                        );

                System.out.println("\nFile: " + file.getFileName());
                System.out.println(
                        "Size: " + attributes.size() + " bytes"
                );

                int fileLines = 0;
                int fileKeywordCount = 0;

                // Read each line
                for (String line : Files.readAllLines(file)) {

                    fileLines++;

                    if (line.contains(keyword)) {
                        fileKeywordCount++;
                    }
                }

                totalLines += fileLines;
                keywordCount += fileKeywordCount;

                System.out.println("Total lines: " + fileLines);
                System.out.println(
                        "Lines containing " + keyword + ": "
                                + fileKeywordCount
                );
            }

            System.out.println("\n====================");
            System.out.println("Overall Summary");
            System.out.println("====================");
            System.out.println("Total lines: " + totalLines);
            System.out.println(
                    "Lines containing " + keyword + ": " + keywordCount
            );

        } catch (IOException e) {
            System.out.println("Error analyzing files: " + e.getMessage());
        }
    }
}
