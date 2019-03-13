package com.sweet.apple.domain;

/**
 * @Author zhujialing
 * @Create 2019-02-02 下午5:32
 * @Description:
 */
public class Base
{
    private String baseName = "base";
    public Base() {}

    public void callName()
    {
        System. out. println(baseName);
        System.out.println("===basecalname==");
    }

    static class Sub
    {
        private String baseName = "sub";
        public void callName()
        {
            Base b = new Base();
            System. out. println (baseName) ;
            System.out.println("===sub==");
        }
    }
    public static void main(String[] args)
    {
        Base b = new Base();
    }
}

