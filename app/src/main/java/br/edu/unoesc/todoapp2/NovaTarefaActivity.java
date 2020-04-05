package br.edu.unoesc.todoapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.unoesc.todoapp2.dao.TarefaDAO;
import br.edu.unoesc.todoapp2.entidades.Tarefa;
import br.edu.unoesc.todoapp2.util.AuxiliarForm;

public class NovaTarefaActivity extends AppCompatActivity {

    private Button btnSalvar;
    private AuxiliarForm auxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        auxiliar = new AuxiliarForm(this);
        btnSalvar = findViewById(R.id.btnSalvar);

        Intent intent = getIntent();
        Tarefa tarefa = (Tarefa) intent.getSerializableExtra("tarefa");

        if(tarefa != null){
            auxiliar.popularTarefa(tarefa);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tarefa tarefa = auxiliar.retornarTarefa();
                TarefaDAO dao = new TarefaDAO(NovaTarefaActivity.this);
                if(tarefa.getId() != null) {
                    dao.editarTarefa(tarefa);
                    Toast.makeText(NovaTarefaActivity.this,
                            "Editado com sucesso", Toast.LENGTH_LONG).show();
                }else {
                    dao.inserirTarefa(tarefa);
                    Toast.makeText(NovaTarefaActivity.this,
                            "Cadastrado" + tarefa.getTitulo(), Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}
