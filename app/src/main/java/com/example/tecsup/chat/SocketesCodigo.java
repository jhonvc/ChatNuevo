package com.example.tecsup.chat;

import android.app.Application;
import android.util.Log;

import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketesCodigo extends Application {
    Socket miSocket;
    {
        try {
            miSocket= IO.socket("http://172.23.8.98:3000/");
        }catch (Exception e){
            Log.e("Error",e.toString());

        }

    }
    public  Socket getMisocket(){
        return  miSocket;
    }
}