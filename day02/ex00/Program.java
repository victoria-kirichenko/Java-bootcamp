package ex00;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new Analyzer();
        analyzer.fillSignature();
        analyzer.GetExtensions();
    }
}