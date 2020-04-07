package com.oneframe.cucumber.oneframeutils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import continuum.cucumber.Utilities;

public class FileUtil {
    public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    private static String sourceLocation;
    private static String targetLocation;

    private static final String LOG_DATA_FILE_NAME = "log_data.txt";
    private static final String PROJECT_BASE_DIR = System.getProperty("user.dir");
    private static final Path GENERATED_DATA_FILE = Paths.get(PROJECT_BASE_DIR, LOG_DATA_FILE_NAME);

    private static final String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss aa";

    private static PrintWriter printWriter;
    private static FileWriterWithEncoding fileWriter;

    private static final boolean WRITE_TO_LOG_FILE = Boolean
            .parseBoolean(Utilities.getMavenProperties("writeToScenarioLog"));

    static {
        if (!Files.exists(GENERATED_DATA_FILE)) {
            try {
                Files.createFile(GENERATED_DATA_FILE);
            } catch (IOException e) {
                LogPrinter.printLog("Unable to Create File with path : " + GENERATED_DATA_FILE.toString() + " Error : "
                        + e.getMessage());
            }
        }
        try {
            fileWriter = new FileWriterWithEncoding(GENERATED_DATA_FILE.toFile(), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            LogPrinter.printLog("Error Initializing FileWriter : " + e.getMessage());
        }

    }

    /**
     * Load files as string.
     *
     * @param path
     *            - path where the files is present.
     * @return - file content in form of string.
     * @author sudheer.singh
     */
    public static String loadFileAsString(String path) {
        String fileContents = null;
        try (InputStream inputStream = FileUtil.class.getResourceAsStream(path)) {
            fileContents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return fileContents;
    }

    /**
     * Resource files from class resource.
     *
     * @param resourceFilePath
     *            - location of resource file path.
     * @return - file content.
     * @author sudheer.singh
     */
    public String readFileFromResources(String resourceFilePath) {
        String result = "";
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(resourceFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return result;
    }

    /**
     * get resource by specified path. if resource was not found, assume path
     * variable is absolute already.
     */
    public static String getAbsPath(String path) {
        return FileUtil.class.getResource(path) != null ? FileUtil.class.getResource(path).getPath() : path;
    }

    /**
     * Delete folder.
     *
     * @param pathString
     *            - folder location.
     */
    public static void deleteFolder(String pathString) {
        try {
            FileUtils.deleteDirectory(new File(pathString));
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Check if file exist or not.
     *
     * @param filePath
     *            - path of the file.
     * @return - True if file exist else false.
     */
    public static boolean isExists(String filePath) {
        File fileObj = new File(filePath);
        return fileObj.exists() && fileObj.isFile();
    }

    /**
     * Delete file.
     *
     * @param pathString
     *            - path of the file.
     */
    public static void deleteFile(String pathString) {
        Path path = Paths.get(pathString);
        try {
            logger.info("Deleting file {}", path);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Copy files from source location to target location, set source location
     * and set target location would be the prerequisit before you use this
     * method.
     *
     * @param fileName
     *            - Name of the files which has to be copied.
     * @author sudheer.singh
     * @throws IOException
     *             if an IO error occurs during copying.
     */
    public static void copyFilesFromSourceToTarget(String fileName) throws IOException {
        if (FilesBeans.getSourceFileLocation() != null) {
            sourceLocation = FilesBeans.getSourceFileLocation() + fileName;
        }
        if (FilesBeans.getTargetFileLocation() != null) {
            targetLocation = FilesBeans.getTargetFileLocation() + fileName;
        }

        File sourceFile = new File(sourceLocation);
        File targetFile = new File(targetLocation);
        try {
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            LogPrinter.printLog("Error occured while copying");
            throw e;
        }
    }

    /**
     * readJsonFile.
     *
     * @param fileName
     *            - json file name.
     * @return JsonObject - content in the form of json.
     * @author sudheer.singh
     */
    public static JsonObject readJsonFile(String fileName) throws IOException {
        JsonParser parser = new JsonParser();
        InputStream inputStream = FileUtil.class.getResourceAsStream(fileName);
        String jsonTxt = IOUtils.toString(inputStream, Charset.defaultCharset());
        JsonElement jsonElement = parser.parse(jsonTxt);
        return jsonElement.getAsJsonObject();
    }

    /**
     * Read xml files should be available in continuum/cucumber/utils/testutil
     * folder.
     *
     * @param fileName
     *            - file name.
     * @return - file content
     * @author poras.suryawanshi
     * @throws IOException
     *             - If file is not present in the Classpath.
     */
    public static String readXmlFile(String fileName) throws IOException {
        String fileContent = null;
        try {
            InputStream inputStream = FileUtil.class.getResourceAsStream(fileName);
            fileContent = IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException ioe) {
            LogPrinter.printLog("Error occured while reading XML file");
            throw ioe;
        }
        return fileContent;
    }

    /**
     * Prints an Object and then terminates the line inside the file created at
     * Projects Base Directory.
     *
     * @param toWrite
     *            the <code>Object</code> value to be written on file.
     */
    public static void writeToDataFile(Object toWrite) {
        if (!WRITE_TO_LOG_FILE) {
            return;
        }
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            if (printWriter == null) {
                printWriter = new PrintWriter(fileWriter, true);
            }
            final String currentDateTime = dateFormatter.format(new Date());
            printWriter.println("Writing at " + currentDateTime + " Log : " + toWrite.toString());
        } catch (Exception ioe) {
            LogPrinter.printLog("Unable to write to File due to some error:" + ioe.getMessage());
        }

    }

    /**
     * Close the Write instance to stop writing to file further and flush the
     * contents from the Writers Pipeline.
     */
    public static void closeWriter() {
        if (printWriter != null) {
            printWriter.flush();
            printWriter.close();
        }
        printWriter = null;
    }
}