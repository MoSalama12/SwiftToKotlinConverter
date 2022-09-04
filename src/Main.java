import generatedantlr.Swift3Lexer;
import generatedantlr.Swift3Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.String;


public class Main {
    public static boolean terminated;
    public static  String projectDirectory = System.getProperty("user.dir");
    public static void main(String[] args) {
        System.out.println(convertToKotlin("var x: Int = 5"));
    }
    private static String convertToKotlin(String swiftCode) {
        SwiftToKotlinVisitor swiftToKotlinVisitor = new SwiftToKotlinVisitor();
        Swift3Parser parser = new Swift3Parser(null);
        ANTLRInputStream input = new ANTLRInputStream(swiftCode);
        Swift3Lexer lexer = new Swift3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser.setInputStream(tokens);
        // from here u need to change your code

        ParseTree parseTree = parser.top_level();
        String javaCode = swiftToKotlinVisitor.visitTop_level((Swift3Parser.Top_levelContext) parseTree);
        return javaCode;
    }
}
