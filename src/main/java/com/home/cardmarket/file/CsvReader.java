package com.home.cardmarket.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.home.cardmarket.commandhandler.CsvHeaderEnum;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class CsvReader {

    public List<Map<CsvHeaderEnum, String>> read(String filePath) {
        if (Objects.isNull(filePath) || filePath.isBlank()) {
            log.error("CSV file path must not be empty");
            return new ArrayList<>();
        }

        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            log.error("CSV file does not exist: {}", filePath);
            return new ArrayList<>();
        }

        List<Map<CsvHeaderEnum, String>> rows = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            Map<CsvHeaderEnum, String> headerWithIndex = Map.of();
            while (Objects.nonNull(line = reader.readLine())) {
                if (headerWithIndex.isEmpty()) {
                    headerWithIndex = parseHeader(line);
                } else {
                    rows.add(parseCsvLine(line, headerWithIndex));
                }
            }
        } catch (IOException e) {
            log.error("Could not read CSV file: {}", filePath, e);
            return new ArrayList<>();
        }

        log.debug("Rows: {}", rows);
        log.debug("Read {} rows from CSV file: {}", rows.size(), filePath);

        return rows;
    }

    private Map<CsvHeaderEnum, String> parseHeader(String line) {
        List<String> headers = parseCsvLineToList(line);
        Map<CsvHeaderEnum, String> headerWithIndex = Stream.of(CsvHeaderEnum.values()).collect(Collectors.toMap(
                header -> header,
                header -> headers.indexOf(header.header) + ""));
        return headerWithIndex;
    }

    private Map<CsvHeaderEnum, String> parseCsvLine(String line, Map<CsvHeaderEnum, String> headerWithIndex) {
        List<String> cells = parseCsvLineToList(line);
        return headerWithIndex.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> cells.get(Integer.parseInt(entry.getValue()))));
    }

    private List<String> parseCsvLineToList(String line) {
        return Stream.of(line.split(",", -1)).collect(Collectors.toList());
    }
}
