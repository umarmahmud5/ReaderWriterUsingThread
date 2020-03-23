package readerwriterusingthread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Umar Mahmud
 * @date XXIII-III-MMXX
 * https://github.com/umarmahmud5/ReaderWriterUsingThread
 */

public class ReaderWriterThread extends Thread {

    static File inputFile;
    static File outputFile;
    static BufferedReader inputReader;
    static BufferedWriter outputWriter;
    private static String line;
    private static String writeLine; //Shared Buffer - Unbounded

    public ReaderWriterThread(String threadName) {	//Constuctor
        super(threadName);	//Call to constructor of Thread class     
        this.writeLine = "";
        inputFile = new File("inputFile.txt");
        outputFile = new File("outputFile.txt");
    }

    private synchronized void readLine() {
        System.out.println("Reading File");
        try {
            while ((this.line = inputReader.readLine()) != null) {
                if (this.line != null) {
                    this.writeLine += this.line+"\n";
                } 
            }
            //System.out.println("This has been read by reader... " + this.writeLine);
            inputReader.close();
            yield();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private synchronized void writeLine() {
        try {
            if (this.writeLine != null) {
                outputWriter.write(this.writeLine);
                outputWriter.flush();              
            } 
            //System.out.println("This has been written by writer... " + this.writeLine);
            outputWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        } 
    }

    @Override
    public void run() {
        try {
            inputReader = new BufferedReader(new FileReader(inputFile));
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
            if (Thread.currentThread().getName().startsWith("Reader")) {
                //In a reader
                System.out.println("READER Thread has started...");
                readLine();
            } else if (Thread.currentThread().getName().startsWith("Writer")) {
                //In a Writer
                System.out.println("WRITER thread has started");
                writeLine();
            } else {	//default case
                System.err.println("Some Error");
            }        
            System.out.println("END of Program and both threads have finished");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
