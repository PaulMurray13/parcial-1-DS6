package com.example.demo_list_v2

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.android.material.button.MaterialButton

class CancionDetalleFragment : Fragment() {

    private var player: ExoPlayer? = null
    private var playerView: PlayerView? = null

    companion object {
        private const val ARG_TITULO = "titulo"
        private const val ARG_ARTISTA = "artista"
        private const val ARG_ALBUM = "album"
        private const val ARG_GENERO = "genero"
        private const val ARG_DURACION = "duracion"
        private const val ARG_FECHA = "fecha"
        private const val ARG_IMAGEN = "imagen"
        private const val ARG_AUDIO = "audio"

        fun newInstance(cancion: Cancion): CancionDetalleFragment {
            return CancionDetalleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITULO, cancion.titulo)
                    putString(ARG_ARTISTA, cancion.artista)
                    putString(ARG_ALBUM, cancion.album)
                    putString(ARG_GENERO, cancion.genero)
                    putString(ARG_DURACION, cancion.duracion)
                    putString(ARG_FECHA, cancion.fechaLanzamiento)
                    putInt(ARG_IMAGEN, cancion.imagenRes)
                    putInt(ARG_AUDIO, cancion.audioRes)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cancion_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()

        view.findViewById<ImageView>(R.id.ivPortadaDetalle)
            .setImageResource(args.getInt(ARG_IMAGEN))

        view.findViewById<TextView>(R.id.tvDetalleTitulo).text =
            args.getString(ARG_TITULO)

        view.findViewById<TextView>(R.id.tvDetalleArtista).text =
            "Artista: ${args.getString(ARG_ARTISTA)}"

        view.findViewById<TextView>(R.id.tvDetalleAlbum).text =
            "Álbum: ${args.getString(ARG_ALBUM)}"

        view.findViewById<TextView>(R.id.tvDetalleGenero).text =
            "Género: ${args.getString(ARG_GENERO)}"

        view.findViewById<TextView>(R.id.tvDetalleDuracion).text =
            "Duración: ${args.getString(ARG_DURACION)}"

        view.findViewById<TextView>(R.id.tvDetalleFecha).text =
            "Lanzamiento: ${args.getString(ARG_FECHA)}"

        playerView = view.findViewById(R.id.playerView)

        inicializarReproductor(args.getInt(ARG_AUDIO))

        view.findViewById<MaterialButton>(R.id.btnPlay).setOnClickListener {
            player?.play()
        }

        view.findViewById<MaterialButton>(R.id.btnPause).setOnClickListener {
            player?.pause()
        }

        view.findViewById<MaterialButton>(R.id.btnSeekRetroceder).setOnClickListener {
            player?.let { exoPlayer ->
                val nuevaPosicion = (exoPlayer.currentPosition - 10000L).coerceAtLeast(0L)
                exoPlayer.seekTo(nuevaPosicion)
            }
        }

        view.findViewById<MaterialButton>(R.id.btnSeekAdelantar).setOnClickListener {
            player?.let { exoPlayer ->
                val duracionTotal = exoPlayer.duration
                val nuevaPosicion = exoPlayer.currentPosition + 10000L

                if (duracionTotal > 0) {
                    exoPlayer.seekTo(nuevaPosicion.coerceAtMost(duracionTotal))
                } else {
                    exoPlayer.seekTo(nuevaPosicion)
                }
            }
        }

        view.findViewById<MaterialButton>(R.id.btnRegresar).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun inicializarReproductor(audioRes: Int) {
        val context = requireContext()

        player = ExoPlayer.Builder(context).build().also { exoPlayer ->

            val audioUri = Uri.parse("android.resource://${context.packageName}/$audioRes")
            val mediaItem = MediaItem.fromUri(audioUri)

            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = false

            playerView?.player = exoPlayer
        }
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    private fun liberarReproductor() {
        playerView?.player = null
        player?.release()
        player = null
    }

    override fun onDestroyView() {
        liberarReproductor()
        playerView = null
        super.onDestroyView()
    }
}