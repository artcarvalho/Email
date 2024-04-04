package artur.renata.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Selectiona nosso formulário de email
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString(); //Instancia nosso objeto com o valor digitado no campo email

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString(); // Salva o valor digitado em assunto na variavel assunto

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString(); //Guarda o valor digitado no editTexto Texto

                //Inicializa uma intenção externa, para todos os aplicativos passiveis de enviar emails
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));

                String[] emails = new String[] {email};
                //envia as informações através da intent
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try { //tenta achar apps que enviam email
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                } // caso nao ache
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum APP que possa realizar esta operação.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}