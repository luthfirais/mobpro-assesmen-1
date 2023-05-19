package org.d3if0132.kalkulatorpersegipanjang.ui.histori

import android.graphics.drawable.GradientDrawable
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0132.kalkulatorpersegipanjang.R
import org.d3if0132.kalkulatorpersegipanjang.databinding.ItemHistoriBinding
import org.d3if0132.kalkulatorpersegipanjang.db.PpEntity
import org.d3if0132.kalkulatorpersegipanjang.model.KategoriPp
import org.d3if0132.kalkulatorpersegipanjang.model.hitungPp
import java.util.*

class HistoryAdapter :
    ListAdapter<PpEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PpEntity>() {
                override fun areItemsTheSame(
                    oldData: PpEntity, newData: PpEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: PpEntity, newData: PpEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.N)
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(item: PpEntity) = with(binding) {
            val hasilPp = item.hitungPp()
            kategoriTextView.text = hasilPp.kategori.toString().substring(0, 1)
            val colorRes = when(hasilPp.kategori) {
                KategoriPp.KECIL -> R.color.kecil
                KategoriPp.SEDANG -> R.color.sedang
                else -> R.color.besar
            }
            20
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            ppTextView.text = root.context.getString(R.string.hasil_x,
                hasilPp.pp, hasilPp.kategori.toString())
            val tipe = root.context.getString(
                if (item.isLuas) R.string.luas else R.string.keliling)
            dataTextView.text = root.context.getString(R.string.data_x,
                item.panjang, item.lebar, tipe)
        }

    }
}