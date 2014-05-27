package com.github.pisatoshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class GCLogAggregator {
    public static void main(String[] args) {
        // TODO: commandline arguments validate needed.
        String inputFilename = args[0];
        String outputFilename = args[1];

        AggregateResult result = new AggregateResult();
        Path inputLog = FileSystems.getDefault().getPath(inputFilename);
        try (BufferedReader br = Files.newBufferedReader(inputLog, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                LogEntry entry = LogEntry.getInstance(line);
                if (entry == null) {
                    continue;
                }
                System.out.println(entry.getSource());
                result.add(entry);
            }
        } catch (IOException ex) {
        }
        CsvWriter.write(outputFilename, result);
    }
}
