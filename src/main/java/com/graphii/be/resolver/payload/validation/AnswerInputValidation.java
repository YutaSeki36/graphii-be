package com.graphii.be.resolver.payload.validation;

import com.graphii.be.resolver.payload.AnswerInput;

public class AnswerInputValidation {

    public static boolean validation(AnswerInput input) throws IllegalArgumentException {
        if (input.getAnswerer().isEmpty()) throw new IllegalArgumentException("回答者を入力してください");
        if (input.getAnswerItemInputs().size() == 0) throw new IllegalArgumentException("回答を入力してください");


        for(AnswerInput.AnswerItemInput a: input.getAnswerItemInputs()){
            if (a.getVal() > 10 || a.getVal() < 0) throw new IllegalArgumentException("0から10までの値を入力してください");
        }

        return true;
    }
}
