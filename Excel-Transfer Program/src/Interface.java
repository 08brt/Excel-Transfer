import java.io.IOException;
import java.util.Scanner;

public class Interface {

    private Codebook codebook;
    private Codeframe codefram;
    private Scanner reader;

    public Interface() throws IOException {
        this.reader = new Scanner(System.in);

        System.out.println("Welcome to the Excel Transfer Program");
        System.out.println("Make sure to write the correct file path");
        System.out.println("Make sure to add .xlsx at the end of the address");
        System.out.println();

        //asks user for file destination
        System.out.print("Enter CodeFrame Address: ");
        String codefameAddress = reader.nextLine();
        System.out.println();

        //asks user for file destination
        System.out.print("Enter CodeBook Address: ");
        String codebookAddress = reader.nextLine();

        //launches the program with given paramaters
        this.codebook = new Codebook(codebookAddress, codefameAddress);
    }
}
