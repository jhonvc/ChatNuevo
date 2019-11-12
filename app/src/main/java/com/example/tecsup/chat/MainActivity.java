package com.example.tecsup.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    EditText usuario;
    Button btn_loging;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       EnlazarViews();
       SocketesCodigo app=(SocketesCodigo)getApplication();
       socket=app.getMisocket();
       socket.connect();
       btn_loging.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               socket.emit("add user",usuario.getText().toString());
           }
       });
       socket.on("login",onLoging);



    }
    void EnlazarViews(){
        usuario=findViewById(R.id.txt_chat);
        btn_loging=findViewById(R.id.btn_chat);


    }
    private Emitter.Listener onLoging=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    int cantidad=0;
                    try {
                        cantidad = data.getInt("numUsers");

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,cantidad+"",Toast.LENGTH_SHORT).show();
                }
            });

        }
    };
}
