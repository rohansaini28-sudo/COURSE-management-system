package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupServiceImpl implements BackupService {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path BACKUP_DIR = Paths.get("backups");

    @Override
    public void backupData() {
        try {
            if (!Files.exists(BACKUP_DIR)) {
                Files.createDirectories(BACKUP_DIR);
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path target = BACKUP_DIR.resolve("backup_" + timestamp);

            Files.createDirectories(target);

            try (var paths = Files.list(DATA_DIR)) {
                paths.forEach(file -> {
                    try {
                        Files.copy(file, target.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            System.out.println("Backup created at: " + target);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}