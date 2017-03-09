package br.edu.ifpb.sinemaps.Activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.sinemaps.AsyncTask.SineAsyncTask;
import br.edu.ifpb.sinemaps.Entidades.Sine;
import br.edu.ifpb.sinemaps.R;

public class BuscarSinesActivity extends Activity {

    EditText codPosto;
    ListView sine;
    List<Sine> listaSine;
    ArrayAdapter<Sine> adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_sines);

        codPosto = (EditText) findViewById(R.id.codPosto);
        sine = (ListView) findViewById(R.id.sine);
        listaSine = new ArrayList<>();
    }

    public void buscarSinesCod(View view){

        String cod = codPosto.toString();

        try {
            listaSine.addAll(new SineAsyncTask().execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/cod/"+cod).get());
            adaptador = new ArrayAdapter<Sine> (this, android.R.layout.simple_list_item_1, listaSine);
            sine.setAdapter(adaptador);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
