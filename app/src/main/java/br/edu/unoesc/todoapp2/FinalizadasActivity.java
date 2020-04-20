package br.edu.unoesc.todoapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.unoesc.todoapp2.dao.TarefaDAO;
import br.edu.unoesc.todoapp2.entidades.Tarefa;

public class FinalizadasActivity extends AppCompatActivity {

    private ListView lstFinalizadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizadas);

        lstFinalizadas = findViewById(R.id.lstFinalizadas);

        registerForContextMenu(lstFinalizadas);
        this.listarTarefasFinalizadas();
    }

    public void listarTarefasFinalizadas(){
        TarefaDAO dao = new TarefaDAO(this);
        List<Tarefa> tarefas = dao.retonarTarefasFinalizadas();
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        lstFinalizadas.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletarTarefa = menu.add("Excluir");
        deletarTarefa.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo informacao = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Tarefa tarefa = (Tarefa) lstFinalizadas.getItemAtPosition(informacao.position);

                TarefaDAO dao = new TarefaDAO(FinalizadasActivity.this);
                dao.deletarTarefa(tarefa);
                listarTarefasFinalizadas();

                Toast.makeText(FinalizadasActivity.this,
                        "Excluído com sucesso", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
