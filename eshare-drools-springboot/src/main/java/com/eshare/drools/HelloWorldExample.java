package com.eshare.drools;


import com.eshare.drools.model.Message;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by liangyh on 2019/10/13. Email:10856214@163.com
 */
public class HelloWorldExample {

  public static void main(String[] args) {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    KieSession ksession = kc.newKieSession("HelloWorldKS");

    // Set up listeners.
    ksession.addEventListener(new DebugAgendaEventListener());
    ksession.addEventListener(new DebugRuleRuntimeEventListener());

// Set up a file-based audit logger.
    //KieRuntimeLogger logger = KieServices.get().getLoggers().newFileLogger( ksession, "./target/helloworld" );

// Set up a ThreadedFileLogger so that the audit view reflects events while debugging.
    KieRuntimeLogger logger = ks.getLoggers()
        .newThreadedFileLogger(ksession, "helloworld", 1000);
    // Insert facts into the KIE session.
    final Message message = new Message();
    message.setMessage("Hello World");
    message.setStatus(Message.HELLO);
    ksession.insert(message);

// Fire the rules.
    ksession.fireAllRules();
    ksession.dispose();
  }
}
