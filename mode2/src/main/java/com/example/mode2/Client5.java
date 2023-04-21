package com.example.mode2;

public class Client5 {
    public static void main(String[] args) {
        SubscriptionSubject mSubscriptionSubject = new SubscriptionSubject();
        //创建微信用户
        WeixinUser user1 = new WeixinUser("1111");
        WeixinUser user2 = new WeixinUser("2222");
        WeixinUser user3 = new WeixinUser("3333");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新消息发送给微信用户
        mSubscriptionSubject.notify("小宁的博客更新了！");
    }
}
