package mercedesgj.cma.calcmarket.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavType;

import java.util.ArrayList;

import mercedesgj.cma.calcmarket.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public TableLayout table;
    public TextView nombre;
    public TextView Precio;
    public TextView Productos;
    public ImageButton guardar;
    private String[]header= {"Precio","Productos","Nombre"};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TableDynamic tableDynamic;
    TextView precio;
    double preu;
    int productos;
    static float total;
    String mos;
    private ImageButton eliminar;
    private ImageButton calcular;
    public boolean esta;
    private TableLayout tableguar;
    ArrayList<String> guardao;
    Bundle estadogua;
    ArrayList<String[]> again = TableDynamic.SacarArray();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        nombre = root.findViewById(R.id.TextoNombre);
        Productos = root.findViewById(R.id.TextoProductos);
        Precio = root.findViewById(R.id.TextoPrecio);
        table = root.findViewById(R.id.Table);
        guardar = root.findViewById(R.id.btnGuardar);
        precio = root.findViewById(R.id.txtprecio);
        eliminar = root.findViewById(R.id.btnEliminar);
        calcular = root.findViewById(R.id.btnCalcular);

        estadogua = savedInstanceState;


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
                Calcular();
            }
        });



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comprobar();
                if(esta == false){
                    if(Productos.getText().toString().matches("[0-9]+") && !Precio.getText().toString().matches("[a-zA-Z]+") ){
                        Guardar();
                        Calcular();
                    }else{
                        Context context = root.getContext();
                        CharSequence text = "¡¡Se calcula con números, !!SOLO NÚMEROS!!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }else{
                    Context context = root.getContext();
                    CharSequence text = "¡¡Los nombres no se pueden repetir!!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }



            }
        });

        table.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Calcular();
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calcular();
            }
        });

        if(again == null){
            tableDynamic = new TableDynamic(table, getContext());
            tableDynamic.addHeader(header);
        }else {
            tableDynamic = new TableDynamic(table, getContext());
            tableDynamic.addHeader(header);
            String[] ci = new String[again.size()];
            for (int i = 0; i < again.size(); i++){
                ci= again.get(i);
                Restaurar(ci);
            }

        }




      //  tableDynamic.addData(getClients());
     //   tableDynamic.backgroundHeader(Color.BLUE);
      //  tableDynamic.textColorheader(Color.CYAN);

        //tableDynamic.textColorData(Color.BLACK);
      //  tableDynamic.lineColor(Color.BLACK);

        return root;


    }
/*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("Todo", estadogua);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(estadogua != null)
        estadogua = savedInstanceState.getBundle("Todo");

    }
*/
    private ArrayList<String[]> getClients(){
        rows.add(new String[]{" "," "," "});
       // rows.add(new String[]{" "," "," "});
       // rows.add(new String[]{" "," "," "});
       // rows.add(new String[]{" "," "," "});
        return rows;
    }
    public void Guardar(){
        String[] item = new String[]{Precio.getText().toString(),Productos.getText().toString(), nombre.getText().toString()};

        tableDynamic.addItem(item);
       // tableDynamic.backgroundData(Color.RED, Color.RED);

    }
    public void Restaurar(String[] restaurar){

        tableDynamic.addItem(restaurar);
    }
    public void Eliminar(){
        tableDynamic.EliminarRow();
    }

    public void Calcular(){
      precio.setText(tableDynamic.Precio());
      total = (float) Double.parseDouble(tableDynamic.Precio());
    }
    public boolean Comprobar(){
        esta = tableDynamic.comprobar(nombre.getText().toString());
        return esta;
    }
    public static float MonedaAct(){
        return total;
    }
}