package br.edu.unoesc.todoapp2.util;

import android.widget.CheckBox;
import android.widget.EditText;

import br.edu.unoesc.todoapp2.NovaTarefaActivity;
import br.edu.unoesc.todoapp2.R;
import br.edu.unoesc.todoapp2.entidades.Tarefa;

public class AuxiliarForm extends Tarefa {

    private Tarefa tarefa;
    private EditText edtTitulo;
    private EditText edtDescricao;
    private CheckBox ckbUrgente;

    public AuxiliarForm(NovaTarefaActivity activity) {
        edtTitulo = activity.findViewById(R.id.edtTitulo);
        edtDescricao = activity.findViewById(R.id.edtDescricicao);
        ckbUrgente = activity.findViewById(R.id.ckbUrgente);
        tarefa = new Tarefa();
    }

    public Tarefa retornarTarefa() {
        tarefa.setTitulo(edtTitulo.getText().toString());
        tarefa.setDescricao(edtDescricao.getText().toString());
        tarefa.setUrgente(ckbUrgente.isChecked());
        return tarefa;
    }

    public void popularTarefa(Tarefa tarefa) {
        edtTitulo.setText(tarefa.getTitulo());
        edtDescricao.setText(tarefa.getDescricao());
        ckbUrgente.setChecked(tarefa.isUrgente());
        this.tarefa = tarefa;
    }
}
