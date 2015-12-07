package com.ufscar.alunos.mqc.Menu;

/**
 * Created by Vinicius on 05/12/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ufscar.alunos.mqc.R;

public class MyAdapterTrabalhos extends BaseAdapter{

    private String[] listTrabalho;
    private String[] listDataTrabalho;
    private Context context;

    public MyAdapterTrabalhos(Context context, String[] listTrabalho, String[] listDataTrabalho){
        this.context = context;
        this.listTrabalho = listTrabalho;
        this.listDataTrabalho = listDataTrabalho;
    }

    @Override
    public int getCount() {
        return listTrabalho.length;
    }

    @Override
    public Object getItem(int i) {
        return listTrabalho[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //uso qualquer servico do sistema, por exemplo bateria, tipo de conexao atual
        View root = inflater.inflate(R.layout.row_provas,null);

        TextView nameTrabalho = (TextView) root.findViewById(R.id.nomeProva);
        nameTrabalho.setText(listTrabalho[position]);

        TextView dataTrabalho = (TextView) root.findViewById(R.id.data_prova);
        dataTrabalho.setText(listDataTrabalho[position]);

//        //premite a cada linha da lista ser clicavel
//        root.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ParseQuery<ParseObject> query = ParseQuery.getQuery("Course");
//                query.whereEqualTo("name", list[position]);
//                query.whereEqualTo("owner", ParseUser.getCurrentUser());
//
//                query.findInBackground(new FindCallback<ParseObject>() {
//                    public void done(List<ParseObject> cList, ParseException e) {
//                        if (e == null) {
//                            Intent intent = new Intent(context, InicialCursos.class);
//                            intent.putExtra("course", cList.get(0).getString("name"));
//                            intent.putExtra("id", cList.get(0).getString("objectId"));
//
//                            context.startActivity(intent);
//                        } else {
//                            Log.d("score", "Error: " + e.getMessage());
//                        }
//                    }
//                });
//            }
//
//
//        });
        return root;
    }

    private ViewClickListener mViewClickListener;

    public interface ViewClickListener {
        void onImageClicked(int position);
    }

    public void setViewClickListener (ViewClickListener viewClickListener) {
        mViewClickListener = viewClickListener;
    }
}



