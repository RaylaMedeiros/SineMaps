package br.edu.ifpb.sinemaps.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifpb.sinemaps.R;

public class SineRegiaoActivity extends Activity {
    public final String LAT_JP = "-7.1194958", LONG_JP = "-34.8448668" ;
    public final String LAT_CG = "-7.2290752", LONG_CG = "-35.8808337" ;
    public final String LAT_CJ = "-6.8897071", LONG_CJ = "-38.5612185" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sine_regiao);
    }

        public void joaoPessoa (View view){
            Intent intent = new Intent(SineRegiaoActivity.this, ListaSineCidadesActivity.class);
            // passa coordenadas de JP
            intent.putExtra("Lat", LAT_JP);
            intent.putExtra("Long", LONG_JP);

            startActivity(intent);
        }

        public void campinaGrande (View view){
            Intent intent = new Intent(SineRegiaoActivity.this, ListaSineCidadesActivity.class);
            // passa coordenadas de CG
            intent.putExtra("Lat", LAT_CG);
            intent.putExtra("Long", LONG_CG);
            startActivity(intent);
        }

        public void cajazeiras (View view){
            Intent intent = new Intent(SineRegiaoActivity.this, ListaSineCidadesActivity.class);
            // passa coordenadas de CJ
            intent.putExtra("Lat", LAT_CJ);
            intent.putExtra("Long", LONG_CJ);
            startActivity(intent);
        }

}
