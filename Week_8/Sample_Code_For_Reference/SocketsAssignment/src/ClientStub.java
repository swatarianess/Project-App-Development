import java.io.*;
import java.net.Socket;


class ClientStub {
    BufferedReader in;
    PrintStream out;
    boolean exit;

    ClientStub() {
        in   = new BufferedReader(new InputStreamReader(System.in));
        out  = new PrintStream(System.out);
        exit = false;
    }

    boolean sayHello(BufferedReader srvIn, PrintWriter srvOut)
            throws IOException {
    /* todo: ask the user which name they want to use, and 
       then perform a handshake with the server */

        out.println("Name? :");

        if(in.ready()) {
            String line = in.readLine();
            srvOut.println("HELLO-FROM " + line);
            return srvIn.readLine().equals("HELLO " + line);
        } else {
            return false;
        }

    }

    /**
     * @param srvIn BufferedReader of server where message received from the server
     * @param srvOut Printwriter to send message to send to the server
     * @throws IOException Exception if there is a problem with the input
     */
    void handleInput(BufferedReader srvIn, PrintWriter srvOut)
            throws IOException {
        String line = in.readLine();
        if(line == null) {
            out.println("could not read line from user");
            return;
        }

    /* supported commands:
       !who          - perform a WHO request to the server
       @<user> <msg> - send <msg> to <user>
       !exit         - stop the program */
        if(line.equals("!who")) {
            requestUserList(srvIn, srvOut);
        }
        else if(line.startsWith("@")) {
            sendMessage(line, srvIn, srvOut);
        }
        else if(line.equals("!exit")) {
            exit = true;
        }
        else {
            out.println("unknown command");
        }
    }

    /**
     * @param srvIn List of persons on the server sent as a response from the server
     * @param srvOut
     * @throws IOException
     */
    void requestUserList(BufferedReader srvIn, PrintWriter srvOut)
            throws IOException {
    /* todo: send a who request to srvOut, read the response from 
       srvIn, and display it on out */
        srvOut.println("WHO");
        if(srvIn.ready()){
            out.println(srvIn.lines());
        }

    }

    void sendMessage(String line, BufferedReader srvIn, PrintWriter srvOut)
            throws IOException {
    /* todo: send the message stored in the String line on srvOut 
       after parsing it as needed, making sure to check for any 
       errors returned on srvIn */
        if(srvIn.ready()){
            if(srvIn.readLine().contains("HELLO-FROM")) {
                srvOut.append(line);
            }
        }

    }

    void recvMessage(BufferedReader srvIn)
            throws IOException {
        out.println(srvIn);
    }

    void start(String[] args) throws IOException {
        if(args.length != 2) {
            out.println("usage: java ClientStub <server> <port>");
            return;
        }

        Socket socket          = new Socket(args[0],Integer.parseInt(args[1]));
        BufferedReader srvIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter srvOut   = new PrintWriter(socket.getOutputStream());

        if(!sayHello(srvIn, srvOut)) {
            out.println("Invalid");
            return;
        }

        while(!exit) {
            try {
                if(in.ready()) {
                    handleInput(srvIn, srvOut);
                }
                if(srvIn.ready()) {
                    recvMessage(srvIn);
                }
                Thread.sleep(200);
            }
            catch(IOException | InterruptedException e) {
                out.println(e.getMessage());
            }
        }
        socket.close();
    }

    public static void main(String[] args){
        try {
            new ClientStub().start(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
