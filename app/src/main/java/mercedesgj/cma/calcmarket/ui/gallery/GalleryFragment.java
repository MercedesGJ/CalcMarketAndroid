package mercedesgj.cma.calcmarket.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import mercedesgj.cma.calcmarket.R;
import mercedesgj.cma.calcmarket.ui.home.HomeFragment;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    public Spinner SelMon;
    public RecyclerView lista;
    private DatabaseReference mDatabase;
    public EditText monedaactual;
    public EditText monedanueva;
    public float mon = HomeFragment.MonedaAct();
    String monedasel;
    String sconv;
    public ImageButton cal;
    float num;
    DecimalFormat formatodecimales = new DecimalFormat("#.##");

    String[] array;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        SelMon = root.findViewById(R.id.SpiSelMon);
        monedaactual = root.findViewById(R.id.txtMonedaActual);
        monedanueva = root.findViewById(R.id.txtMonedaNew);
        cal = root.findViewById(R.id.btnCalcConv);
        monedaactual.setKeyListener(null);
        monedanueva.setKeyListener(null);
        monedaactual.setText(String.valueOf(mon));
        //Base de datos

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Array combo box

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.Monedas, android.R.layout.simple_spinner_item);
        SelMon.setAdapter(adapter);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calc();
            }
        });



        return root;
    }
    public void Calc(){
        monedasel = SelMon.getSelectedItem().toString();
        sconv = null;
                mDatabase.child("Monedas").child("EUR").child(monedasel).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            num = dataSnapshot.getValue(float.class);
                          //  String nombre = dataSnapshot.child("EUR").getValue(String.class);
                            sconv = String.valueOf(num);
                            System.out.println(" float " + num + " String " + sconv);
                            System.out.println(num);
                            float fin = mon * num;

                           // fin = Float.parseFloat(formatodecimales.format(fin));
                            BigDecimal resufinDecimal = new BigDecimal(fin).setScale(2, RoundingMode.UP);
                            System.out.println(fin);
                            monedanueva.setText( String.valueOf(resufinDecimal));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



    }
}