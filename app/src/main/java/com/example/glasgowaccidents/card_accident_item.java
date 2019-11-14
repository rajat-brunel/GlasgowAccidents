package com.example.glasgowaccidents;

public class card_accident_item {

    private String mText1;
    private String mText2;
    private String mText3;
    private String mText4;

    public card_accident_item(String text1, String text2, String text3, String text4){
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
        mText4 = text4;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }

    public String getText3(){
        return mText3;
    }

    public String getText4(){
        return mText4;
    }
}
