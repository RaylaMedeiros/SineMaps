package br.edu.ifpb.sinemaps.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.sinemaps.AsyncTask.SineAsyncTask;
import br.edu.ifpb.sinemaps.Entidades.Sine;
import br.edu.ifpb.sinemaps.R;

public class SineRegiaoActivity extends Activity {
    String latitudeJP = "-7.1194958",longitudeJP = "-34.8448668" ;
    String latitudeCG = "-7.2290752",longitudeCG = "-35.8808337" ;
    String latitudeCJ = "-6.8897071",longitudeCJ = "-38.5612185" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sine_regiao);
    }

        public void joaoPessoa (View view){
            Intent intent = new Intent(SineRegiaoActivity.this, ListaSineCidades.class);
            // passa coordenadas de joão pessoa
            intent.putExtra("LatJP", latitudeJP);
            intent.putExtra("LongJP", longitudeJP);
            intent.putExtra("LatCG", latitudeJP);
            intent.putExtra("LongCG", longitudeJP);
            intent.putExtra("LatCJ", latitudeJP);
            intent.putExtra("LongCJ", longitudeJP);

            startActivity(intent);
        }

        public void campinaGrande (View view){
            Intent intent = new Intent(SineRegiaoActivity.this, ListaSineCidades.class);
            // passa coordenadas de campina grande
            intent.putExtra("LatCG", latitudeCG);
            intent.putExtra("LongCG", longitudeCG);
            intent.putExtra("LatJP", latitudeCG);
            intent.putExtra("LongJP", longitudeCG);
            intent.putExtra("LatCJ", latitudeCG);
            intent.putExtra("LongCJ", longitudeCG);
            startActivity(intent);
        }

        public void cajazeiras (View view){
            Intent intent = new Intent(SineRegiaoActivity.this, ListaSineCidades.class);
            // passa coordenadas de cajazeiras
            intent.putExtra("LatCJ", latitudeCJ);
            intent.putExtra("LongCJ", longitudeCJ);
            intent.putExtra("LatCG", latitudeCJ);
            intent.putExtra("LongCG", longitudeCJ);
            intent.putExtra("LatJP", latitudeCJ);
            intent.putExtra("LongJP", longitudeCJ);
            startActivity(intent);
        }

}
