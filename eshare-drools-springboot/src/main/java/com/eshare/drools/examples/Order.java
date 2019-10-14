package com.eshare.drools.examples;

import lombok.Data;

/**
 * Created by liangyh on 2019/10/14. Email:10856214@163.com
 */
@Data
public class Order {

  private int score;
  private double amount;
  private User user;
}
