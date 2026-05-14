# Lab 1 – Playlist con RecyclerView y Fragments
## Desarrollo de Software VI – Grupo 1GS132

---

## Estructura del proyecto

Este proyecto extiende el ejemplo base **Demo_List_v2** para cumplir todos los requisitos del Laboratorio 1.

```text
app/src/main/
├── java/com/example/demo_list_v2/
│   ├── Cancion.kt
│   ├── Canciones.kt
│   ├── MainActivity.kt
│   ├── PlaylistFragment.kt
│   └── CancionDetalleFragment.kt
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── fragment_playlist.xml
    │   ├── item_cancion.xml
    │   └── fragment_cancion_detalle.xml
    └── drawable/
        ├── portada.jpg
        ├── img_my_heart.jpg
        ├── img_fade.jpg
        ├── img_sky_high.jpg
        ├── img_hope.jpg
        ├── img_invincible.jpg
        ├── img_blank.jpg
        ├── img_on_and_on.jpg
        ├── img_heroes_tonight.jpg
        ├── img_firefly.jpg
        ├── img_feel_good.jpg
        ├── img_cloud9.jpg
        ├── img_limitless.jpg
        ├── img_mortals.jpg
        ├── img_superhero.jpg
        ├── img_vision.jpg
        ├── img_circles.jpg
        ├── img_nekozilla.jpg
        ├── img_earth.jpg
        ├── img_adventure.jpg
        └── img_candyland.jpg
```

---

## Pasos de integración en Android Studio

### 1. Copiar los archivos Kotlin

Copia los siguientes archivos **reemplazando** los originales dentro de:
`app/src/main/java/com/example/demo_list_v2/`

- `Cancion.kt` (nuevo)
- `Canciones.kt` (reemplaza el original)
- `MainActivity.kt` (reemplaza el original)
- `PlaylistFragment.kt` (reemplaza el original)
- `CancionDetalleFragment.kt` (nuevo)

### 2. Copiar los layouts XML

Copia los siguientes archivos en `app/src/main/res/layout/`:

- `fragment_playlist.xml` (reemplaza el original)
- `item_cancion.xml` (reemplaza el original)
- `fragment_cancion_detalle.xml` (nuevo)

### 3. Agregar las imágenes de portadas

En `app/src/main/res/drawable/` agrega **20 imágenes** (JPG o PNG, mínimo 200×200 px) con exactamente los nombres listados en la estructura de arriba, por ejemplo:

```
img_bohemian_rhapsody.jpg
img_stairway_to_heaven.jpg
... (ver lista completa arriba)
```

Puedes descargar portadas oficiales de álbumes (de libre uso académico) o usar imágenes representativas del género/artista. **Cada imagen debe ser única**.

### 4. Verificar dependencias en build.gradle.kts (app)

Asegúrate de tener estas dependencias:

```kotlin
dependencies {
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.cardview:cardview:1.0.0")
    // las demás ya vienen con Demo_List_v2
}
```

### 5. Ejecutar el proyecto

Compila y ejecuta con `Run ▶` en Android Studio. La app debe:

- Mostrar la playlist de 20 canciones en el RecyclerView
- Al pulsar una canción, navegar al fragmento de detalle con su portada única
- Al pulsar "Volver a la Playlist", restaurar la imagen `portada.jpg` original
- El botón "Ordenar A–Z / Z–A" ordena las canciones dinámicamente

---

## Criterios de evaluación cubiertos

| Criterio | Implementación |
|---|---|
| Diseño y arquitectura (Fragments + RecyclerView) | ✅ PlaylistFragment + CancionDetalleFragment |
| RecyclerView con mín. 20 canciones | ✅ 20 canciones en Canciones.kt |
| Navegación entre fragmentos | ✅ parentFragmentManager.beginTransaction() |
| Imagen única por canción | ✅ campo imagenRes en data class Cancion |
| Restauración de imagen al regresar | ✅ onViewCreated en PlaylistFragment |
| Ordenamiento ascendente y descendente | ✅ botón A–Z / Z–A con sortBy/sortByDescending |
| ViewHolder + Adapter | ✅ SongAdapter + SongViewHolder |
| Separación lógica vs. UI | ✅ data class + object Canciones separados |
| Sintaxis Kotlin y buenas prácticas | ✅ data class, lambda, companion object |

---

## Canciones incluidas (20)

| # | Título | Artista | Álbum | Género | Duración | Lanzamiento |
|---|--------|---------|-------|--------|----------|-------------|
| 1 | Bohemian Rhapsody | Queen | A Night at the Opera | Rock Clásico | 5:55 | 31 oct 1975 |
| 2 | Stairway To Heaven | Led Zeppelin | Led Zeppelin IV | Rock Clásico | 8:02 | 08 nov 1971 |
| 3 | Hotel California | Eagles | Hotel California | Rock | 6:30 | 26 feb 1977 |
| 4 | November Rain | Guns N' Roses | Use Your Illusion I | Hard Rock | 8:57 | 01 oct 1991 |
| 5 | Nothing Else Matters | Metallica | Metallica (Black Album) | Heavy Metal | 6:29 | 12 ago 1991 |
| 6 | Comfortably Numb | Pink Floyd | The Wall | Rock Progresivo | 6:23 | 30 nov 1979 |
| 7 | Smells Like Teen Spirit | Nirvana | Nevermind | Grunge | 5:01 | 10 sep 1991 |
| 8 | Highway To Hell | AC/DC | Highway to Hell | Hard Rock | 3:28 | 27 jul 1979 |
| 9 | Sweet Child O' Mine | Guns N' Roses | Appetite for Destruction | Hard Rock | 5:56 | 21 jun 1988 |
| 10 | Enter Sandman | Metallica | Metallica (Black Album) | Heavy Metal | 5:32 | 29 jul 1991 |
| 11 | The Sound Of Silence | Disturbed | Immortalized | Heavy Metal | 4:08 | 28 ago 2015 |
| 12 | Dream On | Aerosmith | Aerosmith | Hard Rock | 4:26 | 13 jun 1973 |
| 13 | Wish You Were Here | Pink Floyd | Wish You Were Here | Rock Progresivo | 5:40 | 12 sep 1975 |
| 14 | Creep | Radiohead | Pablo Honey | Alternative | 3:58 | 21 sep 1992 |
| 15 | Black | Pearl Jam | Ten | Grunge | 5:42 | 27 ago 1991 |
| 16 | Hail To The King | Avenged Sevenfold | Hail to the King | Heavy Metal | 5:34 | 27 ago 2013 |
| 17 | Master Of Puppets | Metallica | Master of Puppets | Thrash Metal | 8:35 | 03 mar 1986 |
| 18 | Smoke On The Water | Deep Purple | Machine Head | Hard Rock | 5:40 | 25 mar 1972 |
| 19 | Iron Man | Black Sabbath | Paranoid | Heavy Metal | 5:57 | 25 sep 1970 |
| 20 | Thunderstruck | AC/DC | The Razors Edge | Hard Rock | 4:52 | 13 ago 1990 |

---

## Entrega

Según el Paso 3 del laboratorio, elige una modalidad:

- **Opción A**: comprimir todo el proyecto → `integrante1_integrante2_lab1.zip`
- **Opción B**: repositorio público en GitHub (URL incluida en el documento de evidencias)

**Fecha límite: martes 12 de mayo de 2026 a las 11:59 PM**
