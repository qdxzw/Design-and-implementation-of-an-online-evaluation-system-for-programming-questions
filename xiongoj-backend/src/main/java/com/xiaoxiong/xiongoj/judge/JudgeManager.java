package com.xiaoxiong.xiongoj.judge;

import com.xiaoxiong.xiongoj.judge.strategy.DefaultJudgeStrategy;
import com.xiaoxiong.xiongoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.xiaoxiong.xiongoj.judge.strategy.JudgeContext;
import com.xiaoxiong.xiongoj.judge.strategy.JudgeStrategy;
import com.xiaoxiong.xiongoj.judge.codesandbox.model.JudgeInfo;
import com.xiaoxiong.xiongoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
        JudgeInfo doJudge(JudgeContext judgeContext){
            QuestionSubmit questionSubmit=judgeContext.getQuestionSubmit();
            String language=questionSubmit.getLanguage();
            JudgeStrategy judgeStrategy=new DefaultJudgeStrategy();
            if("java".equals(language)){
                judgeStrategy=new JavaLanguageJudgeStrategy();
            }
            return judgeStrategy.doJudge(judgeContext);
        }
    }

