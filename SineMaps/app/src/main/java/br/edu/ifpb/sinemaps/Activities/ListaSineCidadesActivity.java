package br.edu.ifpb.sinemaps.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.sinemaps.AsyncTask.SineAsyncTask;
import br.edu.ifpb.sinemaps.Entidades.Sine;
import br.edu.ifpb.sinemaps.R;

public class ListaSineCidadesActivity extends Activity {

    ListView lvSinesProximos;
    List<Sine> sineRegiao;
    ArrayAdapter<Sine> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sine_cidades);

        Bundle bundle = getIntent().getExtras();

        String latitude = bundle.getString("Lat"), longitude = bundle.getString("Long");

        lvSinesProximos = (ListView) findViewById(R.id.lvSinesProximos);
        SineAsyncTask processo = new SineAsyncTask();

        try {
            sineRegiao = processo.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/latitude/" +latitude+"/longitude/"+longitude+"/raio/100").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adaptador = new ArrayAdapter<Sine> (this, android.R.layout.simple_list_item_1, sineRegiao);
        lvSinesProximos.setAdapter(adaptador);

    }

}
