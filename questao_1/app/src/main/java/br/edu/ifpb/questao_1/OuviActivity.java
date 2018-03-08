package br.edu.ifpb.questao_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by jozimar on 08/03/18.
 */

public class OuviActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouvi);
    }

    public void abrirTelaMain(View v){

        Intent telaMain = new Intent(this, MainActivity.class);
        startActivity(telaMain);
    }

    private AlertDialog alerta;

    public void mensagem(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");

        builder.setMessage("em construção");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(OuviActivity.this, "positivo=" + arg1, Toast.LENGTH_SHORT);
            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
