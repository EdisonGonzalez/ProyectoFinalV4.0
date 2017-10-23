package incorporation.app.primera.mi.proyectofinal.Principio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import incorporation.app.primera.mi.proyectofinal.R;

public class Registrarse extends AppCompatActivity {

    private String correo, contraseña, repeat;
    EditText Correo, Contraseña, Repcontraseña;
    int flag_correo = 0, flag_repcontra = 0, flag_contra = 0;

    SharedPreferences datosSP;
    SharedPreferences.Editor Mieditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principio_activity_registrarse);

        Correo = (EditText) findViewById(R.id.correo);
        Contraseña = (EditText) findViewById(R.id.contraseña);
        Repcontraseña = (EditText) findViewById(R.id.repcontraseña);
    }

    public void crear_usuario(View view) {

        correo = Correo.getText().toString();
        contraseña = Contraseña.getText().toString();
        repeat = Repcontraseña.getText().toString();

        if (TextUtils.isEmpty(correo)) {
            Correo.setError("Campo vacio");
            flag_correo = 1;
        } else {
            Correo.setError(null);
        }

        if (TextUtils.isEmpty(contraseña)) {
            Contraseña.setError("Campo vacio");
            flag_contra = 1;
        } else {
            Contraseña.setError(null);
        }

        if (TextUtils.isEmpty(repeat)) {
            Repcontraseña.setError("Campo vacio");
            flag_repcontra = 1;
        } else {
            Repcontraseña.setError(null);
        }
        if (!isEmailValid(correo)) {
            Correo.setError("Email no válido");
        } else {

            if ((flag_correo == 1) || (flag_contra == 1) || (flag_repcontra == 1)) {
                flag_correo = 0;
                flag_correo = 0;
                flag_repcontra = 0;
                Toast.makeText(getApplicationContext(), "¡¡Faltan campos por llenar!!", Toast.LENGTH_SHORT).show();

            } else if ((!contraseña.equals(repeat))) {

                Toast.makeText(getApplicationContext(), "¡¡Contraseñas diferentes!!", Toast.LENGTH_SHORT).show();

            } else {
                Intent intent = new Intent();
                datosSP = PreferenceManager.getDefaultSharedPreferences(this);
                Mieditor = datosSP.edit();
                Mieditor.putString("correo", correo);
                Mieditor.putString("contraseña", contraseña);
                Mieditor.apply();
                setResult(RESULT_OK, intent);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                finish();
            }
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);

    }
}
