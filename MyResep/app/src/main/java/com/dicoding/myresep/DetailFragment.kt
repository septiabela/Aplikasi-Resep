package com.dicoding.myresep

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {
    private var articleImage: ImageView? = null
    private var articleTitle: TextView? = null
    private var articleDescription: TextView? = null
    private var position = 0

    private val articleImages = intArrayOf(
        R.drawable.foto1,
        R.drawable.foto2,
        R.drawable.foto3,
        R.drawable.foto4,
        R.drawable.foto5,
        R.drawable.foto6,
        R.drawable.foto7,
        R.drawable.foto8,
        R.drawable.foto9,
        R.drawable.foto10
    )

    private val articleTitles = arrayOf(
        "Resep Nasi Goreng",
        "Resep Ayam Goreng",
        "Resep Ayam Kecap",
        "Resep Nila Bumbu Kuning",
        "Resep Sate Kambing",
        "Resep Steak Ayam",
        "Resep Balado Terong",
        "Resep Balado Hati Ayam",
        "Resep Seblak",
        "Resep Soto"
    )

    private val articleDescriptions = arrayOf(
        "Bahan: Nasi, minyak, bawang putih, bawang merah, telur, kecap manis, garam, merica, sayuran (opsional).\n" +
                "Langkah:\n" +
                "1. Panaskan minyak, tumis bawang putih dan bawang merah.\n" +
                "2. Masukkan telur, aduk hingga setengah matang.\n" +
                "3. Tambahkan nasi, kecap manis, garam, dan merica. Aduk rata.\n" +
                "4. Tambahkan sayuran jika diinginkan. Aduk hingga matang.",

        "Bahan: Potong ayam, tepung terigu, garam, merica, minyak.\n" +
                "Langkah:\n" +
                "1. Campurkan ayam dengan garam dan merica.\n" +
                "2. Gulingkan ayam dalam tepung terigu.\n" +
                "3. Goreng ayam hingga kuning kecoklatan dan matang.",

        "Bahan: Potong ayam, kecap manis, bawang putih, bawang merah, garam, merica, minyak.\n" +
                "Langkah:\n" +
                "1. Tumis bawang putih dan bawang merah hingga harum.\n" +
                "2. Masukkan ayam, tumis hingga berubah warna.\n" +
                "3. Tambahkan kecap manis, garam, dan merica. Aduk hingga ayam matang.",

        "Bahan: Ikan nila, bumbu kuning (kunyit, bawang putih, bawang merah, cabe merah), garam, minyak.\n" +
                "Langkah:\n" +
                "1. Haluskan bumbu kuning, tumis hingga harum.\n" +
                "2. Masukkan ikan nila, tambahkan garam. Masak hingga ikan matang.",

        "Bahan: Daging kambing, bumbu sate, tusuk sate.\n" +
                "Langkah:\n" +
                "1. Potong daging kambing, rendam dalam bumbu sate.\n" +
                "2. Tusuk daging dengan tusuk sate.\n" +
                "3. Panggang sate hingga matang.",

        "Bahan: Daging ayam, garam, merica, minyak.\n" +
                "Langkah:\n" +
                "1. Lumuri daging ayam dengan garam dan merica.\n" +
                "2. Panaskan minyak, panggang daging ayam hingga matang sesuai selera.",

        "Bahan: Terong, bumbu balado, minyak.\n" +
                "Langkah:\n" +
                "1. Potong terong, goreng hingga matang.\n" +
                "2. Tumis bumbu balado, tambahkan terong. Aduk hingga terong terbalut bumbu.",

        "Bahan: Hati ayam, bumbu balado, minyak.\n" +
                "Langkah:\n" +
                "1. Goreng hati ayam hingga matang.\n" +
                "2. Tumis bumbu balado, tambahkan hati ayam. Aduk rata.",

        "Bahan: Kerupuk, tahu, kacang, mie, bumbu seblak, air.\n" +
                "Langkah:\n" +
                "1. Rendam kerupuk dan mie dalam air panas.\n" +
                "2. Tumis tahu, tambahkan kacang dan bumbu seblak.\n" +
                "3. Campurkan semua bahan, aduk rata.",

        "Bahan: Daging ayam/sapi, bawang putih, bawang merah, ketumbar, garam, lada, air, mie, sayuran.\n" +
                "Langkah:\n" +
                "1. Rebus daging dengan bumbu hingga empuk.\n" +
                "2. Tambahkan mie dan sayuran.\n" +
                "3. Sajikan dengan kuah dan pelengkap seperti bawang goreng dan jeruk nipis."
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)

        // Hubungkan komponen tampilan dengan variabel
        articleImage = view.findViewById(R.id.article_image)
        articleTitle = view.findViewById(R.id.article_title)
        articleDescription = view.findViewById(R.id.article_description)

        // Ambil posisi yang dikirimkan dari argument
        val args: Bundle? = arguments
        if (args != null) {
            position = args.getInt("position", 0)
        }

        // Setel konten sesuai dengan item yang dipilih
        articleImage?.setImageResource(articleImages[position])
        articleTitle?.text = articleTitles[position]
        articleDescription?.text = articleDescriptions[position]

        setHasOptionsMenu(true) // Aktifkan opsi menu di sini

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_back -> {
                requireActivity().onBackPressed() // Kembali ke HomeFragment
                return true
            }
            R.id.menu_share -> {
                // Implementasikan aksi berbagi di sini
                shareArticle()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun shareArticle() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = "Saya ingin berbagi resep ${articleTitles[position]}."
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Bagikan via"))
    }
}