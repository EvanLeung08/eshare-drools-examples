package com.eshare.drools.examples;

import java.util.ArrayList;
import java.util.List;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by liangyh on 2019/10/14. Email:10856214@163.com
 */
public class UserScoreExample {

  public static void main(String[] args) throws Exception {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    execute(kc);
  }

  public static void execute(KieContainer kc) throws Exception {
    // From the container, a session is created based on
    // its definition and configuration in the META-INF/kmodule.xml file
    KieSession ksession = kc.newKieSession("userScoreKS");

    List<Order> orderList = initializeData();

    for (int i = 0; i < orderList.size(); i++) {
      Order o = orderList.get(i);
      ksession.insert(o);
      ksession.fireAllRules();
      // 执行完规则后, 打印信息
      println(o);
    }

    ksession.dispose();

  }

  private static void println(Order o) {

    System.out.println(o.getUser().getName() + "的金额是" + o.getAmount() + ",有" + o.getScore() + "积分");
  }


  private static List<Order> initializeData() {
    List<Order> orders = new ArrayList<>();

    {
      User user = new User();
      user.setAccount("001");
      user.setName("周星星");
      Order order = new Order();
      order.setAmount(10);
      order.setUser(user);
      orders.add(order);
    }
    {
      User user = new User();
      user.setAccount("002");
      user.setName("李克勤");
      Order order = new Order();
      order.setAmount(101);
      order.setUser(user);
      orders.add(order);
    }
    {
      User user = new User();
      user.setAccount("003");
      user.setName("黎明");
      Order order = new Order();
      order.setAmount(600);
      order.setUser(user);
      orders.add(order);
    }
    {
      User user = new User();
      user.setAccount("004");
      user.setName("张学友");
      Order order = new Order();
      order.setAmount(2000);
      order.setUser(user);
      orders.add(order);
    }
    return orders;
  }
}
