package com.home.cardmarket.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class CsvWriter {

    public void write(String filePath, String fileName, List<List<String>> rows) {
        if (filePath == null || filePath.isBlank()) {
            log.error("CSV file path must not be empty");
            return;
        }

        if (rows == null) {
            log.error("CSV rows must not be null");
            return;
        }

        Path path = Path.of(filePath + "\\" + fileName + ".csv");
        Path parent = path.getParent();

        try {
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                for (List<String> row : rows) {
                    writer.write(toCsvLine(row));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            log.error("Could not write CSV file: {}", filePath, e);
            return;
        }
    }

    private String toCsvLine(List<String> row) {
        if (row == null) {
            return "";
        }

        return row.stream()
                .map(this::escapeCell)
                .collect(Collectors.joining(","));
    }

    private String escapeCell(String cell) {
        if (cell == null) {
            return "";
        }

        boolean mustQuote = cell.contains(",")
                || cell.contains("\"")
                || cell.contains("\n")
                || cell.contains("\r");

        if (!mustQuote) {
            return cell;
        }

        return "\"" + cell.replace("\"", "\"\"") + "\"";
    }
}
