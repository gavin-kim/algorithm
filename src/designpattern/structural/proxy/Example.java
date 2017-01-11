package designpattern.structural.proxy;

/**
 * Purpose: class Document
 *               -image    ------> ImageProxy
 *                                -fileName     ------> Image
 *                                                      data
 * The proxy has access the real Subject and provides an interface for the real Subject.
 * It controls access to the real Subject and is responsible for creating and deleting.
 *
 * For example, When loading a document file, loading images cost expensive
 * Client doesn't know about the Proxy. When images is being loaded
 *
 * in the ImageProxy
 *
 * Draw() {
 *     if (image == null)
 *         image = LoadImage(filename);  // invoke LoadImage() and wait for loading is done
 *     else
 *         image.Draw();                 // draw the real Image
 * }
 *
 * GetExtent() { // width and height
 *     if (image == null)
 *         return extent;
 *     else
 *         return image.getExtent();
 * }
 *
 * A remote proxy can gide the fact that an object resides in different address space
 * A virtual proxy can perform optimizations such as creating an object on demand
 *
 */

public class Example {
    public static void main(String[] args) {

        DiagnosticTool tool = new EngineDiagnosticTool();
        Engine engine = new StandardEngine(1300);
        engine.diagnose(tool);
    }
}
