package com.example.pratica3_maps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public final class BancoDadosSingleton {
    protected SQLiteDatabase db;
    private final String NOME_BANCO = "pratica3";
    private static BancoDadosSingleton INSTANCE;

    private final String[] SCRIP_DATABASE_CREATE = new String[] {
            "CREATE TABLE Logs (id INTEGER PRIMARY KEY AUTOINCREMENT,"+" msg TEXT, timestamp TEXT, id_location INTEGER"+ " FOREIGN KEY (id_location) REFERENCES Location (id));",
            "CREATE TABLE Location (id INTEGER PRIMARY KEY AUTOINCREMENT,"+" descricao TEXT, latitude REAL, longitude REAL);",
            "INSERT INTO Location (id, descricao, latitude, longitude) VALUES ('1', 'Minha casa em Itaocara', '-21.674786599501207', '-42.087803755987444' );",
            "INSERT INTO Location (id, descricao, latitude, longitude) VALUES ('2', 'Minha casa em Viçosa', '-20.756855651371684', '-42.8751327472389' );",
            "INSERT INTO Location (id, descricao, latitude, longitude) VALUES ('3', 'DPI/UFV', '-20.764807038105072', '-42.86837497388309');"
    };

    private BancoDadosSingleton() {
        //Obtem contexto da aplicação usando a classe criada para essa finalidade
        Context ctx = MyApp.getAppContext();

        // Abre o banco de dados já existente ou então cria um banco novo
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);

        //busca por tabelas existentes no banco igual "show tables" do MySQL
        //SELECT * FROM sqlite_master WHERE type = "table"
        Cursor c = buscar("sqlite_master", null, "type = 'table'", "");

        //Cria tabelas do banco de dados caso o mesmo estiver vazio.
        if(c.getCount() == 1) {
            for (String s : SCRIP_DATABASE_CREATE) {
                db.execSQL(s);
            }
            Log.i("BANCO_DADOS", "Criou as tabelas do banco e as populou.");
        }
        c.close();
        Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
    }

    // Insere um novo registro
    public long inserir(String tabela, ContentValues valores) {
        long id = db.insert(tabela, null, valores);
        Log.i("BANCO_DADOS", "Cadastrou registro com o id [" + id + "]");
        return id;
    }

    // Atualiza registros
    public int atualizar(String tabela, ContentValues valores, String where) {
        int count = db.update(tabela, valores, where, null);
        Log.i("BANCO_DADOS", "Atualizou [" + count + "] registros");
        return count;
    }

    // Deleta registros
    public int deletar(String tabela, String where) {
        int count = db.delete(tabela, where, null);
        Log.i("BANCO_DADOS", "Deletou [" + count + "] registros");
        return count;
    }

    // Busca registros
    public Cursor buscar(String tabela, String[] colunas, String where, String orderBy) {
        Cursor c;
        if(!where.equals(""))
            c = db.query(tabela, colunas, where, null, null, null, orderBy);
        else
            c = db.query(tabela, colunas, null, null, null, null, orderBy);
        Log.i("BANCO_DADOS", "Realizou uma busca e retornou [" + c.getCount() + "] registros.");
        return c;
    }

    // Abre conexão com o banco
    private void abrir() {
        Context ctx = MyApp.getAppContext();
        if(!db.isOpen()) {
            db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
            Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
        } else {
            Log.i("BANCO_DADOS", "Conexão com o banco já estava aberta.");
        }
    }

    //Retorna a única instância existente dessa classe para qualquer parte do projeto
    public  static BancoDadosSingleton getInstance() {
        if(INSTANCE == null)
            INSTANCE = new BancoDadosSingleton();
        INSTANCE.abrir();
        return INSTANCE;
    }

    // Fecha conexão com o banco
    public void fechar() {
        if(db != null && db.isOpen()) {
            db.close();
            Log.i("BANCO_DADOS", "Fechou conexão com o banco.");
        }
    }
}
