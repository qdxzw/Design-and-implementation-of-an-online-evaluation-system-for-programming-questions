package com.xiaoxiong.xiongoj.judge.codesandbox.impl;

import com.xiaoxiong.xiongoj.judge.codesandbox.CodeSandbox;
import com.xiaoxiong.xiongoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.xiaoxiong.xiongoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.xiaoxiong.xiongoj.judge.codesandbox.model.JudgeInfo;
import com.xiaoxiong.xiongoj.model.enums.JudgeInfoMessageEnum;
import com.xiaoxiong.xiongoj.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * 示例代码沙箱（仅为了跑通业务流程）
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest){
        List<String > inputList=executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse=new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo=new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        System.out.println("示例代码沙箱");
        return executeCodeResponse;
    }
}
