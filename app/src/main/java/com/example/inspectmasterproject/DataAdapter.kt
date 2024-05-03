import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inspectmasterproject.Contact
import com.example.inspectmasterproject.R


class DataAdapter(private val dataList: List<Contact>) : RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_layout, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.textViewAddress.text = currentItem.address
        holder.textViewPhone.text = currentItem.phone
        holder.textViewNew.text = currentItem.new
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
