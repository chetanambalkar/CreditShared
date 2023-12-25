package com.smicc.CreditShare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderProcessor {

    private Configuration config;
    private static final Logger logger = LoggerFactory.getLogger(FolderProcessor.class);

    public FolderProcessor(Configuration config) {
        this.config = config;
    }

    public void processFolders() throws IOException {
        logger.info("Inside processFolders method");
        FolderHandler folderHandler = new FolderHandler(config);

        File tmpFile = new File(config.getTempPath());
        if (!tmpFile.exists()) {
            Files.createDirectories(Paths.get(config.getTempPath()));
        }

        folderHandler.deleteFolderFromTemp(new File(config.getTempPath()));

        for (String folderName : config.getFolderNames()) {
           logger.info("Folder Name: {}", folderName);
            logger.info("Root path: {}", config.getRootPath());
            Path folderPath = Paths.get(config.getRootPath(), folderName);

            folderHandler.handleFolder(folderPath);
        }
    }
}
