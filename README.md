# GDAL-TIF-Compressor
This java desktop app iterates through an INPUT directory and compresses any TIF files into an OUTPUT directory using the python command 'gdal_translate'.

![screenshot](GDAL_Translator_Screenshot.PNG)

## Getting Started
GDAL is a library for reading and writing raster and vector geospatial data formats. This GDAL_Translator is a custom Java application developed by the Pitkin County BITS Department. This application implements the ‘gdal_translate’ command (from GDAL). The GDAL_Translator loops through a user-defined input directory (non-recursive), compressing each TIFF file into the specified output directory using the original filename (up to an optional maximum number of files). The GUI will be responsive while the command executes, which takes ~1 min per file.  This compression reduces the TIFF file size by approximately 95%.

A common use case for this application is for processing large image sets from a fly-over or reducing a TIFF file for distribution.   Typical users include the Pitkin County GIS Department.

### Prerequisites / Minimum System Requirements:
  Geospatial Data Abstraction Library (GDAL)
  Python?
  Windows 7?

![screenshot](GDAL_Translator_HELP.PNG)

### Install / Run the Application
Make sure the system requirements have been met, including prerequisite software (see “Version Info”).

The Java Application is distributed as a JAR file (see icon on right). Users running Windows based operating systems can double click this file to run the application. Once the application is running, the user can follow the process defined below to use the GDAL_Translator.


## User Guide

## Using the Application (SOP)
Users can modify the following parameters within the GUI prior to clicking the ‘Translate’ button:
Where the uncompressed images can be found (Input Directory)
Where the compressed images should be sent (Output Directory)
Max number of files to compress (# of Files)

Figure_1: This annotated screenshot above highlights the key features of the GDAL_Translator GUI. Below is an outline of the typical process flow for the GDAL_Tranlsator application.

### Set Input Directory
Click the ‘Set Input Directory’ button, use folder browser dialog to select the directory that contains the uncompressed image files
Set Output Directory
Click the ‘Set Output Directory’ button, use folder browser dialog to select the directory where the compressed image files should be written
### Review # of Files
If this value is a positive integer (≥0) then the application will run through all files, otherwise the application will only compress the specified number of files
NOTE: Output files that already exist do not count towards the number of files.
Click ‘Translate’
The ‘Translate’ button will be disabled while the task is running.
The log will indicate what task it is starting.
Feedback is logged for each file completed.
Finally the word ‘Complete’ will indicate the task is finished, and the ‘Translate’ button will be re-enabled.
Wait for completion



## FAQ (and Test Cases)
What if the ‘# of Files’ is not-numeric? A positive integer? Less than 0?
If any error is thrown trying to convert the ‘# of Files’ to an integer, a message is logged, the value will default to 1 file, and the translation is cancelled.
If a positive integer is entered, the maximum number of files to be processed is set to the value provided.
*WARNING* If an integer value of <= 0 is entered, the program will process ALL files. The user will not be prompted or warned, and this could take a considerable amount of time (at ~1 minute per TIF file).
What happens if the output file already exists?
If the output file exists already, then the application will assume the compression has already been completed, and will skip this file.
Files that are found to already exist in the output directory DO NOT count towards the number of files converted.
What happens if the input directory does not exist?
This is checked when the input directory is chosen from the folder browser dialog. It is also checked when the ‘translate’ button is clicked.
A message is logged to indicate that the input directory does not exist.
Example: Translate Failed: Invalid input directory, Dir = 'inputDir’
Translation is cancelled
What happens if the output directory does not exist?
Currently the translation fails and throws an error. The GUI does not crash and can be triggered again.
What should happen: Create the directory, no message to user


## Release Process
The source code for this project is currently located on Andrew Shewmaker’s desktop. Moving the code to a centralized location should be a high priority. When changes are made to the source code, the application can be ‘published’/’deployed’ by distributing the JAR file from the ../dist/ directory. While the project is only ready for internal use Andrew will distribute the JAR file by copying it to a network drive. If the application is released for public/general use, we could provide access from PCIN.
Future Development
This section is used to document requests for additional features and/or bug fixes.
Recursive Option
Review “file exists” logic. We could ask the user?
Implement ‘Cancel’ functionality while loop is running
Option to open output directory upon completion
Add some compression statistics to Log
When input directory is set, we can count number of TIFF files, then use that number to estimate total time required, and make decisions about warning the user
Final State: Application is stable and available on PCIN including clear documentation
Is there a way to embed the GDAL python (or java) commands in our code? Such that deploying the GDAL_Translator does not require any prerequisites?

