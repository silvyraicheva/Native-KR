import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inspectmasterproject.R

class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewAddress: TextView = itemView.findViewById(R.id.textViewAddress)
    val textViewPhone: TextView = itemView.findViewById(R.id.textViewPhone)
    val textViewNew: TextView = itemView.findViewById(R.id.textViewNew)
}
