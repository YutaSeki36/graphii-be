package com.graphii.be.resolver.payload.validation;

import com.graphii.be.resolver.payload.ThemeInput;

public class ThemeInputValidation {

    public static boolean validation(ThemeInput input) throws Exception {

        if(input.getTitle().isEmpty()) throw new IllegalArgumentException("お題を入力してください");
        if(input.getCreator().isEmpty()) throw new IllegalArgumentException("作成者を入力してください");
        if (input.getItemInputs().size() < 4) throw new IllegalArgumentException("項目を3つ以上追加してください");
        if(input.getImageUrl().isEmpty()) throw new IllegalArgumentException("画像のURLがありません");

        if(input.getTitle().length() < 1 || input.getTitle().length() > 80){
            throw new IllegalArgumentException("お題を正しい文字数で入力してください");
        }

        if(input.getCreator().length() < 1 || input.getCreator().length() > 50){
            throw new IllegalArgumentException("作者名を正しい文字数で入力してください");
        }

        if (input.getItemInputs().size() > 7){
            throw new IllegalArgumentException("項目は7つまでしか登録できません");
        }

        return true;
    }
}
