package com.xiongoj.xiongojcodesandbox.utils;

import cn.hutool.core.util.StrUtil;
import com.xiongoj.xiongojcodesandbox.model.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.*;

/**
 * 进程工具类
 */
public class ProcessUtils {
    /**
     * 执行进程并获取信息
     *
     * @param runProcess
     * @param opName
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String opName) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
            StopWatch stopWatch=new StopWatch();
            stopWatch.start();
            //            等待程序执行，获取错误码
            int exitValue = runProcess.waitFor();
            executeMessage.setExitValue(exitValue);
//            正常退出
            if (exitValue == 0) {
                System.out.println(opName + "成功");
//                分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                //                逐行读取
                String comileOutputLine;
                while ((comileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(comileOutputLine).append("\n");
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());
            } else {
//                异常退出
                System.out.println(opName + "失败，错误码：" + exitValue);
//                分批获取进程的正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                //                逐行读取
                String comileOutputLine;
                while ((comileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(comileOutputLine).append("\n");
                }
                executeMessage.setMessage( compileOutputStringBuilder.toString());
                //                分批获取进程的错误输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder errorCompileOutputStringBuilder = new StringBuilder();
                //                逐行读取
                String errorComileOutputLine;
                while ((errorComileOutputLine = errorBufferedReader.readLine()) != null) {
                    errorCompileOutputStringBuilder.append(errorComileOutputLine).append("\n");
                }
                executeMessage.setErrorMessage(errorCompileOutputStringBuilder.toString());
            }
            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }

    /**
     * 执行交互式进程并获取信息
     *
     * @param runProcess
     * @param args
     * @return
     */
    public static ExecuteMessage runInteractProcessAndGetMessage(Process runProcess,String args) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        try {
//                向控制台输入程序
            OutputStream outputStream = runProcess.getOutputStream();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] s=args.split(" ");
            String join= StrUtil.join("\n",s)+"\n";
            outputStreamWriter.write(join);
            outputStreamWriter.flush();
//            相当于按了回车，执行输入的发送
            outputStreamWriter.flush();
//                分批获取进程的正常输出
            InputStream inputStream=runProcess.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder compileOutputStringBuilder = new StringBuilder();
                //                逐行读取
                String comileOutputLine;
                while ((comileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(comileOutputLine);
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());
//                资源的释放
            outputStreamWriter.close();
            outputStream.close();
            inputStream.close();
            runProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }
}
