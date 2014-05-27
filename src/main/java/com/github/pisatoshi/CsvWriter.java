package com.github.pisatoshi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvWriter {
    private CsvWriter() {
    }

    public static void write(String filename, AggregateResult result) {
        Path outputCsv = FileSystems.getDefault().getPath(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(outputCsv, StandardCharsets.UTF_8)) {
            bw.write(result.getCsvColumns() + "\n");
            bw.write(result.getCsvRecord() + "\n");
        } catch (IOException ex) {
        }
    }
}
