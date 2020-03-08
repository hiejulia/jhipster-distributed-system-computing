package com.project.activemqconsumer;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;


public final class Cosumer {
    private Cosumer() {
    }

    public static void main(String[] args) throws JMSException, InterruptedException {

        String url = "tcp://localhost:61616";

        if (args.length > 0) {
            url = args[0];
        }

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Destination destination = new ActiveMQQueue("netflix-queue");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);

        // Start print message
        for (; ; ) {
            System.out.println("Waiting for message.");
            Message message = consumer.receive();
            if (message == null) {
                break;
            }
            System.out.println("Got message, send to the analytic engine : " + message);
            System.out.println("Got message, send mail to the user : " + ((TextMessage) message).getText());

        }

        connection.close();
    }
}
