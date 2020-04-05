package br.edu.unoesc.todoapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.unoesc.todoapp2.dao.TarefaDAO;
import br.edu.unoesc.todoapp2.entidades.Tarefa;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView lstTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstTarefas = findViewById(R.id.lstTarefas);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(MainActivity.this, NovaTarefaActivity.class);
                startActivity(abreCadastro);
            }
        });

        lstTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) lstTarefas.getItemAtPosition(position);
                Intent abreEdicao = new Intent( MainActivity.this, NovaTarefaActivity.class);
                abreEdicao.putExtra("tarefa", tarefa);
                startActivity(abreEdicao);
            }
        });

        registerForContextMenu(lstTarefas);
        this.listarTarefas();
    }

    public void listarTarefas(){
        TarefaDAO dao = new TarefaDAO(this);
        List<Tarefa> tarefas = dao.retonarTarefas();
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, tarefas);
        lstTarefas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.listarTarefas();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletarTarefa = menu.add("Excluir");
        deletarTarefa.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo informacao = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Tarefa tarefa = (Tarefa) lstTarefas.getItemAtPosition(informacao.position);

                TarefaDAO dao = new TarefaDAO(MainActivity.this);
                dao.deletarTarefa(tarefa);
                listarTarefas();

                Toast.makeText(MainActivity.this,
                        "Excluído com sucesso", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
