/*
 * // This is a version one - prototype of the Required Translator.
 //   Version 2 requirements:
 //   1.  Documentation.
 //   2.  User Interface:  Input Directory, files, Output Directory, specificaion  and possibly filter specification.
 //   3.  Remove all hardcode.
 //   4.  Additional flexibility via exception handling per filter / translation.
 //   5.  Unit Test Harnesses
 //   6.  Put on Schedule for October.
 // SAMPLE PATH
 // \\PKCASPMAIDS04\images\2016_LiDAR
 */
package gisfiletranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;


/**
 *
 * @author jennifer.broderick
 */
public class GDALTranslator {

    private Path inputRasterDir;  //This is the input directory of your raster files.  This should be read from the command line
    private Path outputTifDir;   //This is the output directory for you new TIFF files  This should be read from the command line
    private String inputDirName = "L:\\2011_County\\GeoTiff\\";
    private File[] inFiles;
    private String fileNameWithoutExtension = "test";

    public GDALTranslator() {
        inputRasterDir = Paths.get(inputDirName);
        outputTifDir = Paths.get(inputDirName + "..\\GeoTiff-compressed\\");
    }

    public GDALTranslator(String inputRasterDir, String outputTifDir) {
        this.inputRasterDir = Paths.get(inputRasterDir);
        this.outputTifDir = Paths.get(outputTifDir);
        
        //System.out.println("[GDALTranslator(String inputRasterDir, String outputTifDir)] Initialized");
    }

    public String[] translate(int numFiles) {
        Set<String> commands = new LinkedHashSet<String>();
        int cntFiles = 0;

        if (Files.notExists(outputTifDir)) {  // create the output directory if it does not exist.
            try {
                System.out.println("Creating new Output Directory: " + outputTifDir.toString());
                Files.createDirectory(outputTifDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       
        if (Files.notExists(inputRasterDir)) {  // check that the input directory exists
            System.out.println("ERROR***** NO SUCH DIRECTORY FOUND");
            //txtConsole.append("Translate Failed: Invalid entry for  '# files', Input = '" + txtNumFiles.getText() + "'" + System.getProperty("line.separator"));
        } else {
            inFiles = inputRasterDir.toFile().listFiles();
            for (File inFile : inFiles) {
                if (inFile.toString().endsWith(".tif")) { //&& inFile.toString().contains("TOPO")) {//translate only the tif files
                    System.out.println(inFile.getName());     //This lists the Raster files for input processing
                    //fileNameWithoutExtension = stripExtension(inFile.getName());
                    File outFile = new File(outputTifDir.toString() + "\\" + inFile.getName());
                    if (!outFile.exists()) {  //Then lets create it!
                        System.out.println("Executing translation of " + inFile
                         + " to " + outFile);
                        
                        //Developing for Mary...
//                        commands.add("gdal_translate -co COMPRESS=JPEG  -co PHOTOMETRIC=YCBCR "
//                                + inFile + " " + outFile);  // GDAL translation command
                        
                        //Works on Andrew's Laptop
                        commands.add("cd \\ & OSGeo4W64\\OSGeo4W.bat gdal_translate -co COMPRESS=JPEG  -co PHOTOMETRIC=YCBCR "
                                + inFile + " " + outFile);  // GDAL translation command
                        cntFiles++;
                    }
                    
                    //check if we have processed the requested amount of files...
                    if (cntFiles >= numFiles && numFiles > 0)
                        break;
                }
            }
        }

        String[] commandArray = commands.toArray(new String[commands.size()]);
        //this.executeSystemCommand(commandArray);
        return commandArray;
    }

    public String executeSystemCommand(String[] commands) {
        String response = "";
        for (String cmd : commands) {
            System.out.println("COMMAND:  " + cmd);
            try {
                String s = null;
                
                //Developing for Mary...
                //Process p = Runtime.getRuntime().exec("cmd.exe /c " + cmd + " & cd");
                //this works on Andrew's Laptop
                Process p = Runtime.getRuntime().exec("cmd.exe /c OSGeo4W64\\OSGeo4W.bat & " + cmd + "");
                
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                //read the output from the command
                System.out.println("COMMAND OUTPUT:  ");
                response = "COMMAND OUTPUT:  ";
                response = response.concat(System.getProperty("line.separator"));
                
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                    response = response.concat(s + System.getProperty("line.separator"));
                    //JOptionPane.showMessageDialog(null, response, "TITLE", JOptionPane.WARNING_MESSAGE);
                }

                //read the output from the command
                System.out.println("COMMAND ERRORS:  ");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                    response = response.concat("ERR:" + s);
                    response = response.concat(System.getProperty("line.separator"));
                }

            } catch (Exception e) {
                System.out.println("Exception: ");
                e.printStackTrace();
                System.exit(-1);
            }
        }
        return response;
    }

    private String stripExtension(String str) {
        // Handle null case specially.

        if (str == null) {
            return null;
        }

        // Get position of last '.'.
        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.
        if (pos == -1) {
            return str;
        }

        // Otherwise return the string, up to the dot.
        return str.substring(0, pos);
    }

    public Path getInputRasterDir() {
        return inputRasterDir;
    }

    public void setInputRasterDir(Path inputRasterDir) {
        this.inputRasterDir = inputRasterDir;
    }

    public Path getOutputTiffDir() {
        return outputTifDir;
    }

    public void setOutputTiffDir(Path outputTiffDir) {
        this.outputTifDir = outputTiffDir;
    }

}
