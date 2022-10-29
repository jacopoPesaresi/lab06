package it.unibo.exceptions.myexceptions;

import java.io.IOException;

public class NetworkException extends IOException{

    public NetworkException(){
        new Exception("Network error: no response");
    }

    public NetworkException(String networkErrMessage){
        new Exception("Network error while sending message: " + networkErrMessage);
    }
}