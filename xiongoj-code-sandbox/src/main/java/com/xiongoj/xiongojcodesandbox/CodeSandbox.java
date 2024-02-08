package com.xiongoj.xiongojcodesandbox;


import com.xiongoj.xiongojcodesandbox.model.ExecuteCodeRequest;
import com.xiongoj.xiongojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
