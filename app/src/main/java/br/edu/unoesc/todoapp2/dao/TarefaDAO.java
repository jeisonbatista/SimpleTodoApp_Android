package br.edu.unoesc.todoapp2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.todoapp2.entidades.Tarefa;

public class TarefaDAO extends SQLiteOpenHelper {

    public TarefaDAO(Context context){
        super(context, "todoApp_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Tarefa(ID INTEGER PRIMARY KEY, TITULO TEXT NOT NULL, DESCRIACAO TEXT, URGENTE INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Tarefa";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserirTarefa(Tarefa tarefa){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("TITULO", tarefa.getTitulo());
        valores.put("DESCRIACAO", tarefa.getDescricao());
        valores.put("URGENTE", tarefa.isUrgente());

        db.insert("Tarefa", null, valores);
    }

    public List<Tarefa> retonarTarefas(){
        String sql = "select * from Tarefa";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        while (c.moveToNext()){
            Tarefa tarefa = new Tarefa();
            tarefa.setId(c.getLong(c.getColumnIndex("ID")));
            tarefa.setTitulo(c.getString(c.getColumnIndex("TITULO")));
            tarefa.setDescricao(c.getString(c.getColumnIndex("DESCRIACAO")));
            tarefa.setUrgente(Boolean.parseBoolean(c.getString(c.getColumnIndex("URGENTE"))));
            tarefas.add(tarefa);
        }
        c.close();
        return tarefas;
    }

    public void deletarTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();

        String[] parametros = {tarefa.getId().toString()};
        db.delete("Tarefa", "id = ?",parametros);
    }

    public void editarTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("TITULO", tarefa.getTitulo());
        valores.put("DESCRIACAO", tarefa.getDescricao());
        valores.put("URGENTE", tarefa.isUrgente());
        String[] parametros = {tarefa.getId().toString()};

        db.update("Tarefa", valores, "id = ?", parametros);
    }

}
