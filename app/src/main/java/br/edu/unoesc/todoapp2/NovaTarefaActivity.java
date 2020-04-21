package br.edu.unoesc.todoapp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.edu.unoesc.todoapp2.dao.TarefaDAO;
import br.edu.unoesc.todoapp2.entidades.Tarefa;
import br.edu.unoesc.todoapp2.util.AuxiliarForm;

public class NovaTarefaActivity extends AppCompatActivity {

    private Button btnSalvar;
    private AuxiliarForm auxiliar;
    private Button btnCamera;
    private String caminhoSalvarDocumento;
    private static int codigo = 100;

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
        btnCamera = (Button)findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoSalvarDocumento = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File foto = new File(caminhoSalvarDocumento);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                        NovaTarefaActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        foto
                ));
                startActivityForResult(camera,codigo);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 100) {
                ImageView fotoDocumento = findViewById(R.id.imageView);
                Bitmap fotoBmp = BitmapFactory.decodeFile(caminhoSalvarDocumento);
                Bitmap fotoAjustada = Bitmap.createScaledBitmap(fotoBmp, 400, 400, true);
                fotoDocumento.setImageBitmap(fotoAjustada);
                fotoDocumento.setTag(caminhoSalvarDocumento);
            }
        }
    }
}
