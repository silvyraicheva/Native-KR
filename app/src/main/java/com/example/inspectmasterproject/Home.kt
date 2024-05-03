import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inspectmasterproject.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import com.example.inspectmasterproject.Contact


class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        var inputStream: InputStream? = null
        try {
            inputStream = requireContext().assets.open("data.txt")

            val reader = BufferedReader(InputStreamReader(inputStream))

            val dataList = mutableListOf<Contact>()

            reader.forEachLine { line ->
                val parts = line.split(",")
                if (parts.size == 3) {
                    val data = Contact(parts[0], parts[1], parts[2])
                    dataList.add(data)
                }
            }

            val adapter = DataAdapter(dataList)
            recyclerView.adapter = adapter
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }

        return view
    }
}
