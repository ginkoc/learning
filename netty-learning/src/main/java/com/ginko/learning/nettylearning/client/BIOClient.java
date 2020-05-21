package com.ginko.learning.nettylearning.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ginko
 * @date 4/22/20
 */
public class BIOClient {

    private final String host;
    private final int port;

    public BIOClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() {
        final Scanner reader = new Scanner(System.in);

        Socket socket = null;
        PrintWriter os = null;
        BufferedReader is = null;

        try {
            socket = new Socket(host, port);
            os = new PrintWriter(socket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Connect server failed");

            try {
                if (socket != null) {
                    socket.close();
                }

                if (os != null) {
                    os.close();
                }
            } catch (IOException ex) {
                // do nothing
            }
            return;
        }

        String messageFromCli;
        while (reader.hasNextLine()) {
            messageFromCli = reader.nextLine();
            if ("exit".equalsIgnoreCase(messageFromCli)) {
                break;
            }

            os.println(messageFromCli);
            os.flush();
        }

        try {
            reader.close();
            socket.close();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "Usage: " + BIOClient.class.getSimpleName() +
                            " <host> <port>");
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        new BIOClient(host, port).start();
    }
}
