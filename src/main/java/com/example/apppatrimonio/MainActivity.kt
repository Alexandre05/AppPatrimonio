package com.example.apppatrimonio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var edtdescricao: EditText
    lateinit var edtlocalizacao: EditText
    lateinit var edtcadastra: Button
    lateinit var edtplaca: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtdescricao = findViewById(R.id.edtdescricao)
        edtlocalizacao = findViewById(R.id.edtlocalizacao)
        edtcadastra = findViewById(R.id.edtcadastra)
        edtplaca = findViewById(R.id.edtplaca)

        edtcadastra.setOnClickListener {

            cadastraPatrimonio()


        }
    }

    private fun cadastraPatrimonio() {
        val descricao = edtdescricao.text.toString().trim()

       val localizacao = edtlocalizacao.text.toString().trim()
        val placa=edtplaca.text.toString().trim()


        if (descricao.isEmpty() || localizacao.isEmpty()) {
            edtdescricao.error = "Insira uma Descrição"
            edtlocalizacao.error = "Insira Localização"
            return


        }
        val ref = FirebaseDatabase.getInstance().getReference("Vistorias")
        var vistoId = ref.push().push().key
        val vistoria = Vistoria(vistoId.toString(), descricao, localizacao,placa)
        ref.child(vistoId.toString()).setValue(vistoria).addOnCompleteListener {
            Toast.makeText(applicationContext, "Vistoria salva", Toast.LENGTH_SHORT).show()

        }
        edtdescricao.setText("")
        edtlocalizacao.setText("")
        edtplaca.setText("")
        //TODO("Not yet implemented")

    }
}