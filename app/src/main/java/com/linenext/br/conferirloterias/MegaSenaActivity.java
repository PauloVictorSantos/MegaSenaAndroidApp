package com.linenext.br.conferirloterias;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MegaSenaActivity extends Activity {
    ArrayList<HashMap<String, String>> contactList;
    private ListView lv;
    private EditText filterText;
    private SimpleAdapter adapter;
    private Button btnPesquisa;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        filterText =(EditText) findViewById(R.id.pesquisar);
        btnPesquisa= (Button) findViewById(R.id.btnBuscar);
        filterText.setText("");

        if(isOnline()){
            new MegaSenaTask().execute();
        }else{
            Toast.makeText(this, R.string.connectivity_service, Toast.LENGTH_LONG).show();
            btnPesquisa.setEnabled(false);
        }

        btnPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactList.clear();
                adapter.notifyDataSetChanged();
                new MegaSenaTask().execute();

            }
        });
        MobileAds.initialize(this, "ca-app-pub-7016423715131781/7580221755");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public class MegaSenaTask extends AsyncTask<Object,Object,MegaSena> {



        protected MegaSena doInBackground(Object... param){
            StringBuilder resposta= new StringBuilder();
            try{

                URL url = new URL("https://www.lotodicas.com.br/api/mega-sena/"+filterText.getText());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(false);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {

                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }catch (IOException e ){
                e.printStackTrace();
            }
              return new Gson().fromJson(resposta.toString(), MegaSena.class);
        }

        @Override
        protected void onPostExecute(MegaSena megaSena) {
            super.onPostExecute(megaSena);

            HashMap<String, String> contact =new HashMap<String,String>();
            contact.put("numero",Integer.toString(megaSena.getNumero()));
            contact.put("data",DateFormat.getDateInstance(DateFormat.LONG).format(megaSena.getData()));
            contact.put("sorteio", Arrays.toString(megaSena.getSorteio()).replaceAll("\\[|\\]", "").toString());
            contact.put("sena", String.valueOf((megaSena.getGanhadores()[0]==0)?"Não houve acertador":String.valueOf(megaSena.getGanhadores()[0]+" aposta(s) ganhador(as) de R$" +String.valueOf(megaSena.getRateio()[0]).replace(".",","))));
            contact.put("quina", String.valueOf(megaSena.getGanhadores()[1]+" aposta(s) ganhador(as) de R$ "+String.valueOf(megaSena.getRateio()[1])));
            contact.put("quadra", String.valueOf(megaSena.getGanhadores()[2]+" aposta(s) ganhador(as) de R$ "+String.valueOf(megaSena.getRateio()[2])));
            contact.put("acumulado", megaSena.getAcumulado().equalsIgnoreCase("sim")?"Acumulou!":"Não acumulou!");
            contact.put("valor_acumulado", megaSena.getValor_acumulado());
            contact.put("cidades", Arrays.toString(megaSena.getCidades()).replaceAll("\\[|\\]", "").toString());
            contact.put("proxima_estimativa", megaSena.getProxima_estimativa().toString());
            contact.put("proxima_data",DateFormat.getDateInstance(DateFormat.LONG).format(megaSena.getProxima_data()));

            contactList.add(contact);

            adapter = new SimpleAdapter(
                    MegaSenaActivity.this, contactList,
                    R.layout.list_item, new String[]{
                    "numero", "data", "sorteio","sena","quina","quadra","acumulado",
                    "valor_acumulado","proxima_estimativa","proxima_data"},
                    new int[]{R.id.numero, R.id.data, R.id.sorteio,R.id.sena,R.id.quina,R.id.quadra, R.id.acumulado,R.id.valor_acumulado, R.id.proxima_estimativa,R.id.proxima_data});

            lv.setAdapter(adapter);
        }
    }



}
