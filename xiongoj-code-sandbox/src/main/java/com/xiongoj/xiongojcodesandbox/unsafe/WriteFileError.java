package com.xiongoj.xiongojcodesandbox.unsafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WriteFileError {
    public static void main(String[] args) throws InterruptedException, IOException {
        String userDir=System.getProperty("user.dir");
        String filePath=userDir+ File.separator+"src/main/resources/木马程序.bat";
        String errorProgram="java -version 2>&1";
        Files.write(Paths.get(filePath), Arrays.asList(errorProgram));
        System.out.println("写木马成功");
    }
}
