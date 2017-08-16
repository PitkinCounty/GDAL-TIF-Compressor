/*
 * GISFileTranslator iterates throught a set of files and executes a GDAL command
 *  from the System interface.
 */
package gisfiletranslator;

import java.io.Console;

/**
 *
 * @author jennifer.broderick
 */
public class GISFileTranslator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GDALTranslator translator;
        System.out.println(args.length);

        if (args.length == 0) {
            //translator = new GDALTranslator("C:\\Users\\andrew.shewmaker\\Desktop\\OneFoot-MiddleRF\\", "C:\\Users\\andrew.shewmaker\\Desktop\\OneFoot-MiddleRF\\" + "..\\GeoTiff-compressed\\");
            //translator.translate();
            
            //Display Form
            FileIteratorDialog frm = new FileIteratorDialog();
            frm.setVisible(true);
        } else if (args.length == 2) {
            String inputDirectory = args[0];
            String outputDirectory = args[1];
            translator = new GDALTranslator(inputDirectory, outputDirectory);

        } else {
            System.out.println("USAGE:  GISFILETranslator [inputDirectoryName] [outputDirectoryName]");
        }
    }

}
