package br.edu.unoesc.todoapp2.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import br.edu.unoesc.todoapp2.NovaTarefaActivity;
import br.edu.unoesc.todoapp2.R;
import br.edu.unoesc.todoapp2.entidades.Tarefa;

public class AuxiliarForm extends Tarefa {

    private Tarefa tarefa;
    private EditText edtTitulo;
    private EditText edtDescricao;
    private CheckBox ckbUrgente;
    private ImageView documento;

    public AuxiliarForm(NovaTarefaActivity activity) {
        edtTitulo = activity.findViewById(R.id.edtTitulo);
        edtDescricao = activity.findViewById(R.id.edtDescricicao);
        ckbUrgente = activity.findViewById(R.id.ckbUrgente);
        documento = activity.findViewById(R.id.imageView);
        tarefa = new Tarefa();
    }

    public Tarefa retornarTarefa() {
        tarefa.setTitulo(edtTitulo.getText().toString());
        tarefa.setDescricao(edtDescricao.getText().toString());
        tarefa.setUrgente(ckbUrgente.isChecked());
        tarefa.setDocumento(documento.getTag().toString());
        return tarefa;
    }

    public void popularTarefa(Tarefa tarefa) {
        edtTitulo.setText(tarefa.getTitulo());
        edtDescricao.setText(tarefa.getDescricao());
        ckbUrgente.setChecked(tarefa.isUrgente());
        if(tarefa.getDocumento() != null){
            Bitmap fotoBmp = BitmapFactory.decodeFile(tarefa.getDocumento());
            Bitmap fotoAjustada = Bitmap.createScaledBitmap(fotoBmp, 400,400,true);
            documento.setImageBitmap(fotoAjustada);
            documento.setTag(tarefa.getDocumento());
        }
        this.tarefa = tarefa;
    }
}
