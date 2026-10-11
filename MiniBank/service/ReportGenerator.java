package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {

    private ReportGenerator() {
    }

    public static String generate(
            Path logsFolder,
            Path reportFile) throws IOException {

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        StringBuilder report = new StringBuilder();

        report.append("========== END OF DAY REPORT ==========\n");

        if (Files.exists(logsFolder)) {

            try (DirectoryStream<Path> stream =
                         Files.newDirectoryStream(logsFolder)) {

                for (Path file : stream) {

                    if (!Files.isRegularFile(file)) {
                        continue;
                    }

                    report.append("\nFile: ")
                            .append(file.getFileName())
                            .append("\n");

                    BasicFileAttributes attributes =
                            Files.readAttributes(
                                    file,
                                    BasicFileAttributes.class
                            );

                    report.append("Size: ")
                            .append(attributes.size())
                            .append(" bytes\n");

                    report.append("Last Modified: ")
                            .append(attributes.lastModifiedTime())
                            .append("\n");

                    try (BufferedReader reader =
                                 Files.newBufferedReader(
                                         file,
                                         StandardCharsets.UTF_8)) {

                        String line;

                        while ((line = reader.readLine()) != null) {

                            String[] parts =
                                    line.trim().split("\\s+");

                            if (parts.length != 2) {
                                continue;
                            }

                            try {

                                long amount =
                                        Long.parseLong(parts[1]);

                                if (amount <= 0) {
                                    continue;
                                }

                                if (parts[0].equalsIgnoreCase("DEPOSIT")) {

                                    totalDeposits =
                                            Math.addExact(
                                                    totalDeposits,
                                                    amount
                                            );

                                } else if (
                                        parts[0].equalsIgnoreCase("WITHDRAW")) {

                                    totalWithdrawals =
                                            Math.addExact(
                                                    totalWithdrawals,
                                                    amount
                                            );
                                }

                            } catch (NumberFormatException e) {

                                report.append(
                                        "Skipped invalid amount: "
                                ).append(line).append("\n");

                            } catch (ArithmeticException e) {

                                throw new IOException(
                                        "Transaction total overflow.",
                                        e
                                );
                            }
                        }
                    }
                }
            }

        } else {

            report.append("Log directory does not exist.\n");
        }

        report.append("\nTotal Deposits: ₹")
                .append(totalDeposits);

        report.append("\nTotal Withdrawals: ₹")
                .append(totalWithdrawals);

        report.append("\nNet Movement: ₹")
                .append(totalDeposits - totalWithdrawals);

        report.append("\n=======================================\n");

        Path parent = reportFile.toAbsolutePath().getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(
                reportFile,
                report.toString(),
                StandardCharsets.UTF_8
        );

        return report.toString();
    }
}
