package com.example.proapp;


public class StateList {

    private String mhead;
    private String mdata1;
    private String mdata2;
    private String mdata3;
    private String mdata4;
    private String mdata5;
//    private int mdata6;
//    private int mdata7;



    public StateList(String mhead, String mdata1, String mdata2, String mdata3, String mdata4, String mdata5) {
        this.mhead = mhead;
        this.mdata1 = mdata1;
        this.mdata2 = mdata2;
        this.mdata3 = mdata3;
        this.mdata4 = mdata4;
        this.mdata5 = mdata5;

    }

    public String gethead() {
        return mhead;
    }

    public String getdata1() {
        return mdata1;
    }

    public String getdata2() {
        return mdata2;
    }

    public String getdata3() {
        return mdata3;
    }

    public String getdata4() {
        return mdata4;
    }

    public String getdata5() {
        return mdata5;
    }

//    public int getdata6() {
//        int mdata6= (Integer.parseInt(mdata2)*100)/Integer.parseInt(mdata1);
//        return mdata6;
//    }
//
//    public int getdata7() {
//        int mdata7= (Integer.parseInt(mdata3)*100)/Integer.parseInt(mdata1);
//        return mdata7;
//    }
}
