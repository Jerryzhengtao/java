package nju.zt.designpattern;

import java.util.ArrayList;

/**
 * @ClassName ObserverPattern
 * @Description //
 * 观察者模式，翻了十几篇博客，这篇算是讲得很清楚,例子也容易理解
 * 连接：
 * https://blog.csdn.net/itachi85/article/details/50773358
 * 补充：
 * https://www.jianshu.com/p/d55ee6e83d66
 * 此博客例子更实际
 * @Author zt
 * @Date 2021/1/28 23:49
 * @Version 1.0
 **/
public class ObserverPattern {
    /**
     * 以下例子摘取自上面博客：
     * 拿微信公众号来举例，假设微信用户
     * 就是观察者，微信公众号是被观察者，
     * 有多个的微信用户关注了程序猿这个
     * 公众号，当这个公众号更新时就会通
     * 知这些订阅的微信用户。
     */
    //观察者接口，实现此接口的类都可称为观察者
    interface Observer {
        void update(String msg);
    }

    //被观察者
    interface Subject {
        //增加观察者
        void attach(Observer observer);

        //删除观察者
        void detach(Observer observer);

        //通知消息
        void notify(String msg);
    }

    //具体的被观察者，微信公众号
    static class OfficialAccount implements Subject {
        private String name;
        //存储订阅此公众号的微信用户
        private ArrayList<Observer> fans = new ArrayList<>();

        public OfficialAccount(String name) {
            this.name = name;
        }

        @Override
        public void attach(Observer observer) {
            fans.add(observer);
        }

        @Override
        public void detach(Observer observer) {
            fans.remove(observer);
        }

        @Override
        public void notify(String msg) {
            if (fans.size() != 0) {
                for (Observer ob : fans) {
                    ob.update(this.name + msg);
                }
            }
        }
    }

    static class WechatUser implements Observer {
        private String name;
        private ArrayList<OfficialAccount> officialAccounts = new ArrayList<>();

        public WechatUser(String name) {
            this.name = name;
        }

        public void beFans(OfficialAccount officialAccount) {
            //关注此公众号
            officialAccounts.add(officialAccount);
            //注册为此公众号的观察者
            officialAccount.attach(this);
        }

        @Override
        public void update(String msg) {
            System.out.println(name + " 收到 " + msg);
        }
    }

    public static void main(String[] args) {
        //创建公众号
        OfficialAccount oc1 = new OfficialAccount("公众号1");
        OfficialAccount oc2 = new OfficialAccount("公众号2");
        //创建微信用户
        WechatUser zs = new WechatUser("张三");
        WechatUser ls = new WechatUser("李四");
        WechatUser lh = new WechatUser("李华");
        //让张三李四去关注公众号1,张三李华去关注公众号2
        zs.beFans(oc1);
        ls.beFans(oc1);
        zs.beFans(oc2);
        lh.beFans(oc2);
        //公众号更新
        oc1.notify("发布的新文章了");
        oc2.notify("发布动态");
    }
}
