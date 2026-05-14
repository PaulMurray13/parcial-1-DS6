package com.example.demo_list_v2

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class PlaylistFragment : Fragment() {

    private lateinit var adapter: SongAdapter
    private var ascendente = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_playlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore the default cover image every time PlaylistFragment is shown
        view.findViewById<ImageView>(R.id.imageViewPortada)
            .setImageResource(R.drawable.portada)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewSongs)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = SongAdapter(Canciones.playlist) { cancion ->
            // Navigate to detail fragment
            val fragment = CancionDetalleFragment.newInstance(cancion)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter

        // Sort button (A-Z / Z-A toggle)
        view.findViewById<MaterialButton>(R.id.btnOrdenar).setOnClickListener {
            if (ascendente) {
                Canciones.playlist.sortBy { it.titulo }
            } else {
                Canciones.playlist.sortByDescending { it.titulo }
            }
            ascendente = !ascendente
            adapter.notifyDataSetChanged()

            val label = view.findViewById<MaterialButton>(R.id.btnOrdenar)
            label.text = if (ascendente) "Ordenar A–Z" else "Ordenar Z–A"
        }
    }
}

// ──────────────────────────────────────────────
// Adapter
// ──────────────────────────────────────────────

class SongAdapter(
    private val canciones: MutableList<Cancion>,
    private val onClick: (Cancion) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    inner class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo:  TextView  = view.findViewById(R.id.tvTituloItem)
        val tvArtista: TextView  = view.findViewById(R.id.tvArtistaItem)
        val ivPortada: ImageView = view.findViewById(R.id.ivPortadaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cancion, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val cancion = canciones[position]
        holder.tvTitulo.text  = cancion.titulo
        holder.tvArtista.text = cancion.artista
        holder.ivPortada.setImageResource(cancion.imagenRes)
        holder.itemView.setOnClickListener { onClick(cancion) }
    }

    override fun getItemCount() = canciones.size
}
