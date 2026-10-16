package com.arithmatrix.organizer;

import java.nio.file.Path;
import java.nio.file.Paths;
public class Cli {
    // 1. Configuration fields
    private Path sourceDir;
    private Path targetDir;
    private boolean dryRun = false;
    private Path logFile = Paths.get("organizer.log");


    public static Cli parse(String[] args) {
        Cli config = new Cli();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-s":
                case "--source":
                    if (i + 1 < args.length) {
                        config.sourceDir = Paths.get(args[++i]);
                    }
                    break;
                case "-t":
                case "--target":
                    if (i + 1 < args.length) {
                        config.targetDir = Paths.get(args[++i]);
                    }
                    break;
                case "-d":
                case "--dry-run":
                    config.dryRun = true;
                    break;
                case "-l":
                case "--log":
                    if (i + 1 < args.length) {
                        config.logFile = Paths.get(args[++i]);
                    }
                    break;
                case "-h":
                case "--help":
                    printHelp();
                    System.exit(0);
                    break;
                default:
                    System.err.println("Unknown option: " + arg);
                    printHelp();
                    System.exit(1);
            }
        }
        // Validation: Source folder is mandatory!
        if (config.sourceDir == null) {
            System.err.println("Error: Source directory (--source or -s) is required.");
            printHelp();
            System.exit(1);
        }
        // If target was not specified, default to source
        if (config.targetDir == null) {
            config.targetDir = config.sourceDir;
        }
        return config;
    }


    //Dashboard for the CLI
    private static void printHelp() {
        System.out.println("===============================================================");
        System.out.println("  Arithmatrix AVIP 2026 - CLI File Organizer (Java)");
        System.out.println("===============================================================");
        System.out.println("Usage: java com.arithmatrix.organizer.Main [options]");
        System.out.println("Options:");
        System.out.println("  -s, --source <path>    (Required) Source directory to organize");
        System.out.println("  -t, --target <path>    (Optional) Target destination directory (default: source)");
        System.out.println("  -d, --dry-run          Simulate planned moves without moving files");
        System.out.println("  -l, --log <path>       Log file destination (default: organizer.log)");
        System.out.println("  -h, --help             Show this help screen");
    }

    // Getters for the configuration fields
    public Path getSourceDir() {
        return sourceDir;
    }

    public Path getTargetDir() {
        return targetDir;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public Path getLogFile() {
        return logFile;
    }
    

}

