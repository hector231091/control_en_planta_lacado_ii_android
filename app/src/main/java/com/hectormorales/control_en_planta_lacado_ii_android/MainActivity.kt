package com.hectormorales.control_en_planta_lacado_ii_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Quitar la ActionBar de la aplicación.
        supportActionBar?.hide()

        // Para mantener la pantalla siempre en modo LANDSCAPE.
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        // Poner por defecto el día/mes/año actual
        //number_of_day.text = today

        register_day_and_number_button.setOnClickListener{

            // Crear un aviso cuando falte algo por rellenar y otro cuando se haya hecho bien.
            if(check_personal_number_and_date())
            {
                // Si falta algún dato poner un mensaje con que algo falta.
                register_data_label.text = personal_number.text.toString() + "-" +
                        number_of_day.text.toString() +
                        "/" +
                        name_of_month.getSelectedItem().toString() +
                        "/" +
                        number_of_year.text.toString()
            }
        }

        check_hours_button.setOnClickListener {

            // Variable 1
            if (input_number_of_squares_entry_1.text.toString().isEmpty())
            {
                number_of_squares_in_hours_1.text = "0"
                input_number_of_squares_entry_1.setText("0")
            }
            else
            {
                number_of_squares_in_hours_1.text = (input_number_of_squares_entry_1.text.toString().toFloat() * 5 / 60).toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }

            // Variable 2
            if (input_number_of_squares_entry_2.text.toString().isEmpty())
            {
                number_of_squares_in_hours_2.text = "0"
                input_number_of_squares_entry_2.setText("0")
            }
            else
            {
                number_of_squares_in_hours_2.text = (input_number_of_squares_entry_2.text.toString().toFloat() * 5 / 60).toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }

            // Variable 3
            if (input_number_of_squares_entry_3.text.toString().isEmpty())
            {
                number_of_squares_in_hours_3.text = "0"
                input_number_of_squares_entry_3.setText("0")
            }
            else
            {
                number_of_squares_in_hours_3.text = (input_number_of_squares_entry_3.text.toString().toFloat() * 5 / 60).toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }

            // Variable 4
            if (input_number_of_squares_entry_4.text.toString().isEmpty())
            {
                number_of_squares_in_hours_4.text = "0"
                input_number_of_squares_entry_4.setText("0")
            }
            else
            {
                number_of_squares_in_hours_4.text = (input_number_of_squares_entry_4.text.toString().toFloat() * 5 / 60).toBigDecimal().setScale(2, RoundingMode.UP).toString()
            }

            total_of_hours.text = (input_number_of_squares_entry_1.text.toString().toFloat() * 5 / 60 +
                    input_number_of_squares_entry_2.text.toString().toFloat() * 5 / 60 +
                    input_number_of_squares_entry_3.text.toString().toFloat() * 5 / 60 +
                    input_number_of_squares_entry_4.text.toString().toFloat() * 5 / 60).toBigDecimal().setScale(2, RoundingMode.UP).toString()
        }

        register_input_data_button.setOnClickListener {
            // Poner una función para que compruebe si hay un día regitrado.
            write_data_in_storage()

            // Crear un aviso cuando falte algo por rellenar y otro cuando se haya hecho todo bien.

            // Función para borrar todas las celdas.
            delete_all_edit_and_text_view()
        }

        // Crear un botón para eliminar el archivo del móvil.

    }

    private fun check_personal_number_and_date():Boolean
    {
        // Variable de ayuda.
        var variable = ""

        // Comprobamos que todos los campos están rellenos.
        if (check_personal_number() && check_day() && check_year()) {
            variable = ""
        }
        else {
            //errores.text = "No furula"
            if (check_personal_number() == false) {
                //Snackbar
                //val message = "El color no es correcto."
                //Snackbar.make(findViewById(R.id.colour_entry), message, Snackbar.LENGTH_LONG).show()

                // Hacemos un "AlertDialog".
                val message = "Falta introducir el número del operario."
                dialog_alert(message)

            } else if (check_day() == false) {
                // Hacemos un "AlertDialog".
                val message = "Falta introducir el día."
                dialog_alert(message)

            } else if (check_year() == false) {
                // Hacemos un "AlertDialog".
                val message = "Falta introducir el año."
                dialog_alert(message)
            }
            variable = "1"
        }
        return variable.isEmpty()
    }

    private fun check_personal_number():Boolean {
        return personal_number.text.toString().isNotEmpty()
    }

    private fun check_day():Boolean {
        return number_of_day.text.toString().isNotEmpty()
    }

    private fun check_year():Boolean {
        return number_of_year.text.toString().isNotEmpty()
    }

    private fun delete_all_edit_and_text_view()
    {
        personal_number.setText("")
        number_of_day.setText("")
        number_of_year.setText("")

        register_data_label.setText("")

        input_number_of_incidence_entry_1.setText("")
        input_number_of_incidence_entry_2.setText("")
        input_number_of_incidence_entry_3.setText("")
        input_number_of_incidence_entry_4.setText("")

        number_of_squares_in_hours_1.setText("")
        number_of_squares_in_hours_2.setText("")
        number_of_squares_in_hours_3.setText("")
        number_of_squares_in_hours_4.setText("")

        input_number_of_squares_entry_1.setText("")
        input_number_of_squares_entry_2.setText("")
        input_number_of_squares_entry_3.setText("")
        input_number_of_squares_entry_4.setText("")

        total_of_hours.setText("")
    }

    private fun write_data_in_storage()
    {
        val input_data_line_1 = personal_number.text.toString() + ";" +
                number_of_day.text.toString() + "/" +
                name_of_month.getSelectedItem().toString() + "/" +
                number_of_year.text.toString() + ";" +
                register_data_label.getText().toString() + ";" +
                input_number_of_incidence_entry_1.getText().toString() + ";" +
                input_number_of_squares_entry_1.getText().toString() + ";" +
                number_of_squares_in_hours_1.getText().toString() + "\n"

        val input_data_line_2 = personal_number.text.toString() + ";" +
                number_of_day.text.toString() + "/" +
                name_of_month.getSelectedItem().toString() + "/" +
                number_of_year.text.toString() + ";" +
                register_data_label.getText().toString() + ";" +
                input_number_of_incidence_entry_2.getText().toString() + ";" +
                input_number_of_squares_entry_2.getText().toString() + ";" +
                number_of_squares_in_hours_2.getText().toString() + "\n"

        val input_data_line_3 = personal_number.text.toString() + ";" +
                number_of_day.text.toString() + "/" +
                name_of_month.getSelectedItem().toString() + "/" +
                number_of_year.text.toString() + ";" +
                register_data_label.getText().toString() + ";" +
                input_number_of_incidence_entry_3.getText().toString() + ";" +
                input_number_of_squares_entry_3.getText().toString() + ";" +
                number_of_squares_in_hours_3.getText().toString() + "\n"

        val input_data_line_4 = personal_number.text.toString() + ";" +
                number_of_day.text.toString() + "/" +
                name_of_month.getSelectedItem().toString() + "/" +
                number_of_year.text.toString() + ";" +
                register_data_label.getText().toString() + ";" +
                input_number_of_incidence_entry_4.getText().toString() + ";" +
                input_number_of_squares_entry_4.getText().toString() + ";" +
                number_of_squares_in_hours_4.getText().toString() + "\n"

        // Dirección en la que lo vamos a guardar
        val file_direccion = getExternalFilesDir(null)

        // Creamos una carpeta dentro de la dirección anterior.
        val folder = File(file_direccion, "Datos Lacado")
        //Log.i("Dirección: ", folder.toString())
        // Comprobamos que existe la carpeta. En caso contrario la creamos.
        if(!folder.exists()){
            folder.mkdir()
        }

        val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()) + ".csv"

        // Creamos el fichero y le pasamos los datos que debe almacenar.
        val ficheroFisico = File(folder, "control_en_planta.csv")
        ficheroFisico.appendText(input_data_line_1)
        ficheroFisico.appendText(input_data_line_2)
        ficheroFisico.appendText(input_data_line_3)
        ficheroFisico.appendText(input_data_line_4)
    }

    private fun dialog_alert(message: String){
        val builder = AlertDialog.Builder(this@MainActivity)
        // Título.
        builder.setTitle("ERROR DE REGISTRO")

        // Mensaje a mostrar.
        builder.setMessage(message)

        // Mostrar un botón neutral para cerrar la alerta.
        builder.setNeutralButton("ENTENDIDO"){ _, _ ->
        }

        // Creamos la alerta
        val dialog: AlertDialog = builder.create()

        // Mostramos la alerta.
        dialog.show()
    }
}