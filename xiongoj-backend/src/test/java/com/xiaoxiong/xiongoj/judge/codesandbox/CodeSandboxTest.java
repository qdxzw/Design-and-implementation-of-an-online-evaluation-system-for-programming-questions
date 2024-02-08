package com.xiaoxiong.xiongoj.judge.codesandbox;

import com.xiaoxiong.xiongoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.xiaoxiong.xiongoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.xiaoxiong.xiongoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.xiaoxiong.xiongoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.xiaoxiong.xiongoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class CodeSandboxTest {
    @Value("${codesandbox.type:example}")
    private String type;
    @Test
    void executeCode() {
        CodeSandbox codeSandbox=new RemoteCodeSandbox();
        String code="int main(){ }";
        String language= QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList= Arrays.asList("1 2","3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
    @Test
    void executeCodeByValue() {
        CodeSandbox codeSandbox=CodeSandboxFactory.newInstance(type);
        String code="int main(){ }";
        String language= QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList= Arrays.asList("1 2","3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
    @Test
    void executeCodeProxy() {
        CodeSandbox codeSandbox=CodeSandboxFactory.newInstance(type);
        codeSandbox=new CodeSandboxProxy(codeSandbox);
        String code="int main(){ }";
        String language= QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList= Arrays.asList("1 2","3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()){
            String type=scanner.next();
            CodeSandbox codeSandbox=CodeSandboxFactory.newInstance(type);
            String code="int main(){ }";
            String language= QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList= Arrays.asList("1 2","3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .code(code)
                    .language(language)
                    .inputList(inputList)
                    .build();
            ExecuteCodeResponse executeCodeResponse=codeSandbox.executeCode(executeCodeRequest);
        }
    }
}