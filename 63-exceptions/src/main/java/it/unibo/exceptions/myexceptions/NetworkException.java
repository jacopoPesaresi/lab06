package it.unibo.exceptions.myexceptions;

import java.io.IOException;

public class NetworkException extends IOException{

    public NetworkException(){
        new IOException("Network error: no response");
    }

    public NetworkException(String networkErrMessage){
        new IOException("Network error while sending message: " + networkErrMessage);
    }
}