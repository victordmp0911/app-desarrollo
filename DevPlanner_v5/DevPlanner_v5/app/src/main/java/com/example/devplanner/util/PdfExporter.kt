package com.example.devplanner.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.example.devplanner.data.local.entity.TaskEntity
import java.io.File
import java.io.FileOutputStream

object PdfExporter {
    /**
     * Exporta un resumen simple de tareas a un PDF.
     */
    fun exportTasksToPdf(
        context: Context,
        tasks: List<TaskEntity>,
        file: File,
        title: String = "Reporte de Tareas"
    ): Boolean {
        return try {
            val doc = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 aprox en px
            val page = doc.startPage(pageInfo)
            val canvas: Canvas = page.canvas
            val paint = Paint().apply { textSize = 12f }

            var y = 40f
            paint.isFakeBoldText = true
            canvas.drawText(title, 40f, y, paint)
            paint.isFakeBoldText = false
            y += 20f

            val header = "ID | Title | Status | Eh | Sh"
            canvas.drawText(header, 40f, y, paint)
            y += 15f

            tasks.forEach { t ->
                val line = "${t.id} | ${t.title} | ${t.status} | ${t.estimateHours} | ${t.spentHours}"
                if (y > 800f) {
                    doc.finishPage(page)
                    // Nueva página si se excede
                }
                canvas.drawText(line, 40f, y, paint)
                y += 15f
            }

            doc.finishPage(page)
            FileOutputStream(file).use { fos ->
                doc.writeTo(fos)
            }
            doc.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}