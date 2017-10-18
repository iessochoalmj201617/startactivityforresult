package net.iessochoa.joseantoniolopez.startactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Este ejemplo nos permitirá aprender como podemos llamar a una activity
 * y obtener datos de la misma.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Estas constantes nos permitirán distiguir para qué llamamos la actividad EntradaDatos
    //en el evento onActivityResult
    public final static int OPTION_REQUEST_NOMBRE=0;
    public final static int OPTION_REQUEST_APELLIDO=1;

    //Constantes para identificar los valores Extra que se envían a la actividad EntradaDatos
    //Es constumbre identificarlas mediante el nombre del paquete
    public final static String EXTRA_DATOS="net.iessochoa.joseantoniolopez.startactivityforresult.main.datos";


    TextView tv_Nombre;
    TextView tv_Apellido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Nombre=(TextView) findViewById(R.id.tv_Nombre);
        tv_Apellido=(TextView) findViewById(R.id.tv_Apellido);

        //Manejaremos el evento del botón en el mismo Listener. Para ello fijaros como la activity implementa View.OnClickListener
        ((Button)findViewById(R.id.btn_Nombre)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_Apellido)).setOnClickListener(this);

    }

    /**
     * Maneja el click de los botones de la Activity
     * @param view: Boton que recibe el click
     */
    @Override
    public void onClick(View view) {
        Intent i;
        //creamos el intent para llamar a la acitividad
        i=new Intent(this,EntradaDatosActivity.class);
        switch (view.getId()) {
            case R.id.btn_Nombre:
                //enviamos el nombre actual
                i.putExtra(EXTRA_DATOS,tv_Nombre.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_NOMBRE);
                break;
            case R.id.btn_Apellido:
                //enviamos el apellido actual
                i.putExtra(EXTRA_DATOS,tv_Apellido.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_APELLIDO);
                break;
        }
    }

    /**
     * Cuando la actividad EntradaDatos termine, esta actividad llamará a este evento, donde
     * podremos obtener el resultado devuelto.
     * @param requestCode: si fue llamada para NOMBRE on APELLIDO
     * @param resultCode: Si el usuario pulsó ACEPTAR o CANCELAR
     * @param data: Datos devueltos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad no es  "RESULT_CANCELED".
        if (resultCode != RESULT_CANCELED) {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getStringExtra(EntradaDatosActivity.EXTRA_DATOS_RESULTADO);
            // Y tratamos el resultado en función de si se lanzó para rellenar el
            // nombre o el apellido.
            switch (requestCode) {
                case OPTION_REQUEST_NOMBRE:
                    tv_Nombre.setText(resultado);
                    break;
                case OPTION_REQUEST_APELLIDO:
                    tv_Apellido.setText(resultado);
                    break;

            }
        }
    }


}
