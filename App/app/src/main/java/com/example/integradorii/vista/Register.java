package com.example.integradorii.vista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradorii.Api.ApiService;
import com.example.integradorii.R;
import com.example.integradorii.estructura.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

//    private Button btRegister;
//    private ImageButton btCerrar;
//    private EditText etUsuario, etNombre, etFechaNacimiento, etTelefono, etCorreo, etPassword, etPasswordConfi;
//    private Spinner spinnerGender;

    ImageButton back;
    Button btnRegister;
    TextView backLogin;
    Retrofit retrofit;
    String IP = "http://192.168.1.7:3000/", gender;
    TextInputEditText namein, userin, emailin, passin, phonein, datein;
    boolean active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        back = findViewById(R.id.btn_back);
        backLogin = findViewById(R.id.go_to_login);

        namein = findViewById(R.id.namein);
        userin = findViewById(R.id.userin);
        emailin = findViewById(R.id.emailin);
        passin = findViewById(R.id.passin);
        phonein = findViewById(R.id.phonein);
        phonein = findViewById(R.id.phonein);
        datein = findViewById(R.id.datein);
        btnRegister = findViewById(R.id.btn_register);
        gender = "M";
        active = true;

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentLogin();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
        }
    public void intentLogin() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }

    public void sendData() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectionSpecs(Arrays.asList(
                        ConnectionSpec.CLEARTEXT,
                        ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.COMPATIBLE_TLS))
                .build();
        try{
            retrofit = new Retrofit.Builder()
                    .baseUrl(IP)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            Log.e("olaaa", String.valueOf(userin.getText()));
            response(new User(String.valueOf(userin.getText()), String.valueOf(namein.getText()), String.valueOf(datein.getText()),
                    String.valueOf(emailin.getText()), String.valueOf(passin.getText()), String.valueOf(phonein.getText()),
                    gender, active));
        }catch (Exception e){
            Log.e("error", "error en retrofit");
        }
    }

    public void response(User userInfo) {
        ApiService apiService = retrofit.create(ApiService.class);
        Call<User> anwerRegisterCall = apiService.register(userInfo);
        anwerRegisterCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    intentLogin();
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("ola", response.errorBody().toString());
                    Log.e("ola", response.toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Algo sali+o mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

//        btRegister= findViewById(R.id.btRegister);
//        btCerrar = findViewById(R.id.cerrar);
//
//
//        etUsuario = findViewById(R.id.userRegister);
//        etNombre = findViewById(R.id.nameRegister);
//        etFechaNacimiento = findViewById(R.id.dateRegister);
//        etTelefono = findViewById(R.id.phoneRegister);
//        etCorreo = findViewById(R.id.mailRegister);
//        etPassword = findViewById(R.id.paswordRegister);
//        etPasswordConfi = findViewById(R.id.paswordValidarRegister);
//
//
//        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(threadPolicy);
//
//        spinnerGender = findViewById(R.id.gender);
//
//        // Definir un array de strings para los géneros
//        String[] generos = {"M", "H", "I"};
//
//        // Crear un ArrayAdapter utilizando el array de géneros y el diseño predeterminado
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Aplicar el ArrayAdapter al Spinner
//        spinnerGender.setAdapter(adapter);
//
//        btRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                register();
//            }
//        });
//
//        btCerrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                atras(v);
//            }
//        });
//    }
//
//
//    public void register(){
//        try {
//            final Model model = new Model();
//            String userRegister = etUsuario.getText().toString();
//            String nombre=etNombre.getText().toString();
//            String fecha = etFechaNacimiento.getText().toString();
//            String correo = etCorreo.getText().toString();
//            String pin=etPassword.getText().toString();
//            String pin2=etPasswordConfi.getText().toString();
//            String tel = etTelefono.getText().toString();
//            String genero = spinnerGender.getSelectedItem().toString();
//
//
//            if(genero.isEmpty() && tel.isEmpty() && correo.isEmpty() && nombre.isEmpty() && pin.isEmpty() && pin2.isEmpty() && pin.equals(pin2)){
//                Toast.makeText(this, "ingrese todos los datos", Toast.LENGTH_SHORT).show();
//
//            }else if( tel.length() != 10 || tel.matches("[a-zA-Z\\s]+")) {
//                Toast.makeText(this, "debes ingresar un telefono valido", Toast.LENGTH_SHORT).show();
//
//            }else if (!correo.contains("@") || !correo.contains(".") || correo.indexOf("@") == 0 || correo.lastIndexOf(".") == correo.length() - 1 || correo.lastIndexOf(".") - correo.indexOf("@") <= 1) {
//                Toast.makeText(this, "debes ingresar el correo valido", Toast.LENGTH_SHORT).show();
//
//            }else if(!nombre.matches(".*[a-zA-Z].*")){
//                Toast.makeText(this, "debes ingresar el nombre valido", Toast.LENGTH_SHORT).show();
//
//            }else if(pin.length() != 4|| pin.matches("[a-zA-Z\\s]+")) {
//                Toast.makeText(this, "debes ingresar los 4 digitos del pin", Toast.LENGTH_SHORT).show();
//
//            } else if (!pin.equals(pin2)){
//                Toast.makeText(this, "no coinciden los 2 pines", Toast.LENGTH_SHORT).show();
//
//            }else if (pin.matches("(\\d)\\1{3}")) {
//                Toast.makeText(this, "El PIN no puede tener 4 números iguales", Toast.LENGTH_SHORT).show();
//
//            } else if (hasConsecutiveNumbers(pin)) {
//                Toast.makeText(this, "El PIN no puede contener ser consecutivos", Toast.LENGTH_SHORT).show();
//            }else {
//
//
//
//
//                model.registerUser(userRegister, nombre,fecha,correo,pin,null,tel,genero, new Model.UserCallback() {
//                    @Override
//                    public void onSuccess(User user) {
//                        // Usuario autenticado correctamente
//                        Toast.makeText(Register.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(Register.this, Home.class);
//                        intent.putExtra("correo",correo);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        // Error de autenticación
//                        Toast.makeText(Register.this, "Error: Credenciales incorrectas", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Error al guardar los datos del usuario",
//                    Toast.LENGTH_SHORT).show();
//            Log.e("Error",e.getMessage());
//        }
//    }
//
//    private boolean hasConsecutiveNumbers(String text) {
//        char prevChar = '\0';
//        int consecutiveCount = 1;
//        for (int i = 0; i < text.length(); i++) {
//            char currentChar = text.charAt(i);
//            if (Character.isDigit(currentChar)) {
//                if (prevChar != '\0' && prevChar + 1 == currentChar) {
//                    consecutiveCount++;
//                    if (consecutiveCount == 4) {
//                        return true;
//                    }
//                } else {
//                    consecutiveCount = 1;
//                }
//                prevChar = currentChar;
//            }
//        }
//        return false;
//    }
//
//    public void atras(View view){
//        onBackPressed();
//    }
    }


