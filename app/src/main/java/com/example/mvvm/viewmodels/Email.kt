package com.example.mvvm.viewmodels

import android.R
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.transition.TransitionInflater.from
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.qrcode.encoder.QRCode
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.io.FileOutputStream
import java.text.Format
import java.util.*
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
import javax.inject.Inject
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.mvvm.models.CartMeal
import com.example.mvvm.models.Meal
import com.example.mvvm.models.User
import com.google.zxing.qrcode.QRCodeWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@HiltViewModel
class Email
@Inject constructor(
) : ViewModel(){

    //Send the email attached with the qr code as image and the pdf
    //Smtp.gmail is used as the host for send the email
    //Thread is used to gmail for send the email
    fun sendEmail(email: String) {
        try{
            //Properties for the host and the email direction
            val senderEmail:String = "Tecbankcr@gmail.com"
            val password:String = "awqvizmishnjofhk"
            val receiverEmail: String = email
            val host:String = "smtp.gmail.com"
            val properties = Properties()
            properties.put("mail.smtp.auth", "true")
            properties.put("mail.smtp.smtp.ssl.enable", "true")
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host)
            properties.put("mail.smtp.port", "587")

            //authenticate username and password
            val session: Session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(senderEmail, password)
                }
            })
            try {
                //start write the message
                val message: Message = MimeMessage(session)
                message.setFrom(InternetAddress(senderEmail))
                message.setRecipient(Message.RecipientType.TO, InternetAddress(receiverEmail))
                message.setSubject("Factura del Comedor TEC");
                message.setText("Hola");

                //multipart is used for upload files
                val multipart=MimeMultipart()
                val messageBodyPart = MimeBodyPart()
                val messageBodyPartQR = MimeBodyPart()

                val filename ="storage/emulated/0/documents"+"/Factura.pdf"
                val source: DataSource = FileDataSource(filename)

                val filenameQR ="storage/emulated/0/documents"+"/Qr.png"
                val sourceQR: DataSource = FileDataSource(filenameQR)

                //add the files to email
                messageBodyPart.dataHandler = DataHandler(source)
                messageBodyPart.fileName = filename
                messageBodyPartQR.dataHandler= DataHandler(sourceQR)
                messageBodyPartQR.fileName = filenameQR
                multipart.addBodyPart(messageBodyPartQR)

                multipart.addBodyPart(messageBodyPart)
                message.setContent(multipart);

                //thread for send the message
                val thread = Thread {
                    try {
                        Transport.send(message)
                    } catch (e: MessagingException) {
                        Log.d(TAG, "Problemas: ${e.toString()}")
                    }
                }
                thread.start()
                Log.d(ContentValues.TAG, "Listo: Bien")
            } catch (e: Exception) {
                Log.d(TAG, "Problema: ${e.toString()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, "Problema1: ${e.toString()}")
        }

    }

    //create the document for the products, amount of items and the final cost
    //it is add into a table
    //the document is attached into an email
    fun createPdf (meals: SnapshotStateList<CartMeal>,totalCost:Double){
        //url for the bill
        val file = File( "storage/emulated/0/documents","Factura.pdf")
        val fileOutputStream = FileOutputStream(file)
        val document = Document()
        PdfWriter.getInstance(document, fileOutputStream)

        //open the pdf and write
        document.open()
        val titulo = Paragraph(
            "Factura \n\n\n",
            FontFactory.getFont("arial", 22f, Font.BOLD, BaseColor.BLUE)
        )
        document.add(titulo)

        //create and add the table for the products
        var table = PdfPTable(3)
        table.addCell("Producto")
        table.addCell("Cantidad")
        table.addCell("Costo")

        for (item in meals){
            table.addCell(item.meal?. name)
            table.addCell(item.count.toString())
            table.addCell(item.meal?.cost.toString())
        }
        table.addCell("   ")
        table.addCell("Total: ")
        table.addCell(totalCost.toString())
        document.add(table)

        document.close()
    }

    //create the QR based on User information
    //the qr is converted into a image
    //the image is added into a email
    @RequiresApi(Build.VERSION_CODES.O)
    fun createQR(student: User){
        val writer = QRCodeWriter()
        val dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
        val text="Orden de Compra:  ?\n" + "Carnet: "+student.dsi+"\n"+ "Fecha: "+ dateTime.toString()

        // bits for create the QR code
        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }

        //url to storage the QR as image
        val file = File( "storage/emulated/0/documents","QR.png")
        val fileOutputStream = FileOutputStream(file)
        try{
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream)
        }
        catch (e: Exception){
            Log.d(TAG, "Problema5: ${e.toString()}")
        }
    }

    //Send an email to the registered user
    //Showing a welcome message and a summary of the data provided
    //It is only text in the message
    fun welcomeEmail(user: User){
        try{
            //Properties for the host and the email direction
            val senderEmail:String = "Tecbankcr@gmail.com"
            val password:String = "awqvizmishnjofhk"
            val receiverEmail: String = user.studentEmail
            val host: String = "smtp.gmail.com"
            val properties = Properties()
            properties.put("mail.smtp.auth", "true")
            properties.put("mail.smtp.smtp.ssl.enable", "true")
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host)
            properties.put("mail.smtp.port", "587")

            //authenticate username and password
            val session: Session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(senderEmail, password)
                }
            })
            try {
                //start write the message for the email
                val message: Message = MimeMessage(session)
                message.setFrom(InternetAddress(senderEmail))
                message.setRecipient(Message.RecipientType.TO, InternetAddress(receiverEmail))
                message.setSubject("Bienvenido al Sistema del Comedor TEC");
                message.setText("Ahora puede aprovechar los diversos y ricos sabores que ofrece la institución\n\n"
                + "Los datos suministrados son: \n"+ "Nombre: " +user.fullName+" "+user.maidenName+" "+user.lastName+"\n"+
                "Número de carné: "+ user.dsi+"\n"+ "Número de cédula: " +user.dni+ "\n"+"Edad: "+user.age)


                //thread for send the message
                val thread = Thread {
                    try {
                        Transport.send(message)
                    } catch (e: MessagingException) {
                        Log.d(TAG, "Problemas: ${e.toString()}")
                    }
                }
                thread.start()
                Log.d(ContentValues.TAG, "Listo: Bien")
            } catch (e: Exception) {
                Log.d(TAG, "Problema: ${e.toString()}")
            }
        } catch (e: Exception) {
            Log.d(TAG, "Problema1: ${e.toString()}")
        }
    }
}



