import generatedantlr.Swift3Lexer;
import generatedantlr.Swift3Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.String;
import java.util.Scanner;

public class Main {
    public static  String projectDirectory = System.getProperty("user.dir");
    public static String resetCode = "\033[0m";
    public static void main(String[] args) {
        File testCasesDirectory = new File(projectDirectory+"/testcases/");
        File[] foldersArray = testCasesDirectory.listFiles();
        for(File folderElement : foldersArray) {
            File[] folderFilesArray = folderElement.listFiles();
            System.out.println("\u001B[36m" +folderElement.getName() +" :-");
            System.out.print(resetCode);
            for (File files : folderFilesArray)
            {
                String fileName = files.getName();
                if (fileName.contains(".swift"))
                {
                    System.out.print("\t \u001B[35m"+fileName);
                    System.out.print(resetCode);


                    String swiftCode = IosFileRead(projectDirectory+"/testcases/"+folderElement.getName()+"/"+fileName);
                    String covertedKotlinCode = convertToKotlin(swiftCode);
                    String expectedKotlinCode = IosFileRead(projectDirectory+"/testcases/"+folderElement.getName()+"/"+ fileName.substring(0, fileName.length() - 5)+ "kt");


                    if (isEqual(covertedKotlinCode,expectedKotlinCode.substring(0,expectedKotlinCode.length()-1))) {
                        System.out.println("\u001B[32m Success");
                        System.out.print(resetCode);
                    } else {
                        System.out.println("\u001B[31m Fail");
                        System.out.print(resetCode);
                    }

                    //System.out.println("\t\t" + covertedKotlinCode);
                    //System.out.println("\t\t" + expectedKotlinCode);

                }
            }
        }

        //System.out.println(convertToKotlin("var x: Int = 5"));
    }

    public static boolean isEqual(String text1 , String text2)
    {
        text1 = text1.replaceAll("\\s+","");
        text2 = text2.replaceAll("\\s+","");
        if (text1.length() != text2.length())
            return false;

        for (int i = 0 ; i < text1.length() ;i++)
        {
            if (text1.charAt(i) != text2.charAt(i))
                return false;
        }
        return true;
    }

    public static String IosFileRead(String filepath){
        String swiftCode = "";
        try {
            File Read = new File(filepath);
            Scanner scan = new Scanner(Read);
            while(scan.hasNext())
            {
                swiftCode = swiftCode.concat(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return swiftCode;
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
