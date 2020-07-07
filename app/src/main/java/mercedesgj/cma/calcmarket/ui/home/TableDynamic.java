package mercedesgj.cma.calcmarket.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Iterator;

public class TableDynamic {
    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]> data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;
    private boolean multicolor = false;
    int firstColor;
    int secondColor;
    int textColor;
    float precio;
    float productos;
    float total;
    public TableRow trselec;
    public int nsle;
    public int x;
    String v;
    static ArrayList<String[]> nuevo;
    private TableRow trAnterior;
    public ArrayList<String[]> dto = new ArrayList<>();

    public TableDynamic(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
        newRow();
        nuevo = dto;
    }

    public void addHeader(String[] header) {
        this.header = header;
        createHeader();

    }

    public void addData(ArrayList<String[]> data) {
        this.data = data;
        createDataTable();


    }

    private void newRow() {
        tableRow = new TableRow((context));
        nuevo = dto;

    }

    private void newCell() {
        txtCell = new TextView(context);
        txtCell.setGravity((Gravity.CENTER));
        txtCell.setTextSize(25);
        nuevo = dto;

    }

    private void createHeader() {
        indexC = 0;
        newRow();
        while (indexC < header.length) {
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow);

    }

    private void createDataTable() {
        String info;

        for (indexR = 0; indexR <= header.length; indexR++) {
            newRow();
            for (indexC = 0; indexC < 1; indexC++) {
                newCell();
                String[] row = data.get(0);
                info = (indexC < row.length) ? row[indexC] : "";

                txtCell.setText(info);
                tableRow.addView(txtCell, newTableRowParams());

            }
            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1, 1, 1, 1);
        params.weight = 1;
        return params;
    }

    public static ArrayList<String[]> SacarArray() {

        return nuevo;
    }

    public void addItem(String[] item) {
        String info;
        x++;
        newRow();
        dto.add(item);
        data = new ArrayList<String[]>();
        data.add(item);
        indexC = 0;
       /* for(int i = 0; i < dto.size(); i++){
            String[] z = dto.get(i);
            String s = z[2];
            String[] datac = data.get(i);
            String d = datac[2];
            if (s.equalsIgnoreCase(d)){

            }

        }*/

        while (indexC < header.length) {
            newCell();
            info = (indexC < item.length) ? item[indexC++] : "";
            txtCell.setText(info);
            txtCell.setTextColor(Color.BLACK);
            tableRow.addView(txtCell, newTableRowParams());

        }
        tableLayout.addView(tableRow, data.size());
        final TableRow tabletemp = tableRow;


        tableRow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                //TODO:

                // nsle = dto.listIterator(trselec.getId());
                trselec = tabletemp;

                trselec.setBackgroundColor(Color.MAGENTA);
                if (trAnterior != null) {
                    trAnterior.setBackgroundColor(Color.WHITE);
                }
                trAnterior = trselec;
                int c = 2;
                TextView prueba = (TextView) trselec.getChildAt(c);
                System.out.println("prueba de numero wee " + prueba.getText().toString());
                v = prueba.getText().toString();
                System.out.println("nsle + " + nsle);


                nuevo = dto;

                //  tableLayout.removeView(tabletemp);
            }
        });
        tableLayout.setBackgroundColor(Color.WHITE);


//        reColoring();
    }

    public boolean comprobar(String a) {
        boolean esta = false;
        for (int i = 0; i < dto.size(); i++) {
            String[] z = dto.get(i);
            String s = z[2];


            if (s.equalsIgnoreCase(a)) {
                esta = true;
            }

        }
        return esta;
    }

    public void EliminarRow() {

        for (int i = 0; i < dto.size(); i++) {
            String[] z = dto.get(i);
            String s = z[2];

            if (s.equalsIgnoreCase(v)) {
                dto.remove(i);
            }

        }
        //  dto.remove(nsle);
        tableLayout.removeView(trselec);
    }

    public String Precio() {

        String preu;
        total = 0;
        precio = 0;
        productos = 0;

        for (indexC = 0; indexC < dto.size(); indexC++) {

            String[] as = dto.get(indexC);
            String pr = new String(as[0]);

            precio = Float.parseFloat(pr);
            System.out.println("Esto es el error pr " + pr);
            String[] s = dto.get(indexC);
            String pro = new String(s[1]);
            productos = Float.parseFloat(pro);
            System.out.println(pro);


            total += precio * productos;


        }


        return String.valueOf(total);
    }

    private TableRow getRow(int index) {
        return (TableRow) tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex, int columIndex) {
        tableRow = getRow(rowIndex);
        return (TextView) tableRow.getChildAt(columIndex - 1);
    }

    public void backgroundHeader(int color) {
        indexC = 0;
        while (indexC < header.length) {
            txtCell = getCell(0, indexC++);
            txtCell.setBackgroundColor(color);
        }

    }

    public void backgroundData(int firstColor, int secondColor) {
        for (indexR = 0; indexR <= header.length; indexR++) {
            multicolor = !multicolor;
            for (indexC = 0; indexC < 1; indexC++) {
                txtCell = getCell(indexR, indexC);
                txtCell.setBackgroundColor((multicolor) ? firstColor : secondColor);

            }

        }
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public void reColoring() {
        indexC = 0;
        multicolor = !multicolor;
        while (indexC < header.length) {
            txtCell = getCell(data.size(), indexC++);
            txtCell.setBackgroundColor((multicolor) ? firstColor : secondColor);
            txtCell.setTextColor(textColor);
        }

    }

    public void lineColor(int color) {
        indexR = 0;
        while (indexR < data.size())
            getRow(indexR++).setBackgroundColor(color);
    }

    public void textColorData(int color) {
        for (indexR = 0; indexR <= header.length; indexR++)
            for (indexC = 0; indexC < 1; indexC++)
                getCell(indexR, indexC).setTextColor(color);

        this.textColor = color;
    }

    public void textColorheader(int color) {
        indexC = 0;
        while (indexC < header.length) {
            getCell(0, indexC++).setTextColor(color);
        }
    }

}
