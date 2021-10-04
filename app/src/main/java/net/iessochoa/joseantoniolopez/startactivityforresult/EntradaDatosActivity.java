package net.iessochoa.joseantoniolopez.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

//fijaros que la actividad implementa el lintener del click de los botones
public class EntradaDatosActivity extends AppCompatActivity implements View.OnClickListener {
    //Constantes para identificar los valores Extra que se envían a la actividad EntradaDatos
    //Es constumbre identificarlas mediante el nombre del paquete
    public final static String EXTRA_DATOS_RESULTADO ="net.iessochoa.joseantoniolopez.startactivityforresult.entradadatosactivity.resultado";
    //Constantes para identificar los valores Extra que se envían a la actividad EntradaDatos
    //Es constumbre identificarlas mediante el nombre del paquete
    public final static String EXTRA_DATOS="net.iessochoa.joseantoniolopez.startactivityforresult.entradadatosactivity.datos";
    EditText et_datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_datos);

        et_datos= findViewById(R.id.et_datos);

        //Manejaremos el evento del botón en el mismo Listener
        findViewById(R.id.btn_Ok).setOnClickListener(this);
        findViewById(R.id.btn_Cancel).setOnClickListener(this);

        //obtenemos el valor de la actividad llamadora y lo mostraremos
        et_datos.setText(getIntent().getStringExtra(EXTRA_DATOS));
    }

    /**
     * Este método implementa el evento de los botones ok y cancel
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Ok:
                // Si el EditText no está vacío enviamos el resultado
                if(et_datos.getText().length()!=0) {
                    //guardamos el resultado en el Intent que llamó la actividad, aunque
                    //podríamos crear uno nuevo
                    Intent iBack = getIntent();

                    iBack.putExtra(EXTRA_DATOS_RESULTADO,et_datos.getText().toString());
                    //indicamos que se ha pulsado aceptar y enviamos el Intent
                    setResult(RESULT_OK,iBack);
                    //cerramos la actividad
                    finish();
                }
                break;
            case R.id.btn_Cancel:
                //Indicamos que el usuario ha pulsado Cancelar
                setResult(RESULT_CANCELED);
                //cerramos la actividad
                finish();
                //si el usuario pulsa el botón de back también se devuelve Canceled
                break;
        }
    }
}
