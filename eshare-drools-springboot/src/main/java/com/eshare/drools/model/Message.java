package com.eshare.drools.model;

import lombok.Data;

@Data
public class Message {

  public static final int HELLO = 0;
  public static final int GOODBYE = 1;

  public String message;
  public int status;
}