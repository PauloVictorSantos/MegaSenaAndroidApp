package com.linenext.br.conferirloterias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MegaSenaActivity extends AppCompatActivity {
    private TextView textView;
    private ArrayAdapter<MegaSena> adapter;
    private  int adapterlayout = android.R.layout.simple_list_item_1;
    //private List<MegaSena> listNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.result);
        try {
            MegaSena retorno = new MegaSenaTask().execute().get();
            textView.setText(retorno.toString());
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
