package mercedesgj.cma.calcmarket.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;

import mercedesgj.cma.calcmarket.R;

public class SlideshowFragment extends Fragment {

    private EditText correo;
    private EditText password;
    public ImageButton btnGoogle;
    public Button inisesion;
    public Button registrarse;

    private FirebaseAuth mAuth;

    public String email;
    public String contraseña;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        mAuth = FirebaseAuth.getInstance();

        correo = root.findViewById(R.id.txtUsuario);
        password = root.findViewById(R.id.txtcontraseña);

        btnGoogle = root.findViewById(R.id.btnIniGoogle);
        inisesion = root.findViewById(R.id.btnIniciarSesión);
        registrarse = root.findViewById(R.id.btnRegistrarse);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = correo.getText().toString();
                contraseña = password.getText().toString();

                if(!email.isEmpty() && !contraseña.isEmpty()){
                    if(contraseña.length() > 6){

                    }else {
                        Toast.makeText(root.getContext(), "La contraseña tiene que tener más de 6 carácteres", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(root.getContext(), "No puede estar vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}