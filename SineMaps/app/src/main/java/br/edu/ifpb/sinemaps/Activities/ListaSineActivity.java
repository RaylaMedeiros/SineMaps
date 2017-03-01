package br.edu.ifpb.sinemaps.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.sinemaps.AsyncTask.BuscarSineAsyncTask;
import br.edu.ifpb.sinemaps.AsyncTask.SineAsyncTask;
import br.edu.ifpb.sinemaps.Entidades.Sine;
import br.edu.ifpb.sinemaps.Entidades.SineDetalhado;
import br.edu.ifpb.sinemaps.R;

public class ListaSineActivity extends AppCompatActivity {

    ListView lvSines;
    List<Sine> listaSine;
    ArrayAdapter<Sine> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sine);

        lvSines = (ListView) findViewById(R.id.listViewSine);
        SineAsyncTask processo = new SineAsyncTask();

        try {
            listaSine = processo.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/").get();
            adaptador = new ArrayAdapter<Sine> (this, android.R.layout.simple_list_item_1, listaSine);
            lvSines.setAdapter(adaptador);

            lvSines.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String codPost = (String) adaptador.getItem(position).getCodPosto();
                    ListView listDet = (ListView) findViewById(R.id.listViewSine);
                    BuscarSineAsyncTask listSineDet = new BuscarSineAsyncTask();
                    try {
                        ArrayAdapter<SineDetalhado> adapter = new ArrayAdapter<SineDetalhado>(view.getContext(), android.R.layout.simple_list_item_1, listSineDet.execute("http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/cod/" + codPost).get());
                        listDet.setAdapter(adapter);

                    } catch (InterruptedException|ExecutionException e){
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
