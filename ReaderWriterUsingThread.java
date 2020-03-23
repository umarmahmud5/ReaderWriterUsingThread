package readerwriterusingthread;

/**
 * @author Umar Mahmud
 * @date XXIII-III-MMXX
 * https://github.com/umarmahmud5/ReaderWriterUsingThread
 */
public class ReaderWriterUsingThread {

    public static void main(String[] args) {
        try {
            ReaderWriterThread readerT = new ReaderWriterThread("Reader");
            ReaderWriterThread writerT = new ReaderWriterThread("Writer");
            readerT.start();
            readerT.join(); // So that writer does not start unless reader has finished
            writerT.start();
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }

    }
}
